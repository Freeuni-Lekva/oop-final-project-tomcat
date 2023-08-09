package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.UserModel;
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

        if(username.length() == 0  || password.length() == 0 ){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/TryRegisterAgain.jsp");
        }

        if(userService.accountExists(username)){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/TryRegisterAgain.jsp");
            accountExistsDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            UserModel newUser = new UserModel(null, username,firstName,lastName,password);
            userService.addAccount(newUser);

            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("currentUser",username);
            httpSession.setAttribute("currentUserId",newUser.getId());
            RequestDispatcher homepageDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/Homepage.jsp");
            homepageDispatcher.forward(httpServletRequest,httpServletResponse);
        }
    }

}
