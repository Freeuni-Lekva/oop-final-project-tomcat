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

public class ModelToEntityBridge {
    public static FriendRequest toFriendRequestEntity(FriendRequestModel request) {
        return new FriendRequest(
                toUserEntity(request.getSender()),
                toUserEntity(request.getRecipient())
        );
    }

    //TODO finish methods
    public static Answer toAnswerEntity(AnswerModel answer) {
        return null;
    }

    public static Friendship toFriendshipEntity(FriendshipModel friendship) {
        return null;
    }

    public static Question toQuestionEntity(QuestionModel question) {
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
