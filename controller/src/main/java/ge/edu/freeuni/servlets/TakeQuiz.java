package ge.edu.freeuni.servlets;

import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.QuizGameResponse;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.services.QuizService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * here, the user is actually taking the quiz, so every question is shown and they write answers
 * at the end of the quiz there is a "submit" button, that transfers them to "QuizResult" page.
 * This servlet checks and considers the way creator has decided to display the quiz:
 * check the CreateQuiz.java for details.
 */
@WebServlet(name = "TakeQuiz", urlPatterns = "/TakeQuiz")
public class TakeQuiz extends HttpServlet {
    private final QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Long playerId = (Long) httpServletRequest.getSession().getAttribute("currentUserId");
        Long quizId;
        try {
            quizId = Long.parseLong(httpServletRequest.getParameter("quizId"));
        } catch (NumberFormatException e) {
            httpServletRequest.setAttribute("errorMessage", "Invalid URL format");
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        QuizResponse quizResponse = quizService.getQuiz(quizId);
        if (quizResponse.isSuccess()) {
            boolean practice = httpServletRequest.getParameter("practice") != null;
            QuizGameResponse quizGameResponse = quizService.startQuiz(quizId, playerId, practice);
            if (quizGameResponse.isSuccess()) {
                httpServletRequest.setAttribute("quizGameModel", quizGameResponse.getQuizGame());
                if (Objects.equals(Bool.TRUE.name(), quizGameResponse.getQuizGame().getQuiz().isOnePage())) {
                    httpServletRequest.getRequestDispatcher("WEB-INF/TakeQuizSinglePage.jsp").forward(httpServletRequest, httpServletResponse);
                } else {
                    httpServletRequest.getRequestDispatcher("WEB-INF/TakeQuiz.jsp").forward(httpServletRequest, httpServletResponse);
                }
            } else {
                httpServletRequest.setAttribute("errorMessage", quizGameResponse.getErrorMessage());
                httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else {
            httpServletRequest.setAttribute("errorMessage", quizResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
