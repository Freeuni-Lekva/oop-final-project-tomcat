package ge.edu.freeuni.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendRequestModel extends NotificationModel{
    private final Long id;
    private UserModel sender;
    private UserModel recipient;

    public FriendRequestModel(Long id, Long timeStamp) {
        super(timeStamp);
        this.id = id;
    }

    @Override
    public String getNotificationType() {
        return "FriendRequest";
    }

    @Override
    public List<String> getNotificationLabel() {
        List<String> result = new ArrayList<>();
        result.add(this.getSender().getUsername());
        return result;
    }

    public FriendRequestModel(Long id, UserModel sender, UserModel recipient, Long timestamp) {
        super(timestamp);
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
