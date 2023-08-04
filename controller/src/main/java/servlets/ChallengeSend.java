package servlets;

import entities.User;
import org.example.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChallengeSend extends HttpServlet {


    private final UserService userService = new UserService();

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servlet = getServletContext();

        String from = httpServletRequest.getParameter("from_id");
        String to = httpServletRequest.getParameter("to_id");

        if(from.length() == 0 || to.length() == 0){
            RequestDispatcher accoundnotExist = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/UserDoesNotExist.jsp");
        }

        if(userService.account_exists(to)){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/ChallengeSent.jsp");
            accountExistsDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            RequestDispatcher accoundnotExist = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/UserDoesNotExist.jsp");
            accoundnotExist.forward(httpServletRequest,httpServletResponse);
        }
    }

}

