package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.ChallengeModel;
import ge.edu.freeuni.models.QuizModel;

import java.util.List;

public class ChallengesResponse extends ServiceActionResponse {
    private final List<ChallengeModel> challenges;

    public ChallengesResponse(boolean success, String errorMessage, List<ChallengeModel> challenges) {
        super(success, errorMessage);
        this.challenges = challenges;
    }


    public List<ChallengeModel> getQuizzes() {
        return challenges;
    }

    @Override
    public String toString() {
        return "AllChallengesResponse{" +
                "challenges=" + challenges +
                "} " + super.toString();
    }
}
