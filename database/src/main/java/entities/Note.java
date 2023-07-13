package entities;


import javax.persistence.*;
import java.util.Date;


/**
 * Represents a note entity.
 */
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    private User from;

    @Column(name = "to_id")
    private User to;

    @Column(name = "message")
    private String message;

    @Column(name = "date_time_column")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    /**
     * Default constructor.
     */
    public Note() {
    }


    public Note(User from, User to, String message, Date dateTime) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.dateTime = dateTime;
    }

    /**
     * Gets the ID of the note.
     *
     * @return the ID of the note
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the note.
     *
     * @param id the ID of the note
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the sender of the note.
     *
     * @return the sender of the note
     */
    public User getSender() {
        return from;
    }

    /**
     * Sets the sender of the note.
     *
     * @param sender the from of the note
     */
    public void setSender(User sender) {
        this.from = sender;
    }


    /**
     * Gets the receiver of the note.
     *
     * @return the receiver of the note
     */
    public User getReceiver() {
        return to;
    }

    /**
     * Sets the receiver of the note.
     *
     * @param receiver the receiver of the note
     */
    public void setReceriver(User receiver) {
        this.to = receiver;
    }


    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message of the note
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public Date getTime() {
        return dateTime;
    }

    /**
     * Sets the time.
     *
     * @param dateTime the dateTime of the note
     */
    public void setTime(Date dateTime) {
        this.dateTime = dateTime;
    }

}
