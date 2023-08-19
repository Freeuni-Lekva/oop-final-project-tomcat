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
 * this page shows every friend request of the user,
 * a user should be able to click on the link and see the senders profile
 * and they should also be able to accept or reject the request.
 */
@WebServlet(name = "FriendRequests",urlPatterns = "/FriendRequests")
public class FriendRequests extends HttpServlet {
    private final NotificationsService notificationsService = new NotificationsService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");

        NotificationsResponse friendRequestsResponse = notificationsService.getReceivedFriendRequests(currentUserId);
        if(friendRequestsResponse.isSuccess()){
            InboxModel inboxModel = friendRequestsResponse.getInboxModel();
            httpServletRequest.setAttribute("inboxModel", inboxModel);
            httpServletRequest.setAttribute("createNewURL","./FriendRequest");
            httpServletRequest.setAttribute("location","FriendRequests");
            httpServletRequest.getRequestDispatcher("WEB-INF/Notifications.jsp").forward(httpServletRequest, httpServletResponse);
        }else{
            httpServletRequest.setAttribute("errorMessage",friendRequestsResponse.getErrorMessage());
        }
    }
}
