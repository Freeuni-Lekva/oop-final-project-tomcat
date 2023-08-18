package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.InboxModel;
import ge.edu.freeuni.responses.NotificationsResponse;
import ge.edu.freeuni.services.NotificationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * on this pages user sees all the challenges other people have sent to him.
 * there are links for each challenge.
 * each one of them will have "start the quiz"  button which will automatically transfer the user to the quiz page
 * and the quiz will start,
 * there should also be a decline button, so the challenge will no longer appear in this list,
 * the quiz name should also be a link to the summary of the quz so the user will be able to see the summary
 * before accepting ro declining the challenge.
 */
@WebServlet(name = "Challenges",urlPatterns = "/Challenges")
public class Challenges extends HttpServlet {
    private final NotificationsService challengeService = new NotificationsService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");

        NotificationsResponse challengesResponse = challengeService.getReceivedChallenges(currentUserId);
        if(challengesResponse.isSuccess()){
            InboxModel inboxModel = challengesResponse.getInboxModel();
            httpServletRequest.setAttribute("inboxModel", inboxModel);
            httpServletRequest.setAttribute("createNewURL", "/SendChallenge");
            httpServletRequest.setAttribute("location","Challenges");
            httpServletRequest.getRequestDispatcher("WEB-INF/Notifications.jsp").forward(httpServletRequest, httpServletResponse);
        }else{
            httpServletRequest.setAttribute("errorMessage", challengesResponse.getErrorMessage());
        }

    }
}
