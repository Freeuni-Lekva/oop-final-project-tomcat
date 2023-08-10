package ge.edu.freeuni.util;

import ge.edu.freeuni.entities.Answer;
import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.QuizGame;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.AnswerModel;
import ge.edu.freeuni.models.FriendRequestModel;
import ge.edu.freeuni.models.FriendshipModel;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizGameModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAOFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ModelToEntityBridge {
    public static FriendRequest toFriendRequestEntity(FriendRequestModel request) {
        return new FriendRequest(
                toUserEntity(request.getSender()),
                toUserEntity(request.getRecipient())
        );
    }

    public static Answer toAnswerEntity(AnswerModel answer) {
        try {
            Question question = DAOFactory.getInstance().getDAO(Question.class).read(answer.getQuestionId());
            if (question == null) {
                return null;
            }
            return new Answer(
                    question,
                    answer.getAnswer(),
                    answer.isCorrect() ? "t" : "f",
                    answer.getPoints()
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Friendship toFriendshipEntity(FriendshipModel friendship) {
        return new Friendship(
                toUserEntity(friendship.getUser1()),
                toUserEntity(friendship.getUser2())
        );
    }

    public static Question toQuestionEntity(QuestionModel question) {
        try {
            Quiz quiz = DAOFactory.getInstance().getDAO(Quiz.class).read(question.getQuizId());
            if (quiz == null) {
                return null;
            }
            List<Answer> answers = question.getAnswers().stream()
                    .map(ModelToEntityBridge::toAnswerEntity)
                    .collect(Collectors.toList());
            return new Question(
                    quiz,
                    question.getQuestionType(),
                    answers,
                    question.getImageUrl()
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Quiz toQuizEntity(QuizModel quiz) {
        try {
            List<Question> questions = quiz.getQuestions().stream()
                    .map(ModelToEntityBridge::toQuestionEntity)
                    .collect(Collectors.toList());
            return new Quiz(
                    quiz.getName(),
                    quiz.getDescription(),
                    toUserEntity(quiz.getOwner()),
                    questions
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static QuizGame toQuizGameEntity(QuizGameModel quizGame) {
        return new QuizGame(
                toQuizEntity(quizGame.getQuiz()),
                toUserEntity(quizGame.getUser()),
                quizGame.getScore(),
                quizGame.getStartTimestamp(),
                quizGame.getFinishTimestamp()
        );
    }

    public static User toUserEntity(UserModel user) {
        return new User(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword()
        );
    }
}
