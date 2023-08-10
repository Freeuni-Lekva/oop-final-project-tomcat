package ge.edu.freeuni.servlets;

import ge.edu.freeuni.responses.NoteResponse;
import ge.edu.freeuni.services.NoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * the user sees the whole content of the note, there should be a "reply" bytton on the screen
 * and by clicking on that they can create a new note and reply to that user.
 */
@WebServlet(name = "Note",urlPatterns = "/Note")
public class Note extends HttpServlet {
    private final NoteService noteService = new NoteService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Long noteId = Long.parseLong(httpServletRequest.getParameter("noteId"));

        NoteResponse noteResponse = noteService.getNoteById(noteId);
        if(noteResponse.isSuccess()){
            httpServletRequest.setAttribute("note",noteResponse.getNoteModel());

            RequestDispatcher noteDispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/Note.jsp");
            noteDispatcher.forward(httpServletRequest, httpServletResponse);
        }else{
            httpServletRequest.setAttribute("noteErrorMessage",noteResponse.getErrorMessage());

            RequestDispatcher noteDispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/Mail.jsp");
            noteDispatcher.forward(httpServletRequest, httpServletResponse);
        }
    }

}