package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Note;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.NoteModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.List;
import java.util.stream.Collectors;

public class MailService {
    private DAO<Note> noteDAO = DAOFactory.getInstance().getDAO(Note.class);
    List<NoteModel> receivedNotes(UserModel userModel){
        User targetUser = ModelToEntityBridge.toUserEntity(userModel);
        List<Note> receivedNotes = noteDAO.getByField("to",targetUser.getId());
        return receivedNotes.stream()
                .map(n -> EntityToModelBridge.toNoteModel(n))
                .collect(Collectors.toList());

    }

    List<NoteModel> sentNotes(UserModel userModel){
        User targetUser = ModelToEntityBridge.toUserEntity(userModel);
        List<Note> receivedNotes = noteDAO.getByField("from",targetUser.getId());
        return receivedNotes.stream()
                .map(n -> EntityToModelBridge.toNoteModel(n))
                .collect(Collectors.toList());

    }



}
