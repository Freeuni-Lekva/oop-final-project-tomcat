package ge.edu.freeuni.servlets;

import ge.edu.freeuni.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchUser extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servlet = getServletContext();
        String username = httpServletRequest.getParameter("username");

        if(username.length() == 0){
            RequestDispatcher accoundnotExist = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/UserDoesNotExist.jsp");
        }

        if(userService.accountExists(username)){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/UserFound.jsp");
            accountExistsDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            RequestDispatcher accoundnotExist = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/UserDoesNotExist.jsp");
            accoundnotExist.forward(httpServletRequest,httpServletResponse);
        }
    }
}
