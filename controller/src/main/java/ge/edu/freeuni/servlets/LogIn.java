package ge.edu.freeuni.servlets;

import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The visitor sees the login page and a link to the register one.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LogIn extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/Login.jsp");
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username").toLowerCase();
        String password = httpServletRequest.getParameter("password");

        UserResponse userResponse = userService.findUser(username,password);
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("errorMessage");

        if(userResponse.isSuccess()){
            httpSession.setAttribute("currentUser",username);
            httpSession.setAttribute("currentUserId",userResponse.getUser().getId());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/home");
        }else{
            httpServletRequest.setAttribute("errorMessage",userResponse.getErrorMessage());
            RequestDispatcher loginDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/Login.jsp");
            loginDispatcher.forward(httpServletRequest,httpServletResponse);
        }

    }
}
