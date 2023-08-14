package ge.edu.freeuni.servlets;

import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.QuizzesResponse;
import ge.edu.freeuni.services.QuizService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * show user:
 * An Announcements section highlighting any items put up by the website
 * administrators.
 *  A list of popular quizzes.
 *  A list of recently created quizzes.
 *  A list of their own recent quiz taking activities.
 *  A list of their recent quiz creating activities, if any. You may eliminate this list if the
 * user has never created a quiz.
 *  An indication of all achievements they’ve earned. This can be an abbreviated list
 * with a more complete list elsewhere, if you believe that will provide you with a
 * better looking or more functional website.
 *  An indication if they’ve received any messages, with some information on the most
 * recently received messages (such as whether they are challenges or friend’s
 * requests).
 *  A list of friend’s recent activities including quizzes taken or created and
 * achievements earned. This summary should include links to the friend’s user page
 * and the quiz pages.
 * (any time a user is listed it should be a link to that user's profile)
 */
@WebServlet("/home")
public class Homepage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("currentUserId");

        QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);
        QuizzesResponse popularQuizzes = quizService.getMostPopularQuizzes();
        QuizzesResponse recentQuizzes = quizService.getMostRecentQuizzes();
        QuizzesResponse userRecentQuizzes = quizService.getMostRecentQuizzes(userId);

        request.setAttribute("popularQuizzes", popularQuizzes.getQuizzes());
        request.setAttribute("recentQuizzes", recentQuizzes.getQuizzes());
        request.setAttribute("userRecentQuizzes", userRecentQuizzes.getQuizzes());

        request.getRequestDispatcher("WEB-INF/Homepage.jsp").forward(request, response);

    }
}
