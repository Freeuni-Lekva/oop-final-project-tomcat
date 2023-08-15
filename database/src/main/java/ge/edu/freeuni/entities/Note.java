package ge.edu.freeuni.entities;


import javax.persistence.*;
import java.util.Objects;


/**
 * Represents a note entity.
 */
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_id", referencedColumnName = "id", nullable = false)
    private User from;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_id", referencedColumnName = "id", nullable = false)
    private User to;

    @Column(name = "subject")
    private String subject;
    @Column(name = "message")
    private String message;

    @Column(name = "timestamp")
    private Long timestamp;

    /**
     * Default constructor.
     */
    public Note() {

    }

    public Note(User from, User to, String subject, String message) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
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
    public User getFrom() {
        return from;
    }

    /**
     * Sets the sender of the note.
     *
     * @param sender the from of the note
     */
    public void setFrom(User sender) {
        this.from = sender;
    }


    /**
     * Gets the receiver of the note.
     *
     * @return the receiver of the note
     */
    public User getTo() {
        return to;
    }

    /**
     * Sets the receiver of the note.
     *
     * @param receiver the receiver of the note
     */
    public void setTo(User receiver) {
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
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the time.
     *
     * @param timestamp the timestamp of the note
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(from, note.from) && Objects.equals(to, note.to) && Objects.equals(message, note.message) && Objects.equals(timestamp, note.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, message, timestamp);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
