package ge.edu.freeuni.servlets;

import ge.edu.freeuni.responses.NoteResponse;
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
 * the logged in user creates a new note to send it to a friend.
 */
@WebServlet(name = "CreateNewNote",urlPatterns = "/CreateNewNote")
public class CreateNewNote extends HttpServlet {
    private final NoteService noteService = new NoteService();
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/CreateNewNote.jsp");
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long sendersId = (Long)session.getAttribute("currentUserId");

        String subject = httpServletRequest.getParameter("noteContent");
        String recipientsUsername = httpServletRequest.getParameter("noteRecipient");
        String content = httpServletRequest.getParameter("noteContent");

        NoteResponse noteResponse = noteService.createNewNote(sendersId,recipientsUsername,content,subject);

        if(noteResponse.isSuccess()){
            RequestDispatcher mailDispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/Mail.jsp");
            mailDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            httpServletRequest.setAttribute("NoteCreationErrorMessage", noteResponse.getErrorMessage());
            httpServletRequest.setAttribute("noteSubject", subject);
            httpServletRequest.setAttribute("noteRecipient", recipientsUsername);
            httpServletRequest.setAttribute("noteContent", content);

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/CreateNewNote.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }
    }
}
