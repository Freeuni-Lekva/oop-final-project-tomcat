package ge.edu.freeuni.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a friend requests entity.
 */
@Entity
@Table(name = "friend_requests")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user" , referencedColumnName = "id" , nullable = false)
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "recipient_user" , referencedColumnName = "id" , nullable = false)
    private User recipientUser;

    @Column(name = "sent_timestamp", nullable = false)
    private Long sentTimestamp;

    // constructors


    /**
     * default constructor
     */
    public FriendRequest() {
        this.sentTimestamp = Instant.now().toEpochMilli();
    }

    /**
     * Constructor that receives 2 users:sender and recipient and creates this request.
     *
     * @param senderUser - the user sending the request.
     * @param recipientUser - the user the request is sent to.
     */
    public FriendRequest(User senderUser, User recipientUser) {
        this.senderUser = senderUser;
        this.recipientUser = recipientUser;
        this.sentTimestamp = Instant.now().toEpochMilli();
    }

    /**
     * sets the id of the friendRequest.
     *
     * @param id to set.
     */
    // getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * returns the id value of the friendRequest.
     *
     * @return id value.
     */
    public Long getId() {
        return id;
    }

    /**
     * returns the user who sent the friend request.
     *
     * @return senderUser of the request.
     */
    public User getSenderUser() {
        return senderUser;
    }

    /**
     * sets sender user to the received value.
     *
     * @param senderUser - who sent the request.
     */
    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    /**
     * returns the user the request is sent to.
     *
     * @return recipient user.
     */
    public User getRecipientUser() {
        return recipientUser;
    }

    /**
     * sets the user who receives the request.
     *
     * @param recipientUser - who receives the request.
     */
    public void setRecipientUser(User recipientUser) {
        this.recipientUser = recipientUser;
    }

    /**
     * returns the time at which the request was sent.
     *
     * @return sent time of the request.
     */
    public Long getSentTimestamp() {
        return sentTimestamp;
    }

    /**
     * sets the time of the request sending to the new value.
     *
     * @param sentTimestamp new value of the time sent.
     */
    public void setSentTimestamp(Long sentTimestamp) {
        this.sentTimestamp = sentTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendRequest that = (FriendRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(senderUser, that.senderUser) && Objects.equals(recipientUser, that.recipientUser) && Objects.equals(sentTimestamp, that.sentTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderUser, recipientUser, sentTimestamp);
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", senderUser=" + senderUser +
                ", recipientUser=" + recipientUser +
                ", sentTimestamp=" + sentTimestamp +
                '}';
    }
}
