package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.ChallengeModel;

public class ChallengeResponse extends ServiceActionResponse{
    private final ChallengeModel challenge;
    public ChallengeResponse(boolean success, String errorMessage, ChallengeModel challenge) {
        super(success, errorMessage);
        this.challenge = challenge;
    }

    public ChallengeModel getChallenge() {
        return challenge;
    }

    @Override
    public String toString() {
        return "ChallengeResponse{" +
                "challenge=" + challenge +
                "} " + super.toString();
    }
}
