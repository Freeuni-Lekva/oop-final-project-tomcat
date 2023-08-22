package ge.edu.freeuni.util;

import ge.edu.freeuni.entities.Answer;
import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.models.AnswerModel;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class ModelToEntityBridge {

    public static Answer toAnswerEntity(AnswerModel answer, Question question) {
        try {
            return new Answer(
                    question,
                    answer.getAnswer(),
                    Bool.getByValue(answer.isCorrect()),
                    answer.getPoints()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Question toQuestionEntity(QuestionModel question, Quiz quiz) {
        try {
            Question newQuestion = new Question(
                    quiz,
                    question.getQuestion(),
                    question.getBeforeBlank(),
                    question.getAfterBlank(),
                    question.getQuestionType(),
                    null,
                    question.getImageUrl()
            );
            List<Answer> answers = question.getAnswers().stream()
                    .map(answer -> ModelToEntityBridge.toAnswerEntity(answer, newQuestion))
                    .collect(Collectors.toList());
            newQuestion.setAnswers(answers);
            return newQuestion;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Quiz toQuizEntity(QuizModel quiz) {
        try {
            Quiz newQuiz = new Quiz(
                    quiz.getName(),
                    quiz.getDescription(),
                    null,
                    null,
                    quiz.isRandomizeQuestions(),
                    quiz.isOnePage(),
                    quiz.isImmediateCorrection(),
                    quiz.isPracticeMode()
            );
            List<Question> questions = quiz.getQuestions().stream()
                    .map(question -> ModelToEntityBridge.toQuestionEntity(question, newQuiz))
                    .collect(Collectors.toList());
            newQuiz.setQuestions(questions);
            return newQuiz;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
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
