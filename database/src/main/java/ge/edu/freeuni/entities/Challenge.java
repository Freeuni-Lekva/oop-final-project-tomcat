package ge.edu.freeuni.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_id", referencedColumnName = "id", nullable = false)
    private User from;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_id", referencedColumnName = "id", nullable = false)
    private User to;

    @Column(name = "sender_best_score")
    private Integer bestScore;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    /**
     * Default constructor.
     */
    public Challenge() {

    }


    public Challenge(Quiz quiz, User from, User to, Integer bestScore) {
        this.quiz = quiz;
        this.from = from;
        this.to = to;
        this.bestScore = bestScore;
        this.timestamp = System.currentTimeMillis() / 1000L;
    }

    /**
     * Gets the ID of the challenge.
     *
     * @return the ID of the challenge
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the challenge.
     *
     * @param id the ID of the challenge
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the quiz
     *
     * @return the quiz of the challenge
     */
    public Quiz getQuiz() {
        return quiz;
    }


    /**
     * Sets the sender of the challenge.
     *
     * @param quiz the from of the challenge
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    /**
     * Gets the sender of the challenge.
     *
     * @return the sender of the challenge
     */
    public User getSender() {
        return from;
    }

    /**
     * Sets the sender of the challenge.
     *
     * @param sender the from of the challenge
     */
    public void setSender(User sender) {
        this.from = sender;
    }


    /**
     * Gets the receiver of the challenge.
     *
     * @return the receiver of the challenge
     */
    public User getReceiver() {
        return to;
    }

    /**
     * Sets the receiver of the challenge.
     *
     * @param receiver the receiver of the challenge
     */
    public void setReceiver(User receiver) {
        this.to = receiver;
    }

    /**
     * Gets the sender's best score
     *
     * @return the bestScore of the challenge
     */
    public Integer getBestScore() {
        return bestScore;
    }


    /**
     * Sets the sender's best score of the challenge.
     *
     * @param bestScore the bestScore of the challenge
     */
    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(bestScore, challenge.bestScore) && Objects.equals(id, challenge.id)
                && Objects.equals(quiz, challenge.quiz) && Objects.equals(from, challenge.from)
                && Objects.equals(to, challenge.to) && Objects.equals(timestamp, challenge.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quiz, from, to, bestScore);
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", from=" + from +
                ", to=" + to +
                ", bestScore=" + bestScore +
                '}';
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
