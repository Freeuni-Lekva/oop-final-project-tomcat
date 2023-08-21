package ge.edu.freeuni.models;

import ge.edu.freeuni.util.DatetimeUtil;

import java.util.List;

public abstract class NotificationModel {
    private final String datetime;
    private final Long timestamp;

    public NotificationModel(Long timestamp) {
        this.timestamp = timestamp;
        this.datetime = DatetimeUtil.convertTimestampToString(timestamp);
    }

    public String getDatetime() {
        return datetime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public abstract Long getId();
}
