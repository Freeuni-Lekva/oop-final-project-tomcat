package entities;

import enums.QuestionType;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a question entity.
 */
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id", nullable = false)
    private Quiz quiz;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "points")
    private int points;

    /**
     * Default constructor.
     */
    public Question() {
    }

    /**
     * Constructs a Question object with the specified quiz, question type, answers, correct answers, image URL, and points.
     *
     * @param quiz          the quiz to which the question belongs
     * @param questionType  the type of the question
     * @param answers       all the answers associated with this question
     * @param imageUrl      the image URL associated with the question (for picture-response type)
     * @param points        the score player gets if they guess the question correctly
     */
    public Question(Quiz quiz, QuestionType questionType,List<Answer> answers,
                    String imageUrl, int points) {
        this.quiz = quiz;
        this.questionType = questionType;
        this.answers = answers;
        this.imageUrl = imageUrl;
        this.points = points;
    }

    /**
     * Gets the ID of the question.
     *
     * @return the ID of the question
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the question.
     *
     * @param id the ID of the question
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the quiz to which the question belongs.
     *
     * @return the quiz to which the question belongs
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Sets the quiz to which the question belongs.
     *
     * @param quiz the quiz to which the question belongs
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Gets the type of the question.
     *
     * @return the type of the question
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    /**
     * Sets the type of the question.
     *
     * @param questionType the type of the question
     */
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    /**
     * Gets the image URL associated with the question.
     *
     * @return the image URL associated with the question
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL associated with the question.
     *
     * @param imageUrl the image URL associated with the question
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    /**
     * Gets the list of answers associated with the question.
     *
     * @return the list of answers
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Sets the list of answers associated with the question.
     *
     * @param answers the list of answers
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
