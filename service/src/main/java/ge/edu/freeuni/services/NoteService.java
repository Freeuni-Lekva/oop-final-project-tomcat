package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Note;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.models.NoteModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.NoteResponse;
import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

import java.util.List;

public class NoteService {
    private final DAO<Note> noteDAO = DAOFactory.getInstance().getDAO(Note.class);


    private final UserService userService = new UserService();


    public NoteResponse getNoteById(Long noteId, boolean markAsSeen) {
        try {
            List<Note> notes = noteDAO.getByField("id", noteId);
            if (notes == null || notes.isEmpty() || notes.get(0) == null) {
                throw new Exception();
            }
            Note note = notes.get(0);
            if (markAsSeen) {
                note.setSeen(Bool.TRUE.name());
                noteDAO.update(note);
            }
            NoteModel noteModel = EntityToModelBridge.toNoteModel(note);
            return new NoteResponse(true, null, noteModel);
        } catch (Exception e) {
            e.printStackTrace();
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

            User sender = userService.getById(sendersId);
            User recipient = userService.getByUsername(recipientsUsername);
            Note newNote = new Note(sender,recipient,subject,content);
            noteDAO.create(newNote);
            return new NoteResponse(true, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new NoteResponse(false,
                    "there was a problem with the note's creation, try again later",
                    null);
        }
    }


}
