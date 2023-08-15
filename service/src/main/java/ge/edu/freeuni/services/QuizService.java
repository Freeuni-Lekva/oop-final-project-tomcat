package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.QuizGame;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.AnswerModel;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizGameModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.QuizGameResponse;
import ge.edu.freeuni.responses.QuizzesResponse;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.util.DatetimeUtil;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public QuizService() {
        this.allQuizzes = getAllQuizzes();
        this.allGames = getAllQuizGames();
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

    public ServiceActionResponse createQuiz(QuizModel newQuiz) {
        try {
            if (newQuiz.getName().isEmpty() || newQuiz.getDescription().isEmpty()
                    || newQuiz.getOwner() == null || newQuiz.getQuestions().isEmpty()) {
                return new ServiceActionResponse(false, "Invalid quiz credentials");
            }
            Quiz quiz = ModelToEntityBridge.toQuizEntity(newQuiz);
            Long id = (Long) quizDAO.create(quiz);
            allQuizzes.put(id, newQuiz);
            return new ServiceActionResponse(true, null);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ServiceActionResponse(false, e.getMessage());
        }
    }

    //FIXME commented parts are actual implementations, hardcoded parts are for testing GUI
    public QuizResponse getQuiz(Long id) {
//        try {
//            Quiz quiz = quizDAO.read(id);
//            if (quiz == null) {
//                return new QuizResponse(false, "Quiz doesn't exist", null);
//            }
//            QuizModel quizModel = EntityToModelBridge.toQuizModel(quiz);
//            return new QuizResponse(true, null, quizModel);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            return new QuizResponse(false, e.getMessage(), null);
//        }
        QuestionModel questionModel = new QuestionModel(1L);
        QuestionModel questionModel1 = new QuestionModel(2L);
        QuestionModel questionModel2 = new QuestionModel(2L);
        QuestionModel questionModel3 = new QuestionModel(2L);
        List<QuestionModel> list = new ArrayList<>();
        list.add(questionModel);
        list.add(questionModel1);
        list.add(questionModel2);
        list.add(questionModel3);
        QuizModel quizModel = new QuizModel(id, "World War II: Expert Edition",
                "\"Embark on a comprehensive journey through the pivotal events, strategies, " +
                        "and stories that defined World War II with our expert-level quiz. " +
                        "Test your historical knowledge as you delve into the intricacies of the most significant conflict in human history." +
                        " From the battlefronts to the home fronts, challenge yourself to answer questions about leaders, " +
                        "battles, innovations, and the global impact of World War II. Whether you're a history enthusiast or a scholar, " +
                        "this quiz will put your expertise to the ultimate test and uncover the depths of your understanding of World War II.\"",
                new UserModel(1L, "saba", "saba", "saba", "saba"), list, null);
        return new QuizResponse(true, null, quizModel);
    }

    public QuizzesResponse getAllQuizzes(Long ownerId) {
        try {
            List<QuizModel> quizzes = allQuizzes.values().stream()
                    .filter(quiz -> Objects.equals(quiz.getOwner().getId(), ownerId))
                    .collect(Collectors.toList());
            return new QuizzesResponse(true, null, quizzes);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizzesResponse(false, e.getMessage(), null);
        }
    }

    public QuizzesResponse getMostPopularQuizzes() {
//        try {
//            if (allQuizzes.isEmpty()) {
//                return new QuizzesResponse(false, "No quizzes available", new ArrayList<>());
//            }
//            if (allGames.isEmpty()) {
//                return new QuizzesResponse(false, "No quizzes are played", new ArrayList<>());
//            }
//
//            int numOfTopQuizzes = Math.min(NUM_OF_POPULAR_QUIZZES, allQuizzes.size());
//            Map<QuizModel, Long> quizPlayCounts = allGames.values().stream()
//                    .collect(Collectors.groupingBy(QuizGameModel::getQuiz, Collectors.counting()));
//
//            List<QuizModel> mostPlayedQuizzes = quizPlayCounts.entrySet().stream()
//                    .sorted(Map.Entry.<QuizModel, Long>comparingByValue().reversed())
//                    .limit(numOfTopQuizzes)
//                    .map(Map.Entry::getKey)
//                    .collect(Collectors.toList());
//
//            return new QuizzesResponse(true, null, mostPlayedQuizzes);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            return new QuizzesResponse(false, e.getMessage(), null);
//        }
        QuestionModel questionModel = new QuestionModel(1L);
        QuestionModel questionModel1 = new QuestionModel(2L);
        QuestionModel questionModel2 = new QuestionModel(2L);
        QuestionModel questionModel3 = new QuestionModel(2L);
        List<QuestionModel> list = new ArrayList<>();
        list.add(questionModel);
        list.add(questionModel1);
        list.add(questionModel2);
        list.add(questionModel3);
        QuizModel quizModel = new QuizModel(1L, "World War II: Expert Edition",
                "\"Embark on a comprehensive journey through the pivotal events, strategies, " +
                        "and stories that defined World War II with our expert-level quiz. " +
                        "Test your historical knowledge as you delve into the intricacies of the most significant conflict in human history." +
                        " From the battlefronts to the home fronts, challenge yourself to answer questions about leaders, " +
                        "battles, innovations, and the global impact of World War II. Whether you're a history enthusiast or a scholar, " +
                        "this quiz will put your expertise to the ultimate test and uncover the depths of your understanding of World War II.\"", null, list, null);
        QuizModel quizModel1 = new QuizModel(2L, "2 quiz", "SUPER QUIZ", null, list, null);
        List<QuizModel> quizModels = new ArrayList<>();
        quizModels.add(quizModel);
        quizModels.add(quizModel1);
        return new QuizzesResponse(true, null, quizModels);
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
            return new QuizzesResponse(false, e.getMessage(), null);
        }
//        QuestionModel questionModel = new QuestionModel(1L);
//        QuestionModel questionModel1 = new QuestionModel(2L);
//        List<QuestionModel> list = new ArrayList<>();
//        list.add(questionModel);
//        list.add(questionModel1);
//        QuizModel quizModel = new QuizModel(3L,"3 quiz", "SUPER QUIZ", null, list, null);
//        QuizModel quizModel1 = new QuizModel(4L,"4 quiz", "SUPER QUIZ", null, list, null);
//        List<QuizModel> quizModels = new ArrayList<>();
//        quizModels.add(quizModel);
//        quizModels.add(quizModel1);
//        return new QuizzesResponse(true, null, quizModels);
    }

    public QuizzesResponse getMostRecentQuizzes(Long userId) {
//        try {
//            if (allQuizzes.isEmpty()) {
//                return new QuizzesResponse(false, "No quizzes available", null);
//            }
//
//            List<QuizModel> mostRecentQuizzes = allQuizzes.values().stream()
//                    .filter(quiz -> Objects.equals(quiz.getOwner().getId(), userId))
//                    .sorted(Comparator.comparing(QuizModel::getCreationTimestamp).reversed())
//                    .limit(NUM_OF_RECENT_QUIZZES)
//                    .collect(Collectors.toList());
//
//            return new QuizzesResponse(true, null, mostRecentQuizzes);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            return new QuizzesResponse(false, e.getMessage(), null);
//        }
        QuestionModel questionModel = new QuestionModel(1L);
        QuestionModel questionModel1 = new QuestionModel(2L);
        QuestionModel questionModel2 = new QuestionModel(3L);
        List<QuestionModel> list = new ArrayList<>();
        list.add(questionModel);
        list.add(questionModel1);
        list.add(questionModel2);
        QuizModel quizModel = new QuizModel(5L, "5 quiz", "SUPER QUIZ", null, list, null);
        QuizModel quizModel1 = new QuizModel(6L, "6 quiz", "SUPER QUIZ", null, list, null);
        List<QuizModel> quizModels = new ArrayList<>();
        quizModels.add(quizModel);
        quizModels.add(quizModel1);
        return new QuizzesResponse(true, null, quizModels);
    }

    public QuizGameResponse startQuiz(Long quizId, Long playerId) {
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
                    null,
                    System.currentTimeMillis(),
                    null
            );
            Long gameId = (Long) quizGameDAO.create(quizGame);
            QuizGameModel gameModel = EntityToModelBridge.toQuizGameModel(quizGame, gameId);
            allGames.put(gameId, gameModel);
            return new QuizGameResponse(true, null, gameModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new QuizGameResponse(false, e.getMessage(), null);
        }
    }

    public QuizGameResponse finishQuiz(QuizGameModel gameModel, List<AnswerModel> answers) {
        try {
            Map<Long, AnswerModel> correctAnswers = new HashMap<>();
            int maxScore = 0;
            for (QuestionModel question : gameModel.getQuiz().getQuestions()) {
                for (AnswerModel answer : question.getAnswers()) {
                    if (answer.isCorrect()) {
                        correctAnswers.put(answer.getId(), answer);
                        maxScore += answer.getPoints();
                    }
                }
            }

            int actualScore = 0;
            for (AnswerModel answer : answers) {
                if (correctAnswers.containsKey(answer.getId())) {
                    actualScore += answer.getPoints();
                }
            }

            QuizGame quizGame = quizGameDAO.read(gameModel.getId());
            long finishTimestamp = System.currentTimeMillis();
            quizGame.setFinishTimestamp(finishTimestamp);
            quizGame.setScore(actualScore);
            quizGameDAO.update(quizGame);

            gameModel.setFinishTimestamp(finishTimestamp);
            gameModel.setScore(actualScore);
            gameModel.setMaxScore(maxScore);
            gameModel.setDuration(DatetimeUtil.getDuration(gameModel.getStartTimestamp(), finishTimestamp));

            allGames.replace(gameModel.getId(), gameModel);
            return new QuizGameResponse(true, null, gameModel);
        } catch (RuntimeException e) {
            return new QuizGameResponse(false, e.getMessage(), null);
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
