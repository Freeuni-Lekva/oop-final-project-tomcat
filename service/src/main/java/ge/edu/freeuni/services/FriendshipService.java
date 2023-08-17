package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.FriendRequestModel;
import ge.edu.freeuni.models.FriendshipModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.requests.CreateFriendshipRequest;
import ge.edu.freeuni.responses.AllFriendRequestsResponse;
import ge.edu.freeuni.responses.AllFriendshipsResponse;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FriendshipService {
    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
    private DAO<Friendship> friendshipDAO = DAOFactory.getInstance().getDAO(Friendship.class);

    private DAO<FriendRequest> friendRequestDAO = DAOFactory.getInstance().getDAO(FriendRequest.class);

    public ServiceActionResponse sendFriendshipRequest(CreateFriendshipRequest request) {
        try {
            User sender = userDAO.read(request.getSenderId());
            if (sender == null) {
                return new ServiceActionResponse(false, "Sender user doesn't exist");
            }

            User recipient = userDAO.read(request.getSenderId());
            if (recipient == null) {
                return new ServiceActionResponse(false, "Recipient user doesn't exist");
            }

            friendRequestDAO.create(new FriendRequest(sender, recipient));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ServiceActionResponse(false, "Error while sending a request. Try again later");
        }
        return new ServiceActionResponse(true, null);
    }

    public ServiceActionResponse approveFriendshipRequest(Long recipientId, Long requestId) {
        try {
            FriendRequest request = friendRequestDAO.read(requestId);
            if (request == null) {
                return new ServiceActionResponse(false, "Friendship request doesn't exist");
            }

            if (!Objects.equals(recipientId, request.getRecipientUser().getId())) {
                return new ServiceActionResponse(false, "Approver and recipient user don't match");
            }

            friendshipDAO.create(new Friendship(request.getSenderUser(), request.getRecipientUser()));
            friendRequestDAO.delete(requestId);
        } catch (RuntimeException e) {
            return new ServiceActionResponse(false, "Error while approving the request. Try again later");
        }
        return new ServiceActionResponse(true, null);
    }

    public ServiceActionResponse rejectFriendshipRequest(Long recipientId, Long requestId) {
        try {
            FriendRequest request = friendRequestDAO.read(requestId);
            if (request == null) {
                return new ServiceActionResponse(false, "Friendship request doesn't exist");
            }

            if (!Objects.equals(recipientId, request.getRecipientUser().getId())) {
                return new ServiceActionResponse(false, "Rejector and recipient user don't match");
            }

            friendRequestDAO.delete(requestId);
        } catch (RuntimeException e) {
            return new ServiceActionResponse(false, "Error while rejecting the request. Try again later");
        }
        return new ServiceActionResponse(true, null);
    }

    public AllFriendRequestsResponse getAllFriendRequests(Long userId) {
        try {
            List<FriendRequestModel> requests = friendRequestDAO.getByField("recipient_user", userId).stream()
                    .map(EntityToModelBridge::toFriendRequestModel)
                    .collect(Collectors.toList());
            return new AllFriendRequestsResponse(true, null, requests);
        } catch (RuntimeException e) {
            return new AllFriendRequestsResponse(false, "Error while getting requests. Try again later", null);
        }

    }

    public AllFriendshipsResponse getAllFriends(Long userId) {
        String[] fieldNames = {"first_user", "second_user"};
        Serializable[] values = {userId, userId};
        try {
            List<FriendshipModel> friends = friendshipDAO.getByFields(fieldNames, values, false).stream()
                    .map(EntityToModelBridge::toFriendshipModel)
                    .collect(Collectors.toList());
            return new AllFriendshipsResponse(true, null, friends);
        } catch (RuntimeException e) {
            return new AllFriendshipsResponse(false, "Error while getting friends. Try again later", null);
        }
    }

    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public void setFriendshipDAO(DAO<Friendship> friendshipDAO) {
        this.friendshipDAO = friendshipDAO;
    }

    public void setFriendRequestDAO(DAO<FriendRequest> friendRequestDAO) {
        this.friendRequestDAO = friendRequestDAO;
    }
}
