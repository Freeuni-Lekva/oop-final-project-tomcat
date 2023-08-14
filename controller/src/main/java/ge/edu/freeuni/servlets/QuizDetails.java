package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.services.QuizService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Each quiz should have a summary page which includes
 *  The text description of the quiz.
 *  The creator of the quiz (hot linked to the creator’s user page).
 *  A list of the user’s past performance on this specific quiz. Consider allowing the
 * user to order this by date, by percent correct, and by amount of time the quiz took.
 *  A list of the highest performers of all time.
 *  A list of top performers in the last day.1
 *  A list showing the performance of recent test takers (both good and bad).
 *  Summary statistics on how well all users have performed on the quiz.
 *  A way to initiate taking the quiz.
 *  A way to start the quiz in practice mode, if available.
 *  A way to start editing the quiz, if the user is the quiz owner.
 * (any time a user is listed it should be a link to that user's profile)
 */
@WebServlet("/quizdetails")
public class QuizDetails extends HttpServlet {
    private final QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quizIdParam = request.getParameter("id");
        if (quizIdParam != null) {
            try {
                Long quizId = Long.parseLong(quizIdParam);
                QuizResponse quizDetails = quizService.getQuiz(quizId);

                if (quizDetails.isSuccess()) {
                    request.setAttribute("quizDetails", quizDetails.getQuiz());
                    request.getRequestDispatcher("WEB-INF/QuizDetails.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", quizDetails.getErrorMessage());
                    request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid URL format");
                request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Missing URL parameter");
            request.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(request, response);
        }
    }
}