package ge.edu.freeuni.servlets;

import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.QuizGameResponse;
import ge.edu.freeuni.services.QuizService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuizResults", urlPatterns = "/QuizResults")
public class QuizResults extends HttpServlet {
    private final QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long quizGameId;
        try {
            quizGameId = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid URL format");
            request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
            return;
        }
        QuizGameResponse quizGameResponse = quizService.getQuizGame(quizGameId);
        if (quizGameResponse.isSuccess()) {
            request.setAttribute("quizGameModel", quizGameResponse.getQuizGame());
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/QuizResults.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", quizGameResponse.getErrorMessage());
            request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
        }
    }
}
