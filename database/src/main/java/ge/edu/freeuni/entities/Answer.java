package ge.edu.freeuni.entities;

import ge.edu.freeuni.enums.Bool;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * Represents an answer entity.
 */
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_correct", nullable = false)
    private Bool accuracy;

    @Column(name = "points")
    private Integer points;

    public Answer() {
    }

    public Answer(Question question, String answer, Bool accuracy, Integer points) {
        this.question = question;
        this.answer = answer;
        this.accuracy = accuracy;
        this.points = points;
    }

    /**
     * Sets the ID of the answer.
     *
     * @param id - the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the id of the answer.
     *
     * @return the id of the answer.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the associated question for the answer.
     *
     * @return the associated question.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Sets the associated question for the answer.
     *
     * @param question - the associated question to set.
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Returns the answer text.
     *
     * @return the answer text.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer text.
     *
     * @param answer - the answer text to set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Returns whether this answer is right or wrong.
     *
     * @return if the answer is right or wrong : "yes" or "no".
     */
    public Bool getAccuracy() {
        return accuracy;
    }

    /**
     * Sets the accuracy of the answer.
     *
     * @param accuracy - sets if the answer is right or not: "yes" or "no".
     */
    public void setAccuracy(Bool accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Gets the score player gets if they guess the question correctly.
     *
     * @return the score player gets if they guess the question correctly
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the score player gets if they guess the question correctly.
     *
     * @param points the score player gets if they guess the question correctly
     */
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        if (!Objects.equals(id, answer1.id)) return false;
        if (!Objects.equals(question, answer1.question)) return false;
        if (!Objects.equals(answer, answer1.answer)) return false;
        if (!Objects.equals(accuracy, answer1.accuracy)) return false;
        return Objects.equals(points, answer1.points);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (accuracy != null ? accuracy.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question=" + question.getId() +
                ", answer='" + answer + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", points=" + points +
                '}';
    }
}
