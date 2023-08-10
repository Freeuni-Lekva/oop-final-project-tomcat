package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.FriendRequestModel;

import java.util.List;

public class AllFriendRequestsResponse extends ServiceActionResponse {
    private final List<FriendRequestModel> allFriendRequests;

    public AllFriendRequestsResponse(boolean success, String errorMessage, List<FriendRequestModel> allFriendRequests) {
        super(success, errorMessage);
        this.allFriendRequests = allFriendRequests;
    }

    public List<FriendRequestModel> getAllFriendRequests() {
        return allFriendRequests;
    }

    @Override
    public String toString() {
        return "AllFriendRequestsResponse{" +
                "allFriendRequests=" + allFriendRequests +
                "} " + super.toString();
    }
}
