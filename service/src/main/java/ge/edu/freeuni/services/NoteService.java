package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Note;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.NoteModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.NoteResponse;
import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.List;

public class NoteService {
    private final DAO<Note> noteDAO = DAOFactory.getInstance().getDAO(Note.class);

    private final UserService userService = new UserService();


    public NoteResponse getNoteById(Long noteId) {
        try {
            List<Note> notes = noteDAO.getByField("id", noteId);
            if (notes == null || notes.isEmpty() || notes.get(0) == null) {
                throw new Exception();
            }
            Note note = notes.get(0);
            NoteModel noteModel = EntityToModelBridge.toNoteModel(note);
            return new NoteResponse(true, null, noteModel);
        } catch (Exception e) {
            return new NoteResponse(false,
                    "this note can not be opened",
                    null);
        }
    }


    public NoteResponse createNewNote(Long sendersId, String recipientsUsername, String content, String subject) {
        try {
            UserResponse recipientUserResponse = userService.findUser(recipientsUsername);
            if (!recipientUserResponse.isSuccess()) {
                return new NoteResponse(false, "the recipient user does not exist", null);
            }
            UserResponse senderUserResponse = userService.findUser(sendersId);
            if (!senderUserResponse.isSuccess()) {
                return new NoteResponse(false, "a note can not be sent by this user", null);
            }

            User sender = ModelToEntityBridge.toUserEntity(senderUserResponse.getUser());
            User recipient = ModelToEntityBridge.toUserEntity(recipientUserResponse.getUser());

            noteDAO.create(new Note(sender, recipient, content, subject));

            return new NoteResponse(true, null, null);
        } catch (Exception e) {
            return new NoteResponse(false,
                    "there was a problem with the note's creation, try again later",
                    null);
        }
    }


}
