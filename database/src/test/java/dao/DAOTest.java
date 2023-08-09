package dao;

import ge.edu.freeuni.entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {
    @AfterAll
    public static void cleanup() {
        File dbFile = new File("quizdb.db");
        if (dbFile.exists()) {
            boolean deleted = dbFile.delete();
            assertTrue(deleted);
        }
    }

    @Test
    public void testUser() {
        DAO<User> dao = DAOFactory.getInstance().getDAO(User.class);
        dao.clearTable();

        User user;

        // test create and read
        dao.create(new User("username", "first name", "last name", "password"));
        user = dao.read(1L);
        assertEquals("username", user.getUsername());

        // test update
        user.setFirstName("first name 1");
        dao.update(user);
        user = dao.read(1L);
        assertEquals("username", user.getUsername());
        assertEquals("first name 1", user.getFirstName());

        // test delete
        dao.delete(1L);
        user = dao.read(1L);
        assertNull(user);

        // test getAll
        dao.create(new User("username1", "first name", "last name", "password"));
        dao.create(new User("username2", "first name", "last name", "password"));
        dao.create(new User("username3", "first name", "last name", "password"));
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
        users.forEach(
                user1 -> assertEquals("first name", user1.getFirstName()));
    }
}
