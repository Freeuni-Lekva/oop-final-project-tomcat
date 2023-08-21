package ge.edu.freeuni.entities;

import ge.edu.freeuni.enums.QuestionType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    @Column(name = "question", nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Answer> answers;

    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Default constructor.
     */
    public Question() {
    }

    /**
     * Constructs a Question object with the specified quiz, question type, answers, correct answers, image URL, and points.
     *
     * @param quiz         the quiz to which the question belongs
     * @param questionType the type of the question
     * @param answers      all the answers associated with this question
     * @param imageUrl     the image URL associated with the question (for picture-response type)
     */
    public Question(Quiz quiz, String question, QuestionType questionType, List<Answer> answers,
                    String imageUrl) {
        this.quiz = quiz;
        this.question = question;
        this.questionType = questionType;
        this.answers = answers;
        this.imageUrl = imageUrl;
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
     * Gets the question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question.
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (!Objects.equals(id, question1.id)) return false;
        if (!Objects.equals(quiz, question1.quiz)) return false;
        if (!Objects.equals(question, question1.question)) return false;
        if (questionType != question1.questionType) return false;
        if (!Objects.equals(answers, question1.answers)) return false;
        return Objects.equals(imageUrl, question1.imageUrl);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quiz != null ? quiz.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (questionType != null ? questionType.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quiz=" + quiz.getId() +
                ", question='" + question + '\'' +
                ", questionType=" + questionType +
                ", answers=" + answers +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
