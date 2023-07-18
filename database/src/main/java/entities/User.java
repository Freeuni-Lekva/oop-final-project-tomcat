package entities;

import services.PasswordUtils;

import javax.persistence.*;
import java.util.Objects;

/**
 * Represents a single user entity.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "salt", nullable = false)
    private String salt;

    // Constructors

    /**
     * default constructor
     */
    public User() {
    }

    /**
     * Constructs a new User with the given username, first name, last name, and password.
     */
    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = PasswordUtils.generatePasswordSalt();
        this.passwordHash = PasswordUtils.getPasswordCode(password,this.salt);
    }

    // Getters and Setters


    /**
     * returns user's unique id.
     *
     * @return user's id.
     */
    public Long getId() {
        return id;
    }

    /**
     * sets user's id.
     *
     * @param id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * returns user's username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets new value of the user's username.
     *
     * @param username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * returns the first name of the user.
     *
     * @return user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the user's first name to the received one.
     *
     * @param firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * returns the last name of the user.
     *
     * @return lastName of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the user's last name to the new one.
     *
     * @param lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * returns the password hash of the user.
     *
     * @return passwordHash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * sets the user's password hash to the new one.
     *
     * @param passwordHash to set.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * returns the salt of the user.
     *
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * sets the salt value to the new one.
     *
     * @param salt to set.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, passwordHash, salt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
