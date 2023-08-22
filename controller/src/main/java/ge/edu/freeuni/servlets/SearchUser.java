package ge.edu.freeuni.servlets;

import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.services.FriendshipService;
import ge.edu.freeuni.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class SearchUser extends HttpServlet {

    private final UserService userService = ServiceFactory.getInstance().getService(UserService.class);
    private final FriendshipService friendshipService = ServiceFactory.getInstance().getService(FriendshipService.class);

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        String currentUser = (String) httpServletRequest.getSession().getAttribute("currentUser");

        UserResponse userResponse = userService.findUser(username);

        if (userResponse.isSuccess()) {
            boolean areFriends = friendshipService.areFriends(username, currentUser);
            Long friendRequestId = friendshipService.getFriendRequestId(currentUser, username);
            Long receivedFriendRequestId = friendshipService.getFriendRequestId(username,currentUser);
            httpServletRequest.setAttribute("userdetails", userResponse.getUser());
            httpServletRequest.setAttribute("areFriends", areFriends);
            httpServletRequest.setAttribute("friendRequestId", friendRequestId);
            httpServletRequest.setAttribute("receivedFriendRequestId",receivedFriendRequestId);
            httpServletRequest.getRequestDispatcher("WEB-INF/UserDetails.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.setAttribute("errorMessage", userResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
