package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a friend requests entity.
 */
@Entity
@Table(name = "friend_requests")
public class FriendRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user" , referencedColumnName = "id" , nullable = false)
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "recipient_user" , referencedColumnName = "id" , nullable = false)
    private User recipientUser;

    @Column(name = "sent_time", nullable = false)
    private LocalDateTime sentTime;

    // constructors


    /**
     * default constructor
     */
    public FriendRequests() {
        this.sentTime = LocalDateTime.now();
    }

    /**
     * Constructor that receives 2 users:sender and recipient and creates this request.
     *
     * @param senderUser - the user sending the request.
     * @param recipientUser - the user the request is sent to.
     */
    public FriendRequests(User senderUser, User recipientUser) {
        this.senderUser = senderUser;
        this.recipientUser = recipientUser;
        this.sentTime = LocalDateTime.now();
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
     * @return sentTime of the request.
     */
    public LocalDateTime getSentTime() {
        return sentTime;
    }

    /**
     * sets the time of the request sending to the new value.
     *
     * @param sentTime new value of the time sent.
     */
    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }
}
