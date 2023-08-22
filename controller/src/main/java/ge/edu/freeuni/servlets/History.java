package ge.edu.freeuni.servlets;

import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.AllQuizGamesResponse;
import ge.edu.freeuni.responses.QuizzesResponse;
import ge.edu.freeuni.services.QuizService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "History", urlPatterns = "/History")
public class History extends HttpServlet {
    QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Long currentUserId = (Long) httpServletRequest.getSession().getAttribute("currentUserId");

        QuizzesResponse quizzesResponse = quizService.getAllQuizzes(currentUserId);
        if (!quizzesResponse.isSuccess()) {
            httpServletRequest.setAttribute("errorMessage", quizzesResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }
        AllQuizGamesResponse allQuizGamesResponse = quizService.getAllQuizGames(currentUserId);
        if (!allQuizGamesResponse.isSuccess()) {
            httpServletRequest.setAttribute("errorMessage", allQuizGamesResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        httpServletRequest.setAttribute("allQuizzes", quizzesResponse.getQuizzes());
        httpServletRequest.setAttribute("allQuizGames", allQuizGamesResponse.getAllQuizGames());
        httpServletRequest.getRequestDispatcher("WEB-INF/History.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
