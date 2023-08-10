package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.NoteModel;

public class NoteResponse extends ServiceActionResponse{
    private final NoteModel noteModel;
    public NoteResponse(boolean success, String errorMessage, NoteModel noteModel) {
        super(success, errorMessage);
        this.noteModel = noteModel;
    }

    public NoteModel getNoteModel() {
        return noteModel;
    }
}
