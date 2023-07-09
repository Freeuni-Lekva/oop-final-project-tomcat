package entities;

import javax.persistence.*;

/**
 * Represents a single quiz game entity.
 */
@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    /**
     * Default constructor.
     */
    public Quiz() {
    }

    /**
     * Constructs a Quiz object with the specified name and owner.
     *
     * @param name  the name of the quiz
     * @param owner the owner of the quiz
     */
    public Quiz(String name, User owner) {
        this.name = name;
        this.owner = owner;
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
}
