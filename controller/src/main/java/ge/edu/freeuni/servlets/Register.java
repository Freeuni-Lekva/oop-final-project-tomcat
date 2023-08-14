package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.UserModel;
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

@WebServlet(name = "register new account", urlPatterns = "/register")
public class Register extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/RegisterNewAccount.jsp");
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username").toLowerCase();
        String firstName = httpServletRequest.getParameter("firstName").toLowerCase();
        String lastName = httpServletRequest.getParameter("lastName").toLowerCase();
        String password = httpServletRequest.getParameter("password");

        UserResponse userResponse = userService.addAccount(new UserModel(null,username,firstName,lastName,password));
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("errorMessage");

        if(userResponse.isSuccess()){
            httpSession.setAttribute("currentUser",username);
            httpSession.setAttribute("currentUserId",userResponse.getUser().getId());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
        }else{
            httpServletRequest.setAttribute("errorMessage",userResponse.getErrorMessage());
            RequestDispatcher registerDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/RegisterNewAccount.jsp");
            registerDispatcher.forward(httpServletRequest,httpServletResponse);
        }

    }

}
