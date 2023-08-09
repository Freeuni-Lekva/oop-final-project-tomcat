package ge.edu.freeuni.models;

import java.util.Objects;

public class FriendRequestModel {
    private final Long id;
    private UserModel sender;
    private UserModel recipient;

    public FriendRequestModel(Long id) {
        this.id = id;
    }

    public FriendRequestModel(Long id, UserModel sender, UserModel recipient) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Long getId() {
        return id;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public UserModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserModel recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendRequestModel that = (FriendRequestModel) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(sender, that.sender)) return false;
        return Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendRequestModel{" +
                "id=" + id +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }
}
