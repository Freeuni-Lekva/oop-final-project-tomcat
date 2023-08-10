package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.QuizModel;

public class QuizResponse extends ServiceActionResponse {
    private final QuizModel quiz;

    public QuizResponse(boolean success, String errorMessage, QuizModel quiz) {
        super(success, errorMessage);
        this.quiz = quiz;
    }

    public QuizModel getQuiz() {
        return quiz;
    }

    @Override
    public String toString() {
        return "QuizResponse{" +
                "quiz=" + quiz +
                "} " + super.toString();
    }
}
