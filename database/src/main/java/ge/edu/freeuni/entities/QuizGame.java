package ge.edu.freeuni.entities;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private User player;

    @Column(name = "score", nullable = false)
    private Integer score;
    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

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
     * @param startTimestamp  the timestamp when the game started
     * @param finishTimestamp the timestamp when the game finished
     */
    public QuizGame(Quiz quiz, User player, Integer score, Integer maxScore, Long startTimestamp, Long finishTimestamp) {
        this.quiz = quiz;
        this.player = player;
        this.score = score;
        this.maxScore = maxScore;
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
    public Integer getScore() {
        return score;
    }

    /**
     * Sets the score achieved in the game.
     *
     * @param score the score achieved in the game
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
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

        if (!Objects.equals(id, quizGame.id)) return false;
        if (!Objects.equals(quiz, quizGame.quiz)) return false;
        if (!Objects.equals(player, quizGame.player)) return false;
        if (!Objects.equals(score, quizGame.score)) return false;
        if (!Objects.equals(maxScore, quizGame.maxScore)) return false;
        if (!Objects.equals(startTimestamp, quizGame.startTimestamp))
            return false;
        return Objects.equals(finishTimestamp, quizGame.finishTimestamp);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quiz != null ? quiz.hashCode() : 0);
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (maxScore != null ? maxScore.hashCode() : 0);
        result = 31 * result + (startTimestamp != null ? startTimestamp.hashCode() : 0);
        result = 31 * result + (finishTimestamp != null ? finishTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuizGame{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", player=" + player +
                ", score=" + score +
                ", maxScore=" + maxScore +
                ", startTimestamp=" + startTimestamp +
                ", finishTimestamp=" + finishTimestamp +
                '}';
    }
}
