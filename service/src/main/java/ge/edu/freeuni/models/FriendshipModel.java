package ge.edu.freeuni.models;

import java.util.Objects;

public class FriendshipModel {
    private final Long id;
    private UserModel user1;
    private UserModel user2;

    public FriendshipModel(Long id) {
        this.id = id;
    }

    public FriendshipModel(Long id, UserModel user1, UserModel user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Long getId() {
        return id;
    }

    public UserModel getUser1() {
        return user1;
    }

    public void setUser1(UserModel user1) {
        this.user1 = user1;
    }

    public UserModel getUser2() {
        return user2;
    }

    public void setUser2(UserModel user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendshipModel that = (FriendshipModel) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(user1, that.user1)) return false;
        return Objects.equals(user2, that.user2);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user1 != null ? user1.hashCode() : 0);
        result = 31 * result + (user2 != null ? user2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendshipModel{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }
}
