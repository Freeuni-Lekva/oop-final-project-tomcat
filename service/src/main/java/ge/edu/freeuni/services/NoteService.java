package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Note;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.NoteModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.MailResponse;
import ge.edu.freeuni.responses.NoteResponse;
import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.List;
import java.util.stream.Collectors;

public class NoteService {
    private DAO<Note> noteDAO = DAOFactory.getInstance().getDAO(Note.class);

    private final UserService userService = new UserService();


    public NoteResponse getNoteById(Long noteId) {
        try{
            List<Note> notes = noteDAO.getByField("id", noteId);
            if(notes == null || notes.isEmpty() || notes.get(0) == null){
                throw new Exception("the note is not available");
            }
            Note note = notes.get(0);
            NoteModel noteModel = EntityToModelBridge.toNoteModel(note);
            return new NoteResponse(true,null,noteModel);
        }catch(Exception e){
            return new NoteResponse(false,e.getMessage(),null);
        }
    }

    public MailResponse getUsersReceivedNotes(Long currentUserId) {
        try{
            List<Note> receivedNotes = noteDAO.getByField("id", currentUserId);
            List<NoteModel> receivedNotesModel = receivedNotes.stream()
                    .map(n -> EntityToModelBridge.toNoteModel(n))
                    .collect(Collectors.toList());
            return new MailResponse(true,null,receivedNotesModel);
        }catch(Exception e){
            return new MailResponse(false,e.getMessage(),null);
        }
    }

    public NoteResponse createNewNote(Long sendersId, String recipientsUsername, String content, String subject) {
        try{
            UserResponse recipientUserResponse = userService.searchUser(recipientsUsername);
            if(!recipientUserResponse.isSuccess()){
                throw new Exception("the recipient user does not exist");
            }
            UserResponse senderUserResponse = userService.searchUser(sendersId);
            if(!senderUserResponse.isSuccess()){
                throw new Exception("a note can not be sent by this user");
            }

            User sender = ModelToEntityBridge.toUserEntity(senderUserResponse.getUser());
            User recipient  = ModelToEntityBridge.toUserEntity(recipientUserResponse.getUser());

            noteDAO.create(new Note(sender,recipient,content,subject,System.currentTimeMillis()));

            return new NoteResponse(true,null,null);
        }catch(Exception e){
            return new NoteResponse(false,e.getMessage(),null);
        }
    }
}
