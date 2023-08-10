package ge.edu.freeuni.servlets;

import ge.edu.freeuni.responses.MailResponse;
import ge.edu.freeuni.services.NoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * on this page, the user sees the list of all notes they have received.
 * they can click on any of them to view the note.
 */
@WebServlet(name = "Mail",urlPatterns = "/Mail")
public class Mail extends HttpServlet {
    private final NoteService noteService = new NoteService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");

        MailResponse mailResponse = noteService.getUsersReceivedNotes(currentUserId);
        if(mailResponse.isSuccess()){
            httpServletRequest.setAttribute("receivedNotes",mailResponse.getNoteModels());
        }else{
            httpServletRequest.setAttribute("mailErrorMessage",mailResponse.getErrorMessage());
        }

        RequestDispatcher mailDispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/Mail.jsp");
        mailDispatcher.forward(httpServletRequest,httpServletResponse);
    }

}

