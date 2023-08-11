package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Note;
import ge.edu.freeuni.models.NoteModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.MailResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

import java.util.List;
import java.util.stream.Collectors;

public class MailService {
    private DAO<Note> noteDAO = DAOFactory.getInstance().getDAO(Note.class);

    public MailResponse getUsersReceivedNotes(Long currentUserId) {
        try{
            List<Note> receivedNotes = noteDAO.getByField("id", currentUserId);
            List<NoteModel> receivedNotesModel = receivedNotes.stream()
                    .map(n -> EntityToModelBridge.toNoteModel(n))
                    .collect(Collectors.toList());
            return new MailResponse(true,null,receivedNotesModel);
        }catch(Exception e){
            return new MailResponse(false,
                    "There is a problem with the mail system, please try again later",
                    null);
        }
    }

    public MailResponse sentNotes(UserModel userModel){
        try{
            List<Note> sentNotes = noteDAO.getByField("from",userModel.getId());
            List<NoteModel> sentNotesModel = sentNotes.stream()
                    .map(n->EntityToModelBridge.toNoteModel(n))
                    .collect(Collectors.toList());
            return new MailResponse(true,null,sentNotesModel);
        }catch(Exception e){
            return new MailResponse(false,"There is a problem with the mail system, please try again later",
                    null);
        }

    }



}
