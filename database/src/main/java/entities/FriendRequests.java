package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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
     * Cpnstructor that receives 2 users:sender and recipient and creates this request.
     *
     * @param senderUser - the user sending the request
     * @param recipientUser - the user the request is sent to
     */
    public FriendRequests(User senderUser, User recipientUser) {
        this.senderUser = senderUser;
        this.recipientUser = recipientUser;
        this.sentTime = LocalDateTime.now();
    }

    // getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
