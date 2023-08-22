package ge.edu.freeuni.servlets;

import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.services.FriendshipService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FriendRequest", urlPatterns = "/FriendRequest")
public class FriendRequest extends HttpServlet {
    private final FriendshipService friendshipService = ServiceFactory.getInstance().getService(FriendshipService.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long senderUserId = (Long) session.getAttribute("currentUserId");
        String receiverUsername = request.getParameter("receiverUsername");
        Long receiverUserId = null;
        try {
            receiverUserId = Long.parseLong(request.getParameter("receiverUserId"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid URL format");
            request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
            return;
        }

        ServiceActionResponse actionResponse = friendshipService.sendFriendshipRequest(senderUserId, receiverUserId);

        if (actionResponse.isSuccess()) {
            request.setAttribute("receiverUsername", receiverUsername);
            RequestDispatcher mailDispatcher = request.getRequestDispatcher("WEB-INF/FriendRequestSent.jsp");
            mailDispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", actionResponse.getErrorMessage());
            request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
        }

    }
}
