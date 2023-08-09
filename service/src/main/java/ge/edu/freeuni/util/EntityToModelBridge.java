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

import java.util.List;
import java.util.stream.Collectors;

public class EntityToModelBridge {
    public static FriendRequestModel toFriendRequestModel(FriendRequest request) {
        return new FriendRequestModel(
                request.getId(),
                toUserModel(request.getSenderUser()),
                toUserModel(request.getRecipientUser())
        );
    }

    public static AnswerModel toAnswerModel(Answer answer) {
        return new AnswerModel(
                answer.getId(),
                answer.getQuestion().getId(),
                answer.getAnswer(),
                answer.getAccuracy().equalsIgnoreCase("t")
        );
    }

    public static FriendshipModel toFriendshipModel(Friendship friendship) {
        return new FriendshipModel(
                friendship.getId(),
                toUserModel(friendship.getFirstUser()),
                toUserModel(friendship.getSecondUser())
        );
    }

    public static QuestionModel toQuestionModel(Question question) {
        List<AnswerModel> answers = question.getAnswers().stream()
                .map(EntityToModelBridge::toAnswerModel)
                .collect(Collectors.toList());

        return new QuestionModel(
                question.getId(),
                question.getQuiz().getId(),
                question.getQuestionType(),
                answers,
                question.getImageUrl(),
                question.getPoints()
        );
    }

    public static QuizModel toQuizModel(Quiz quiz) {
        List<QuestionModel> questions = quiz.getQuestions().stream()
                .map(EntityToModelBridge::toQuestionModel)
                .collect(Collectors.toList());
        return new QuizModel(
                quiz.getId(),
                quiz.getName(),
                quiz.getDescription(),
                toUserModel(quiz.getOwner()),
                questions
        );
    }

    public static UserModel toUserModel(User user) {
        return new UserModel(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                null
        );
    }
}
