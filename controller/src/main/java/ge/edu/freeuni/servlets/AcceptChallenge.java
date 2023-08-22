package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.ChallengeResponse;
import ge.edu.freeuni.services.ChallengeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AcceptChallenge", urlPatterns = "/AcceptChallenge")
public class AcceptChallenge extends HttpServlet {
    private final ChallengeService challengeService = ServiceFactory.getInstance().getService(ChallengeService.class);

    @Override
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        Long currentUserId = (Long) servletRequest.getSession().getAttribute("currentUserId");

        Long challengeId = null;

        try {
             challengeId = Long.parseLong(servletRequest.getParameter("challengeId"));
        } catch (NumberFormatException e) {
            servletRequest.setAttribute("errorMessage", "Invalid URL format");
            servletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(servletRequest, servletResponse);
        }
        ChallengeResponse response = challengeService.deleteChallenge(challengeId);
        if (response.isSuccess()) {
            QuizModel quizModel = response.getChallenge().getQuiz();
            long quizId = quizModel.getId();
            String targetUrl = "quizdetails?id=" + quizId;
            servletResponse.sendRedirect(targetUrl);
        } else {
            servletRequest.setAttribute("errorMessage", response.getErrorMessage());
            servletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(servletRequest, servletResponse);
        }
    }

}
