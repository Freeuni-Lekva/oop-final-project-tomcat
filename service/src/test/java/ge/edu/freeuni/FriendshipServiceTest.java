package ge.edu.freeuni;

import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.FriendRequestModel;
import ge.edu.freeuni.models.FriendshipModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.services.FriendshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class FriendshipServiceTest {

    private FriendshipService friendshipService;
    private DAO<User> userDAO;
    private DAO<Friendship> friendshipDAO;
    private DAO<FriendRequest> friendRequestDAO;

    @BeforeEach
    public void setUp() {
        userDAO = mock(DAO.class);
        friendshipDAO = mock(DAO.class);
        friendRequestDAO = mock(DAO.class);

        friendshipService = new FriendshipService();
        friendshipService.setUserDAO(userDAO);
        friendshipService.setFriendshipDAO(friendshipDAO);
        friendshipService.setFriendRequestDAO(friendRequestDAO);
    }

    @Test
    public void testSendFriendshipRequest() {
        User sender = new User();
        User recipient = new User();
        when(userDAO.read(1L)).thenReturn(sender);
        when(userDAO.read(2L)).thenReturn(recipient);

        ServiceActionResponse response = friendshipService.sendFriendshipRequest(1L, 2L);

        verify(friendRequestDAO, times(1)).create(any(FriendRequest.class));
        assertTrue(response.isSuccess());
    }

    @Test
    public void testApproveFriendshipRequest() {
        User sender = new User();
        User recipient = mock(User.class);
        FriendRequest request = new FriendRequest(sender, recipient);
        when(friendRequestDAO.read(anyLong())).thenReturn(request);
        when(userDAO.read(2L)).thenReturn(recipient);
        when(recipient.getId()).thenReturn(2L);

        ServiceActionResponse response = friendshipService.approveFriendshipRequest(2L, 1L);

        verify(friendshipDAO, times(1)).create(any(Friendship.class));
        verify(friendRequestDAO, times(1)).delete(1L);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testRejectFriendshipRequest() {
        User recipient = mock(User.class);
        FriendRequest request = mock(FriendRequest.class);
        when(friendRequestDAO.read(anyLong())).thenReturn(request);
        when(userDAO.read(2L)).thenReturn(recipient);
        when(recipient.getId()).thenReturn(2L);
        when(request.getId()).thenReturn(1L);
        when(request.getRecipientUser()).thenReturn(recipient);

        ServiceActionResponse response = friendshipService.removeFriendshipRequest(2L, 1L);

        verify(friendRequestDAO, times(1)).delete(1L);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testGetAllFriendRequests() {
        List<FriendRequest> friendRequests = new ArrayList<>();
        User recipient = new User();
        friendRequests.add(new FriendRequest(new User(), recipient));
        friendRequests.add(new FriendRequest(new User(), recipient));
        when(friendRequestDAO.getByField(eq("recipient_user"), anyLong())).thenReturn(friendRequests);

        List<FriendRequestModel> result = friendshipService.getAllFriendRequests(2L).getAllFriendRequests();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllFriends() {
        User user = new User();
        user.setId(1L);
        List<Friendship> friendships = new ArrayList<>();
        Friendship friendship1 = new Friendship(user, new User());
        Friendship friendship2 = new Friendship(user, new User());
        friendship1.setId(1L);
        friendship2.setId(2L);
        friendships.add(friendship1);
        friendships.add(friendship2);

        when(friendshipDAO.getAll()).thenReturn(friendships);
        friendshipService = new FriendshipService(userDAO, friendshipDAO, friendRequestDAO);

        List<FriendshipModel> result = friendshipService.getAllFriends(1L).getAllFriends();
        assertEquals(2, result.size());
    }
}
