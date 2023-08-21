package ge.edu.freeuni.services;

import ge.edu.freeuni.models.InboxModel;
import ge.edu.freeuni.responses.AllFriendRequestsResponse;
import ge.edu.freeuni.responses.ChallengesResponse;
import ge.edu.freeuni.responses.MailResponse;
import ge.edu.freeuni.responses.NotificationsResponse;

public class NotificationsService {
    private final FriendshipService friendshipService = new FriendshipService();
    private final MailService mailService = new MailService();
    private final ChallengeService challengeService = new ChallengeService();
    public NotificationsService() {
    }

    public NotificationsResponse getNotifications(Long userId){
        try {
            AllFriendRequestsResponse allFriendRequestsResponse = friendshipService.getAllFriendRequests(userId);
            MailResponse mailResponse = mailService.getUsersReceivedNotes(userId);
            ChallengesResponse challengeResponse = challengeService.getReceivedChallenges(userId);

            InboxModel inboxModel = new InboxModel();
            inboxModel.addNotifications(
                    allFriendRequestsResponse.getAllFriendRequests(),
                    mailResponse.getNoteModels(),
                    challengeResponse.getChallenges()
            );

            return new NotificationsResponse(true, null, inboxModel);
        }catch(Exception e){
            return new NotificationsResponse(false,"the notifications can not be loaded\n" +
                    "please, try again later",
                    null);
        }
    }
    public NotificationsResponse getReceivedChallenges(Long userId){
        try{
            ChallengesResponse challengeResponse = challengeService.getReceivedChallenges(userId);
            if(!challengeResponse.isSuccess()){
                throw new Exception();
            }
            InboxModel inboxModel = new InboxModel();
            inboxModel.addChallenges(challengeResponse.getChallenges());

            return new NotificationsResponse(true, null, inboxModel);
        }catch(Exception e){
            return new NotificationsResponse(false,"the received challenges can not be loaded\n" +
                    "please, try again later",
                    null);
        }

    }

    public NotificationsResponse getMail(Long userId){
        try{
            MailResponse mailResponse = mailService.getUsersReceivedNotes(userId);
            if(!mailResponse.isSuccess()){
                throw new Exception();
            }
            InboxModel inboxModel = new InboxModel();
            inboxModel.addNoteModels(mailResponse.getNoteModels());

            return new NotificationsResponse(true, null, inboxModel);
        }catch(Exception e){
            return new NotificationsResponse(false,"the received notes can not be loaded\n" +
                    "please, try again later",
                    null);
        }

    }

    public NotificationsResponse getReceivedFriendRequests(Long currentUserId) {
        try{
            AllFriendRequestsResponse friendRequestsResponse = friendshipService.getAllFriendRequests(currentUserId);
            if(!friendRequestsResponse.isSuccess()){
                throw new Exception();
            }
            InboxModel inboxModel = new InboxModel();
            inboxModel.addFriendRequests(friendRequestsResponse.getAllFriendRequests());

            return new NotificationsResponse(true,null,inboxModel);
        }catch(Exception e){
            return new NotificationsResponse(false,"the received friend requests can not be loaded\n" +
                    "please, try again later",
                    null);
        }
    }
}
