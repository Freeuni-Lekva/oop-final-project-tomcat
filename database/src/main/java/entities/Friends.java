package entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Represents a friends' entity.
 */
@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

    /**
     * sets the friendship id to the received value
     * @param id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * returns the value of the friendship's unique id
     * @return the id of the friendship
     */
    public Long getId() {
        return id;
    }

    /**
     * returns the first friend
     * @return firstUser the first friend
     */
    public User getFirstUser() {
        return firstUser;
    }

    /**
     * sets the first friend's value to the received one.
     * @param firstUser - first friend in this friendship
     */
    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    /**
     * returns the second friend
     * @return secondUser - the second friend
     */
    public User getSecondUser() {
        return secondUser;
    }

    /**
     * sets the second friend's value to the received one.
     * @param secondUser - second friend in this friendship
     */
    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friends friends = (Friends) o;
        return Objects.equals(id, friends.id) && Objects.equals(firstUser, friends.firstUser) && Objects.equals(secondUser, friends.secondUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstUser, secondUser);
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                '}';
    }
}
