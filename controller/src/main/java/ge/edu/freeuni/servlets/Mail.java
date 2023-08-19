package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.InboxModel;
import ge.edu.freeuni.responses.NotificationsResponse;
import ge.edu.freeuni.services.NotificationsService;

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
    private final NotificationsService notificationsService = new NotificationsService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");

        NotificationsResponse mailResponse = notificationsService.getMail(currentUserId);
        if(mailResponse.isSuccess()){
            InboxModel inboxModel = mailResponse.getInboxModel();
            httpServletRequest.setAttribute("inboxModel", inboxModel);
            httpServletRequest.setAttribute("createNewURL","./CreateNewNote");
            httpServletRequest.setAttribute("location", "Mail");
            httpServletRequest.getRequestDispatcher("WEB-INF/Notifications.jsp").forward(httpServletRequest, httpServletResponse);
        }else{
            httpServletRequest.setAttribute("errorMessage",mailResponse.getErrorMessage());
        }
    }

}

