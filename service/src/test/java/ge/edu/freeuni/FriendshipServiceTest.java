package ge.edu.freeuni;

import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.FriendRequestModel;
import ge.edu.freeuni.models.FriendshipModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.requests.CreateFriendshipRequest;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.services.FriendshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
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

        CreateFriendshipRequest request = new CreateFriendshipRequest(1L, 2L);
        ServiceActionResponse response = friendshipService.sendFriendshipRequest(request);

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

        ServiceActionResponse response = friendshipService.rejectFriendshipRequest(2L, 1L);

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
        List<Friendship> friendships = new ArrayList<>();
        friendships.add(new Friendship(user, new User()));
        friendships.add(new Friendship(user, new User()));
        when(friendshipDAO.getByFields(any(), any())).thenReturn(friendships);

        List<FriendshipModel> result = friendshipService.getAllFriends(1L).getAllFriends();
        assertEquals(2, result.size());
    }
}
