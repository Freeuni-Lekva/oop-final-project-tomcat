package ge.edu.freeuni.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single quiz game entity.
 */
@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Question> questions;

    @Column(name = "creation_timestamp")
    private Long creationTimestamp;

    @Column(name = "randomize_questions")
    private String randomizeQuestions;

    @Column(name = "one_page")
    private String onePage;

    @Column(name = "immediate_correction")
    private String immediateCorrection;

    @Column(name = "practice_mode")
    private String practiceMode;
    /**
     * Default constructor.
     */
    public Quiz() {
    }

    /**
     * Constructs a Quiz object with the specified name and owner.
     *
     * @param name      the name of the quiz
     * @param owner     the owner of the quiz
     * @param questions the list of all questions of the quiz
     */
    public Quiz(String name, String description, User owner, List<Question> questions, String randomizeQuestions, String onePage, String immediateCorrection, String practiceMode) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.questions = questions;
        this.randomizeQuestions = randomizeQuestions;
        this.onePage = onePage;
        this.immediateCorrection = immediateCorrection;
        this.practiceMode = practiceMode;
        this.creationTimestamp = System.currentTimeMillis() / 1000L;
    }

    /**
     * Gets the ID of the quiz.
     *
     * @return the ID of the quiz
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the quiz.
     *
     * @param id the ID of the quiz
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the quiz.
     *
     * @return the name of the quiz
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the quiz.
     *
     * @param name the name of the quiz
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the quiz.
     *
     * @return the description of the quiz
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the quiz.
     *
     * @param description the description of the quiz
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the owner of the quiz.
     *
     * @return the owner of the quiz
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the quiz.
     *
     * @param owner the owner of the quiz
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the questions of the quiz.
     *
     * @return the questions of the quiz
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Sets the questions of the quiz.
     *
     * @param questions the questions of the quiz
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getRandomizeQuestions() {
        return randomizeQuestions;
    }

    public void setRandomizeQuestions(String randomizeQuestions) {
        this.randomizeQuestions = randomizeQuestions;
    }

    public String getOnePage() {
        return onePage;
    }

    public void setOnePage(String onePage) {
        this.onePage = onePage;
    }

    public String getImmediateCorrection() {
        return immediateCorrection;
    }

    public void setImmediateCorrection(String immediateCorrection) {
        this.immediateCorrection = immediateCorrection;
    }

    public String getPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(String practiceMode) {
        this.practiceMode = practiceMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (!Objects.equals(id, quiz.id)) return false;
        if (!Objects.equals(name, quiz.name)) return false;
        if (!Objects.equals(description, quiz.description)) return false;
        if (!Objects.equals(owner, quiz.owner)) return false;
        if (!Objects.equals(questions, quiz.questions)) return false;
        return Objects.equals(creationTimestamp, quiz.creationTimestamp);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (creationTimestamp != null ? creationTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", questions=" + questions +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
