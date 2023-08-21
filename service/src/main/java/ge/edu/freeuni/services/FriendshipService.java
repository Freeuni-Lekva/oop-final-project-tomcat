package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.FriendRequestModel;
import ge.edu.freeuni.models.FriendshipModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.AllFriendRequestsResponse;
import ge.edu.freeuni.responses.AllFriendshipsResponse;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FriendshipService {
    private DAO<User> userDAO;
    private DAO<Friendship> friendshipDAO;

    private DAO<FriendRequest> friendRequestDAO;

    private final Map<Serializable, FriendshipModel> allFriendships;

    public FriendshipService() {
        this.userDAO = DAOFactory.getInstance().getDAO(User.class);
        this.friendshipDAO = DAOFactory.getInstance().getDAO(Friendship.class);
        this.friendRequestDAO = DAOFactory.getInstance().getDAO(FriendRequest.class);
        this.allFriendships = getAllFriendships();
    }

    public FriendshipService(DAO<User> userDAO, DAO<Friendship> friendshipDAO, DAO<FriendRequest> friendRequestDAO) {
        this.userDAO = userDAO;
        this.friendshipDAO = friendshipDAO;
        this.friendRequestDAO = friendRequestDAO;
        this.allFriendships = getAllFriendships();
    }

    private Map<Serializable, FriendshipModel> getAllFriendships() {
        try {
            return friendshipDAO.getAll().stream()
                    .map(EntityToModelBridge::toFriendshipModel)
                    .collect(Collectors.toMap(FriendshipModel::getId, Function.identity()));
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public ServiceActionResponse sendFriendshipRequest(Long senderId, Long receiverId) {
        try {
            User sender = userDAO.read(senderId);
            if (sender == null) {
                return new ServiceActionResponse(false, "Sender user doesn't exist");
            }

            User recipient = userDAO.read(receiverId);
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

            Friendship friendship = new Friendship(request.getSenderUser(), request.getRecipientUser());
            Serializable id = friendshipDAO.create(friendship);
            allFriendships.put(id, EntityToModelBridge.toFriendshipModel(friendship));
            friendRequestDAO.delete(requestId);
        } catch (RuntimeException e) {
            return new ServiceActionResponse(false, "Error while approving the request. Try again later");
        }
        return new ServiceActionResponse(true, null);
    }

    public ServiceActionResponse removeFriendshipRequest(Long initiatorId, Long requestId) {
        try {
            FriendRequest request = friendRequestDAO.read(requestId);
            if (request == null) {
                return new ServiceActionResponse(false, "Friendship request doesn't exist");
            }

            if (!Objects.equals(initiatorId, request.getRecipientUser().getId()) && !Objects.equals(initiatorId, request.getSenderUser().getId())) {
                return new ServiceActionResponse(false, "You don't have permission to reject/cancel the request");
            }

            friendRequestDAO.delete(requestId);
        } catch (RuntimeException e) {
            return new ServiceActionResponse(false, "Error while rejecting/cancelling the request. Try again later");
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
        try {
            List<FriendshipModel> friends = allFriendships.values().stream()
                    .filter(friendship -> Objects.equals(userId, friendship.getUser1().getId()) || Objects.equals(userId, friendship.getUser2().getId()))
                    .collect(Collectors.toList());
            return new AllFriendshipsResponse(true, null, friends);
        } catch (RuntimeException e) {
            return new AllFriendshipsResponse(false, "Error while getting friends. Try again later", null);
        }
    }

    public boolean areFriends(String username1, String username2) {
        return getFriendship(username1, username2) != null;
    }

    private FriendshipModel getFriendship(String username1, String username2) {
        return allFriendships.values().stream()
                .filter(friendship ->
                        (Objects.equals(username1, friendship.getUser1().getUsername()) && Objects.equals(username2, friendship.getUser2().getUsername()))
                                || (Objects.equals(username1, friendship.getUser2().getUsername()) && Objects.equals(username2, friendship.getUser1().getUsername()))
                )
                .findAny().orElse(null);
    }

    public ServiceActionResponse deleteFriend(String senderUsername, String secondUsername) {
        FriendshipModel friendshipModel = getFriendship(senderUsername, secondUsername);
        if (friendshipModel == null) {
            return new ServiceActionResponse(false, "Friendship doesn't exist");
        }

        try {
            friendshipDAO.delete(friendshipModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceActionResponse(false, "Can't  remove the friend. Please try again later");
        }
        allFriendships.remove(friendshipModel.getId());
        return new ServiceActionResponse(true, null);
    }

    public Long getFriendRequestId(String from, String to) {
        try {
            User sender = userDAO.getByField("Username", from).get(0);
            User recipient = userDAO.getByField("Username", to).get(0);
            String[] fields = {"sender_user", "recipient_user"};
            Serializable[] values = {sender.getId(), recipient.getId()};
            return friendRequestDAO.getByFields(fields, values, true).get(0).getId();
        } catch (Exception e) {
            return null;
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
