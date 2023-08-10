package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.QuizGameModel;

public class QuizGameResponse extends ServiceActionResponse {
    private final QuizGameModel quizGame;

    public QuizGameResponse(boolean success, String errorMessage, QuizGameModel quizGame) {
        super(success, errorMessage);
        this.quizGame = quizGame;
    }

    public QuizGameModel getQuizGame() {
        return quizGame;
    }

    @Override
    public String toString() {
        return "QuizGameResponse{" +
                "quizGame=" + quizGame +
                "} " + super.toString();
    }
}
