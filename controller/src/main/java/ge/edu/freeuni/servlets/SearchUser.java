package ge.edu.freeuni.servlets;

import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class SearchUser extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");

        UserResponse userResponse = userService.findUser(username);
        if (userResponse.isSuccess()) {
            httpServletRequest.setAttribute("userdetails", userResponse.getUser());
            httpServletRequest.getRequestDispatcher("WEB-INF/UserDetails.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.setAttribute("errorMessage", userResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
