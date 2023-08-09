package ge.edu.freeuni.servlets;

import ge.edu.freeuni.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * on this page user creates a challenge,
 * a template appears on their screen , they have to choose a target user(the list of options should appear),
 * and choose the quiz. (options should be listed).
 * if the user they chose doesn't exist, isn't their friend or the quiz doesn't exist
 * they will go to a new page which will tell them that the challenge could not be created.
 * (they choose quiz and user from lists, so this case may never happen)
 */
@WebServlet(name = "ChallengeSend",urlPatterns = "/ChallengeSend")
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

        if(userService.accountExists(to)){
            RequestDispatcher accountExistsDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/ChallengeSent.jsp");
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("to", to);
            accountExistsDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            RequestDispatcher accoundnotExist = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/UserDoesNotExist.jsp");
            accoundnotExist.forward(httpServletRequest,httpServletResponse);
        }
    }

}

