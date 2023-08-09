package ge.edu.freeuni;


import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.services.UserService;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.responses.ServiceActionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

public class UserServiceTest {

    private UserService userService;
    private DAO<User> userDAO;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        userDAO = mock(DAO.class);
        userService.setUserDAO(userDAO);
    }

    @Test
    public void testAddAccount() {
        UserModel newUser = new UserModel(null, "newuser", "John", "Doe", "password");
        when(userDAO.getByField(eq("username"), eq(newUser.getUsername()))).thenReturn(new ArrayList<>());

        ServiceActionResponse response = userService.addAccount(newUser);

        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessage());
        verify(userDAO).create(any(User.class));
    }

    @Test
    public void testAddAccountWithExistingUsername() {
        UserModel existingUser = new UserModel(1L, "existinguser", "Jane", "Smith", "password");
        when(userDAO.getByField(eq("username"), eq(existingUser.getUsername())))
                .thenReturn(Collections.singletonList(new User(
                        existingUser.getUsername(),
                        existingUser.getFirstname(),
                        existingUser.getLastname(),
                        existingUser.getPassword()
                )));

        ServiceActionResponse response = userService.addAccount(existingUser);

        assertFalse(response.isSuccess());
        assertEquals("Username is occupied", response.getErrorMessage());
        verify(userDAO, never()).create(any(User.class));
    }

    @Test
    public void testAddAccountWithEmptyCredentials() {
        UserModel newUser = new UserModel(null, "", "John", "Doe", "");
        ServiceActionResponse response = userService.addAccount(newUser);

        assertFalse(response.isSuccess());
        assertEquals("Incorrect credentials", response.getErrorMessage());
        verify(userDAO, never()).create(any(User.class));
    }
}

