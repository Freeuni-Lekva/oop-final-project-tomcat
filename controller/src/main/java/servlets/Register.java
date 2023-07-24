package servlets;

import entities.User;
import org.example.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Register extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/RegisterNewAccount.jsp");
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servlet = getServletContext();
        String username = httpServletRequest.getParameter("username");
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String password = httpServletRequest.getParameter("password");

        if(username.length() == 0  || password.length() == 0 ){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/TryRegisterAgain.jsp");
        }

        if(userService.account_exists(username)){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/TryRegisterAgain.jsp");
            accountExistsDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            User newUser = new User(username,firstName,lastName,password);
            userService.add_account(newUser);

            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("currentUser",username);
            httpSession.setAttribute("currentUserId",newUser.getId());
            RequestDispatcher homepageDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/Homepage.jsp");
            homepageDispatcher.forward(httpServletRequest,httpServletResponse);
        }
    }

}
