package entities;

import javax.persistence.*;

/**
 * Represents a friends' entity.
 */
@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_user" , referencedColumnName = "id" , nullable = false)
    private User firstUser;

    @ManyToOne
    @JoinColumn(name = "second_user" , referencedColumnName = "id" , nullable = false)
    private User secondUser;

    // constructors

    /**
     * default constructor
     */
    public Friends(){
    }

    /**
     * all params constructor. receives two user's ids and creates their friendship
     * @param firstUser - first friend
     * @param secondUser - second friend
     */
    public Friends(User firstUser, User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    // getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }
}
