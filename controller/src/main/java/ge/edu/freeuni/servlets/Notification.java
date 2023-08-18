package ge.edu.freeuni.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Notification",urlPatterns = "/Notification")
public class Notification extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String notificationType =(String) httpServletRequest.getParameter("notificationType");
        Long notificationId = Long.parseLong(httpServletRequest.getParameter("notificationId"));
        String dest = "";
        if(notificationType.equals("Note")){
            httpServletRequest.setAttribute("noteId",notificationId);
            dest = "/Note";
        }else if(notificationType.equals("Challenge")){
            httpServletRequest.setAttribute("challengeId",notificationId);
            dest = "/Challenge";
        }else if(notificationType.equals("FriendRequest")){
            httpServletRequest.setAttribute("friendRequestId",notificationId);
            dest = "/FriendRequest";
        }
        httpServletRequest.getRequestDispatcher(dest).forward(httpServletRequest,httpServletResponse);
    }
}
