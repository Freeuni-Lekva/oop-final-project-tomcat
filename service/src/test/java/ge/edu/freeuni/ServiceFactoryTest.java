package ge.edu.freeuni;

import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.services.FriendshipService;
import ge.edu.freeuni.services.QuizService;
import ge.edu.freeuni.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceFactoryTest {

    private ServiceFactory serviceFactory;

    @BeforeEach
    public void setup() {
        serviceFactory = ServiceFactory.getInstance();
    }

    @Test
    public void testGetUserService() {
        UserService userService1 = serviceFactory.getService(UserService.class);
        UserService userService2 = serviceFactory.getService(UserService.class);

        assertNotNull(userService1);
        assertNotNull(userService2);
        assertSame(userService1, userService2);
    }

    @Test
    public void testGetFriendshipService() {
        FriendshipService friendshipService1 = serviceFactory.getService(FriendshipService.class);
        FriendshipService friendshipService2 = serviceFactory.getService(FriendshipService.class);

        assertNotNull(friendshipService1);
        assertNotNull(friendshipService2);
        assertSame(friendshipService1, friendshipService2); // Same instance should be returned
    }

    @Test
    public void testGetQuizService() {
        QuizService quizService1 = serviceFactory.getService(QuizService.class);
        QuizService quizService2 = serviceFactory.getService(QuizService.class);

        assertNotNull(quizService1);
        assertNotNull(quizService2);
        assertSame(quizService1, quizService2); // Same instance should be returned
    }

    @Test
    public void testGetUnknownService() {
        assertThrows(RuntimeException.class, () -> serviceFactory.getService(UnknownService.class));
    }

    @Test
    public void testCreateMultipleServiceInstances() {
        int initialSize = serviceFactory.getNumberOfServices();

        serviceFactory.getService(UserService.class);
        serviceFactory.getService(UserService.class);

        assertEquals(initialSize + 1, serviceFactory.getNumberOfServices());
    }

    @Test
    public void testGetInstance() {
        ServiceFactory instance1 = ServiceFactory.getInstance();
        ServiceFactory instance2 = ServiceFactory.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2); // Same instance should be returned
    }

    private static class UnknownService {
        // Dummy class for testing an unknown service
    }
}