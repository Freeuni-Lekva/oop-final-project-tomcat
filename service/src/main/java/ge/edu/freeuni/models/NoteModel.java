package ge.edu.freeuni.models;

import java.util.ArrayList;
import java.util.List;

public class NoteModel extends NotificationModel{
    private Long id;
    private UserModel from;
    private UserModel to;
    private String subject;
    private String message;

    public NoteModel(Long timestamp, Long id, UserModel from, UserModel to, String message, String subject) {
        super(timestamp);
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getFrom() {
        return from;
    }

    public void setFrom(UserModel from) {
        this.from = from;
    }

    public UserModel getTo() {
        return to;
    }

    public void setTo(UserModel to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
