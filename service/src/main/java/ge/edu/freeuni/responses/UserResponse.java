package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.UserModel;

public class UserResponse extends ServiceActionResponse {
    private final UserModel user;

    public UserResponse(boolean success, String errorMessage, UserModel user) {
        super(success, errorMessage);
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                "} " + super.toString();
    }
}
