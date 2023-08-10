package ge.edu.freeuni.util;

import ge.edu.freeuni.entities.Answer;
import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.AnswerModel;
import ge.edu.freeuni.models.FriendRequestModel;
import ge.edu.freeuni.models.FriendshipModel;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAOFactory;

public class ModelToEntityBridge {
    public static FriendRequest toFriendRequestEntity(FriendRequestModel request) {
        return new FriendRequest(
                toUserEntity(request.getSender()),
                toUserEntity(request.getRecipient())
        );
    }

    //TODO finish methods
    public static Answer toAnswerEntity(AnswerModel answer) {
        Question question = DAOFactory.getInstance().getDAO(Question.class).read(answer.getQuestionId());
        if (question == null) {
            return null;
        }
        return new Answer(
                question,
                answer.getAnswer(),
                answer.isCorrect() ? "t" : "f"
        );
    }

    public static Friendship toFriendshipEntity(FriendshipModel friendship) {
        return new Friendship(
                toUserEntity(friendship.getUser1()),
                toUserEntity(friendship.getUser2())
        );
    }

    public static Question toQuestionEntity(QuestionModel question) {
        Quiz quiz = DAOFactory.getInstance().getDAO(Quiz.class).read(question.getQuizId());
        if (quiz == null) {
            return null;
        }
        return null;
    }

    public static Quiz toQuizEntity(QuizModel quiz) {
        return null;
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
