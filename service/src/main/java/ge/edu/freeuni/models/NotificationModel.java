package ge.edu.freeuni.models;

import java.util.List;

public abstract class NotificationModel {
    private Long timestamp;

    private String notificationLabel;

    public NotificationModel(Long timestamp) {
        this.timestamp = timestamp;
        this.notificationLabel = notificationLabel;
    }

    public abstract String getNotificationType();

    public Long getTimestamp() {
        return timestamp;
    }
    public abstract List<String> getNotificationLabel();
    public abstract Long getId();
}
