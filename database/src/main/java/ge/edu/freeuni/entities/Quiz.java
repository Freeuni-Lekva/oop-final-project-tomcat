package ge.edu.freeuni.entities;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Question> questions;

    @Column(name = "creation_timestamp")
    private Long creationTimestamp;
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
    public Quiz(String name, String description, User owner, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.questions = questions;
        this.creationTimestamp = System.currentTimeMillis();
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
