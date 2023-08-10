package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.FriendshipModel;

import java.util.List;

public class AllFriendshipsResponse extends ServiceActionResponse {
    private final List<FriendshipModel> allFriends;
    public AllFriendshipsResponse(boolean success, String errorMessage, List<FriendshipModel> allFriends) {
        super(success, errorMessage);
        this.allFriends = allFriends;
    }

    public List<FriendshipModel> getAllFriends() {
        return allFriends;
    }

    @Override
    public String toString() {
        return "AllFriendshipsResponse{" +
                "allFriends=" + allFriends +
                "} " + super.toString();
    }
}
