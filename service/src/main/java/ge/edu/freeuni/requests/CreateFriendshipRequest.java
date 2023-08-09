package ge.edu.freeuni.requests;

import java.util.Objects;

public class CreateFriendshipRequest {
    private final Long senderId;
    private final Long recipientId;

    public CreateFriendshipRequest(Long senderId, Long recipientId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateFriendshipRequest that = (CreateFriendshipRequest) o;

        if (!Objects.equals(senderId, that.senderId)) return false;
        return Objects.equals(recipientId, that.recipientId);
    }

    @Override
    public int hashCode() {
        int result = senderId != null ? senderId.hashCode() : 0;
        result = 31 * result + (recipientId != null ? recipientId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateFriendshipRequest{" +
                "senderId=" + senderId +
                ", recipientId=" + recipientId +
                '}';
    }
}
