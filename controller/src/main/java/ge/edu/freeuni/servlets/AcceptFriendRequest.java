package ge.edu.freeuni.servlets;


import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.services.FriendshipService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AcceptFriendRequest", urlPatterns = "/AcceptFriendRequest")

public class AcceptFriendRequest extends HttpServlet {
    private final FriendshipService friendshipService = ServiceFactory.getInstance().getService(FriendshipService.class);

    @Override
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        Long currentUserId = (Long) servletRequest.getSession().getAttribute("currentUserId");

        String location = servletRequest.getParameter("location");
        if (location == null || !(location.equals("Notifications") || location.equals("FriendRequests"))) {
            servletRequest.setAttribute("errorMessage", "Invalid URL format");
            servletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(servletRequest, servletResponse);
        }

        Long requestId = null;

        try {
            requestId = Long.parseLong(servletRequest.getParameter("requestId"));
        } catch (NumberFormatException e) {
            servletRequest.setAttribute("errorMessage", "Invalid URL format");
            servletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(servletRequest, servletResponse);
        }

        ServiceActionResponse response = friendshipService.approveFriendshipRequest(currentUserId, requestId);
        if (response.isSuccess()) {
            servletResponse.sendRedirect(location);
        } else {
            servletRequest.setAttribute("errorMessage", response.getErrorMessage());
            servletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(servletRequest, servletResponse);
        }

    }
}
