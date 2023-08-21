package ge.edu.freeuni.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InboxModel {
    private List<NotificationModel> notificationModelList;

    public InboxModel() {
        this.notificationModelList = new ArrayList<>();
    }

    public InboxModel(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
    }

    public List<NotificationModel> getNotificationModelList() {
        return notificationModelList;
    }

    public void setNotificationModelList(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
    }

    public void addFriendRequests(List<FriendRequestModel> allFriendRequests) {
        if(allFriendRequests != null)
            this.notificationModelList.addAll(allFriendRequests);
    }

    public void addNoteModels(List<NoteModel> noteModels) {
        if(noteModels != null)
            this.notificationModelList.addAll(noteModels);
    }

    public void addChallenges(List<ChallengeModel> challenges) {
        if(challenges != null)
            this.notificationModelList.addAll(challenges);
    }

    public void addNotifications(List<FriendRequestModel> friendRequests, List<NoteModel> notes, List<ChallengeModel> challenges) {
        addFriendRequests(friendRequests);
        addNoteModels(notes);
        addChallenges(challenges);
        this.notificationModelList.sort(Comparator.comparingLong(NotificationModel::getTimestamp).reversed());
    }
}
