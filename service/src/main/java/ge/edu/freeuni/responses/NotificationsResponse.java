package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.InboxModel;

public class NotificationsResponse extends ServiceActionResponse{
    private final InboxModel inboxModel;

    public NotificationsResponse(boolean success, String errorMessage, InboxModel inboxModel) {
        super(success, errorMessage);
        this.inboxModel = inboxModel;
    }

    public InboxModel getInboxModel() {
        return inboxModel;
    }
}
