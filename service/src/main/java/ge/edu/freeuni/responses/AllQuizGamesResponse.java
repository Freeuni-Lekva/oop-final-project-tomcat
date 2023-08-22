package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.QuizGameModel;

import java.util.List;

public class AllQuizGamesResponse extends ServiceActionResponse {
    private final List<QuizGameModel> allQuizGames;
    public AllQuizGamesResponse(boolean success, String errorMessage, List<QuizGameModel> allQuizGames) {
        super(success, errorMessage);
        this.allQuizGames = allQuizGames;
    }

    public List<QuizGameModel> getAllQuizGames() {
        return allQuizGames;
    }

    @Override
    public String toString() {
        return "AllQuizGamesResponse{" +
                "allQuizGames=" + allQuizGames +
                "} " + super.toString();
    }
}
