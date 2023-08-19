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

@WebServlet(name = "Notifications", urlPatterns = "/Notifications")
public class Notifications extends HttpServlet {
    private final NotificationsService notificationsService = new NotificationsService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        NotificationsResponse serviceActionResponse = notificationsService.getNotifications(currentUserId);

        if(serviceActionResponse.isSuccess()){
            InboxModel inboxModel = serviceActionResponse.getInboxModel();
            httpServletRequest.setAttribute("inboxModel", inboxModel);
            httpServletRequest.setAttribute("createNewURL", "./CreateNewNotification");
            httpServletRequest.setAttribute("location", "Notifications");
            httpServletRequest.getRequestDispatcher("WEB-INF/Notifications.jsp").forward(httpServletRequest, httpServletResponse);
        }else{
            System.out.println(serviceActionResponse.getErrorMessage());
            httpServletRequest.setAttribute("errorMessage",serviceActionResponse.getErrorMessage());
        }
    }
}
