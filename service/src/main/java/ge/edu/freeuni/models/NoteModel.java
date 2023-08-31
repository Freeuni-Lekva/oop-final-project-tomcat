package ge.edu.freeuni.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoteModel extends NotificationModel{
    private Long id;
    private UserModel from;
    private UserModel to;
    private String subject;
    private String message;
    private String seen;

    public NoteModel(Long timestamp, Long id, UserModel from, UserModel to, String message, String subject, String seen) {
        super(timestamp);
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
        this.seen = seen;
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

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteModel noteModel = (NoteModel) o;

        if (!Objects.equals(id, noteModel.id)) return false;
        if (!Objects.equals(from, noteModel.from)) return false;
        if (!Objects.equals(to, noteModel.to)) return false;
        if (!Objects.equals(subject, noteModel.subject)) return false;
        if (!Objects.equals(message, noteModel.message)) return false;
        return Objects.equals(seen, noteModel.seen);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (seen != null ? seen.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", seen='" + seen + '\'' +
                "} " + super.toString();
    }
}
