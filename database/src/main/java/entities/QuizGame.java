package entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Represents a quiz entity.
 */
@Entity
@Table(name = "quiz_games")
public class QuizGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private User player;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "start_timestamp")
    private Long startTimestamp;

    @Column(name = "finish_timestamp")
    private Long finishTimestamp;

    /**
     * Default constructor.
     */
    public QuizGame() {
    }

    /**
     * Constructs a QuizGame object with the specified quiz, player, score, timestamp started, and timestamp finished.
     *
     * @param quiz            the quiz played in the game
     * @param player          the player who played the game
     * @param score           the score achieved in the game
     * @param startTimestamp the timestamp when the game started
     * @param finishTimestamp the timestamp when the game finished
     */
    public QuizGame(Quiz quiz, User player, int score, Long startTimestamp, Long finishTimestamp) {
        this.quiz = quiz;
        this.player = player;
        this.score = score;
        this.startTimestamp = startTimestamp;
        this.finishTimestamp = finishTimestamp;
    }

    /**
     * Gets the ID of the quiz game.
     *
     * @return the ID of the quiz game
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the quiz game.
     *
     * @param id the ID of the quiz game
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the quiz played in the game.
     *
     * @return the quiz played in the game
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Sets the quiz played in the game.
     *
     * @param quiz the quiz played in the game
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Gets the player who played the game.
     *
     * @return the player who played the game
     */
    public User getPlayer() {
        return player;
    }

    /**
     * Sets the player who played the game.
     *
     * @param player the player who played the game
     */
    public void setPlayer(User player) {
        this.player = player;
    }

    /**
     * Gets the score achieved in the game.
     *
     * @return the score achieved in the game
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score achieved in the game.
     *
     * @param score the score achieved in the game
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the timestamp when the game started.
     *
     * @return the timestamp when the game started
     */
    public Long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Sets the timestamp when the game started.
     *
     * @param startTimestamp the timestamp when the game started
     */
    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    /**
     * Gets the timestamp when the game finished.
     *
     * @return the timestamp when the game finished
     */
    public Long getFinishTimestamp() {
        return finishTimestamp;
    }

    /**
     * Sets the timestamp when the game finished.
     *
     * @param finishTimestamp the timestamp when the game finished
     */
    public void setFinishTimestamp(Long finishTimestamp) {
        this.finishTimestamp = finishTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizGame quizGame = (QuizGame) o;
        return score == quizGame.score && Objects.equals(id, quizGame.id) && Objects.equals(quiz, quizGame.quiz) && Objects.equals(player, quizGame.player) && Objects.equals(startTimestamp, quizGame.startTimestamp) && Objects.equals(finishTimestamp, quizGame.finishTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quiz, player, score, startTimestamp, finishTimestamp);
    }

    @Override
    public String toString() {
        return "QuizGame{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", player=" + player +
                ", score=" + score +
                ", startTimestamp=" + startTimestamp +
                ", finishTimestamp=" + finishTimestamp +
                '}';
    }
}
