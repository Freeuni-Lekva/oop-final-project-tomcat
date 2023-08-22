package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Answer;
import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.QuizGame;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.enums.QuestionType;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizGameModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.QuizGameResponse;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.responses.QuizzesResponse;
import ge.edu.freeuni.util.DatetimeUtil;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuizService {
    private static final int NUM_OF_POPULAR_QUIZZES = 10;
    private static final int NUM_OF_RECENT_QUIZZES = 10;
    private static final int NUM_OF_RECENT_QUIZZES_OF_USER = 10;
    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
    private DAO<Quiz> quizDAO = DAOFactory.getInstance().getDAO(Quiz.class);
    private DAO<QuizGame> quizGameDAO = DAOFactory.getInstance().getDAO(QuizGame.class);
    private final Map<Long, QuizModel> allQuizzes;
    private final Map<Long, QuizGameModel> allGames;
    private final Map<Long, QuizGameModel> practiceGames;

    public QuizService() {
        this.allQuizzes = getAllQuizzes();
        this.allGames = getAllQuizGames();
        this.practiceGames = new HashMap<>();
    }

    private Map<Long, QuizModel> getAllQuizzes() {
        try {
            return quizDAO.getAll().stream()
                    .map(EntityToModelBridge::toQuizModel)
                    .collect(Collectors.toMap(QuizModel::getId, Function.identity()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private Map<Long, QuizGameModel> getAllQuizGames() {
        try {
            return quizGameDAO.getAll().stream()
                    .map(EntityToModelBridge::toQuizGameModel)
                    .collect(Collectors.toMap(QuizGameModel::getId, Function.identity()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public QuizResponse createQuiz(QuizModel newQuiz, Long userId) {
        try {
            if (newQuiz.getName().isEmpty() || newQuiz.getDescription().isEmpty()
                    || userId == null || newQuiz.getQuestions().isEmpty()) {
                return new QuizResponse(false, "Invalid quiz credentials", null);
            }
            Quiz quiz = ModelToEntityBridge.toQuizEntity(newQuiz);
            User user = userDAO.read(userId);
            quiz.setOwner(user);

            Long id = (Long) quizDAO.create(quiz);
            QuizModel quizModel = EntityToModelBridge.toQuizModel(quiz);
            allQuizzes.put(id, quizModel);
            return new QuizResponse(true, null, quizModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizResponse(false, "Error while creating a quiz. Try again later", null);
        }
    }

    public QuizResponse getQuiz(Long id) {
        try {
            Quiz quiz = quizDAO.read(id);
            if (quiz == null) {
                return new QuizResponse(false, "Quiz doesn't exist", null);
            }
            QuizModel quizModel = EntityToModelBridge.toQuizModel(quiz);
            return new QuizResponse(true, null, quizModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizResponse(false, "Error while getting the quiz. Try again later", null);
        }
    }

    public QuizzesResponse getAllQuizzes(Long ownerId) {
        try {
            List<QuizModel> quizzes = allQuizzes.values().stream()
                    .filter(quiz -> Objects.equals(quiz.getOwner().getId(), ownerId))
                    .collect(Collectors.toList());
            return new QuizzesResponse(true, null, quizzes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizzesResponse(false, "Error while getting quizzes. Try again later", null);
        }
    }

    public QuizzesResponse getMostPopularQuizzes() {
        try {
            if (allQuizzes.isEmpty()) {
                return new QuizzesResponse(false, "No quizzes available", new ArrayList<>());
            }
            if (allGames.isEmpty()) {
                return new QuizzesResponse(false, "No quizzes are played", new ArrayList<>());
            }

            int numOfTopQuizzes = Math.min(NUM_OF_POPULAR_QUIZZES, allQuizzes.size());
            Map<QuizModel, Long> quizPlayCounts = allGames.values().stream()
                    .collect(Collectors.groupingBy(QuizGameModel::getQuiz, Collectors.counting()));

            List<QuizModel> mostPlayedQuizzes = quizPlayCounts.entrySet().stream()
                    .sorted(Map.Entry.<QuizModel, Long>comparingByValue().reversed())
                    .limit(numOfTopQuizzes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            return new QuizzesResponse(true, null, mostPlayedQuizzes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizzesResponse(false, "Error while getting quizzes. Try again later", null);
        }
    }

    public QuizzesResponse getMostRecentQuizzes() {
        try {
            if (allQuizzes.isEmpty()) {
                return new QuizzesResponse(false, "No quizzes available", null);
            }

            List<QuizModel> mostRecentQuizzes = allQuizzes.values().stream()
                    .sorted(Comparator.comparing(QuizModel::getCreationTimestamp).reversed())
                    .limit(NUM_OF_RECENT_QUIZZES)
                    .collect(Collectors.toList());

            return new QuizzesResponse(true, null, mostRecentQuizzes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizzesResponse(false, "Error while getting quizzes. Try again later", null);
        }
    }

    public QuizzesResponse getMostRecentQuizzes(Long userId) {
        try {
            if (allQuizzes.isEmpty()) {
                return new QuizzesResponse(false, "No quizzes available", null);
            }

            List<QuizModel> mostRecentQuizzes = allQuizzes.values().stream()
                    .filter(quiz -> Objects.equals(quiz.getOwner().getId(), userId))
                    .sorted(Comparator.comparing(QuizModel::getCreationTimestamp).reversed())
                    .limit(NUM_OF_RECENT_QUIZZES_OF_USER)
                    .collect(Collectors.toList());

            if (mostRecentQuizzes.isEmpty()) {
                return new QuizzesResponse(false, "No quizzes available", null);
            }

            return new QuizzesResponse(true, null, mostRecentQuizzes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizzesResponse(false, e.getMessage(), null);
        }
    }

    public QuizGameResponse startQuiz(Long quizId, Long playerId, boolean practice) {
        try {
            Quiz quiz = quizDAO.read(quizId);
            if (quiz == null) {
                return new QuizGameResponse(false, "Quiz doesn't exist", null);
            }
            User player = userDAO.read(playerId);
            if (player == null) {
                return new QuizGameResponse(false, "User doesn't exist", null);
            }

            QuizGame quizGame = new QuizGame(
                    quiz,
                    player,
                    0,
                    0,
                    System.currentTimeMillis() / 1000L,
                    null,
                    Bool.getByValue(practice).name()
            );
            Long gameId = (Long) quizGameDAO.create(quizGame);
            QuizGameModel gameModel = EntityToModelBridge.toQuizGameModel(quizGame, gameId);
            if (practice) {
                practiceGames.put(gameId, gameModel);
            } else {
                allGames.put(gameId, gameModel);
            }
            if (Objects.equals(Bool.TRUE.name(), quiz.getRandomizeQuestions())) {
                gameModel.getQuiz().setQuestions(shuffleQuestions(gameModel.getQuiz().getQuestions()));
            }
            return new QuizGameResponse(true, null, gameModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizGameResponse(false, "Error while starting the quiz. Try again later", null);
        }
    }

    private List<QuestionModel> shuffleQuestions(List<QuestionModel> questions) {
        List<QuestionModel> shuffledQuestions = new ArrayList<>(questions);

        Random random = new Random();
        for (int i = shuffledQuestions.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            QuestionModel temp = shuffledQuestions.get(i);
            shuffledQuestions.set(i, shuffledQuestions.get(j));
            shuffledQuestions.set(j, temp);
        }

        return shuffledQuestions;
    }

    public QuizGameResponse finishQuiz(Map<Long, String> questionsAnswers, Long quizGameId, Long playerId) {
        try {
            QuizGame quizGame = quizGameDAO.read(quizGameId);
            Map<Long, QuizGameModel> games = Objects.equals(Bool.TRUE.name(), quizGame.isPractice()) ? practiceGames : allGames;
            if (!Objects.equals(playerId, quizGame.getPlayer().getId())) {
                throw new RuntimeException("Invalid user credentials");
            }

            int maxScore = 0;
            int actualScore = 0;
            for (Question question : quizGame.getQuiz().getQuestions()) {
                String ans = questionsAnswers.get(question.getId());
                for (Answer answer : question.getAnswers()) {
                    if (answer.getAccuracy().getValue()) {
                        maxScore += answer.getPoints();
                        if ((question.getQuestionType() == QuestionType.MULTIPLE_CHOICE && Objects.equals(answer.getId(), Long.parseLong(ans)))
                                || (question.getQuestionType() != QuestionType.MULTIPLE_CHOICE && Objects.equals(answer.getAnswer(), ans))) {
                            actualScore += answer.getPoints();
                        }
                    }
                }
            }

            long finishTimestamp = System.currentTimeMillis() / 1000L;
            quizGame.setFinishTimestamp(finishTimestamp);
            quizGame.setScore(actualScore);
            quizGame.setMaxScore(maxScore);
            quizGameDAO.update(quizGame);

            QuizGameModel gameModel = games.get(quizGameId);
            gameModel.setFinishTimestamp(finishTimestamp);
            gameModel.setScore(actualScore);
            gameModel.setMaxScore(maxScore);
            gameModel.setDuration(DatetimeUtil.getDuration(gameModel.getStartTimestamp(), finishTimestamp));

            games.replace(gameModel.getId(), gameModel);
            return new QuizGameResponse(true, null, gameModel);
        } catch (RuntimeException e) {
            return new QuizGameResponse(false, "Error while finishing the quiz. Try again later", null);
        }
    }

    public QuizGameResponse getQuizGame(Long quizGameId) {
        try {
            QuizGameModel quizGame = allGames.get(quizGameId);
            if (quizGame == null) {
                quizGame = practiceGames.remove(quizGameId);
                quizGameDAO.delete(quizGame.getId());
            }
            return new QuizGameResponse(true, null, quizGame);
        } catch (Exception e) {
            return new QuizGameResponse(false, "Unable to load quiz results, Probably you are trying to access a game played in practice mode", null);
        }
    }

    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public void setQuizDAO(DAO<Quiz> quizDAO) {
        this.quizDAO = quizDAO;
    }

    public void setQuizGameDAO(DAO<QuizGame> quizGameDAO) {
        this.quizGameDAO = quizGameDAO;
    }
}
