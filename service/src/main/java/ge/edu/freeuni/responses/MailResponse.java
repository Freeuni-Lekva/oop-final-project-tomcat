package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.NoteModel;

import java.util.List;

public class MailResponse extends ServiceActionResponse{
    private final List<NoteModel> noteModels;
    public MailResponse(boolean success, String errorMessage, List<NoteModel> noteModels) {
        super(success, errorMessage);
        this.noteModels = noteModels;
    }

    public List<NoteModel> getNoteModels() {
        return noteModels;
    }
}
