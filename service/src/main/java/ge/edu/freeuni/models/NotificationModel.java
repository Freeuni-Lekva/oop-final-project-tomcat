package ge.edu.freeuni.models;

import ge.edu.freeuni.util.DatetimeUtil;

import java.util.List;

public abstract class NotificationModel {
    private final String datetime;

    public NotificationModel(Long timestamp) {
        this.datetime = DatetimeUtil.convertTimestampToString(timestamp);
    }

    public String getDatetime() {
        return datetime;
    }

    public abstract Long getId();
}
