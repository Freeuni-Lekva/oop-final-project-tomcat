package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a quiz entity.
 */
@Entity
@Table(name = "quiz_games")
public class QuizGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private User player;

    @Column(name = "score")
    private int score;

    @Column(name = "datetime_started")
    private LocalDateTime datetimeStarted;

    @Column(name = "datetime_finished")
    private LocalDateTime datetimeFinished;

    /**
     * Default constructor.
     */
    public QuizGame() {
    }

    /**
     * Constructs a QuizGame object with the specified quiz, player, score, datetime started, and datetime finished.
     *
     * @param quiz            the quiz played in the game
     * @param player          the player who played the game
     * @param score           the score achieved in the game
     * @param datetimeStarted the datetime when the game started
     * @param datetimeFinished the datetime when the game finished
     */
    public QuizGame(Quiz quiz, User player, int score, LocalDateTime datetimeStarted, LocalDateTime datetimeFinished) {
        this.quiz = quiz;
        this.player = player;
        this.score = score;
        this.datetimeStarted = datetimeStarted;
        this.datetimeFinished = datetimeFinished;
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
     * Gets the datetime when the game started.
     *
     * @return the datetime when the game started
     */
    public LocalDateTime getDatetimeStarted() {
        return datetimeStarted;
    }

    /**
     * Sets the datetime when the game started.
     *
     * @param datetimeStarted the datetime when the game started
     */
    public void setDatetimeStarted(LocalDateTime datetimeStarted) {
        this.datetimeStarted = datetimeStarted;
    }

    /**
     * Gets the datetime when the game finished.
     *
     * @return the datetime when the game finished
     */
    public LocalDateTime getDatetimeFinished() {
        return datetimeFinished;
    }

    /**
     * Sets the datetime when the game finished.
     *
     * @param datetimeFinished the datetime when the game finished
     */
    public void setDatetimeFinished(LocalDateTime datetimeFinished) {
        this.datetimeFinished = datetimeFinished;
    }
}
