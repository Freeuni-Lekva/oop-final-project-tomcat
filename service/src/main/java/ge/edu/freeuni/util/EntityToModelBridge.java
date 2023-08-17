package ge.edu.freeuni.util;

import ge.edu.freeuni.entities.*;
import ge.edu.freeuni.models.*;

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
                answer.getAccuracy().getValue(),
                answer.getPoints()
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
                question.getQuestion(),
                question.getQuestionType(),
                answers,
                question.getImageUrl()
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
                questions,
                quiz.getCreationTimestamp());
    }

    public static QuizGameModel toQuizGameModel(QuizGame quizGame) {
        return new QuizGameModel(
                quizGame.getId(),
                toQuizModel(quizGame.getQuiz()),
                toUserModel(quizGame.getPlayer()),
                quizGame.getScore(),
                null,
                quizGame.getStartTimestamp(),
                quizGame.getFinishTimestamp()
        );
    }

    public static QuizGameModel toQuizGameModel(QuizGame quizGame, Long id) {
        return new QuizGameModel(
                id,
                toQuizModel(quizGame.getQuiz()),
                toUserModel(quizGame.getPlayer()),
                quizGame.getScore(),
                null,
                quizGame.getStartTimestamp(),
                quizGame.getFinishTimestamp()
        );
    }


    public static ChallengeModel toChallengeModel(Challenge challenge) {
        return new ChallengeModel(
                challenge.getId(),
                toQuizModel(challenge.getQuiz()),
                toUserModel(challenge.getSender()),
                toUserModel(challenge.getReceiver()),
                challenge.getBestScore()
        );
    }

    public static UserModel toUserModel(User user) {
        return new UserModel(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                null
        );
    }


    public static NoteModel toNoteModel(Note note) {
        return new NoteModel(
                note.getId(),
                EntityToModelBridge.toUserModel(note.getFrom()),
                EntityToModelBridge.toUserModel(note.getTo()),
                note.getMessage(),
                note.getTimestamp(),
                note.getSubject()

        );
    }
}
