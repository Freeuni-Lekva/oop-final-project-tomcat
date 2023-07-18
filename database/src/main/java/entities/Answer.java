package entities;

import javax.persistence.*;

import javax.persistence.Entity;

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

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "is_correct", nullable = false)
    private String accuracy;

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
    public String getAccuracy() {
        return accuracy;
    }

    /**
     * Sets the accuracy of the answer.
     *
     * @param accuracy - sets if the answer is right or not: "yes" or "no".
     */
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}
