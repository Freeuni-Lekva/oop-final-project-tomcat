package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.QuizModel;

import java.util.List;

/**
 * Purpose of this class is to represent all the quizzes of a user
 * or the most popular quizzes at the moment
 */
public class QuizzesResponse extends ServiceActionResponse {
    private final List<QuizModel> quizzes;

    public QuizzesResponse(boolean success, String errorMessage, List<QuizModel> quizzes) {
        super(success, errorMessage);
        this.quizzes = quizzes;
    }

    public List<QuizModel> getQuizzes() {
        return quizzes;
    }

    @Override
    public String toString() {
        return "AllQuizzesResponse{" +
                "quizzes=" + quizzes +
                "} " + super.toString();
    }
}
