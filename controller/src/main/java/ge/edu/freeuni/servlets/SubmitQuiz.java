package ge.edu.freeuni.servlets;

import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.QuizGameResponse;
import ge.edu.freeuni.services.QuestionService;
import ge.edu.freeuni.services.QuizService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SubmitQuiz", urlPatterns = "/SubmitQuiz")
public class SubmitQuiz extends HttpServlet {
    private final QuestionService questionService = ServiceFactory.getInstance().getService(QuestionService.class);
    private final QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);
    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Long userId = (Long) httpServletRequest.getSession().getAttribute("currentUserId");
        long quizId = -1;
        long quizGameId = -1;
        try {
            quizId = Long.parseLong(httpServletRequest.getParameter("quizId"));
            quizGameId = Long.parseLong(httpServletRequest.getParameter("quizGameId"));
        } catch (NumberFormatException e) {
            httpServletRequest.setAttribute("errorMessage", "Invalid URL format");
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        List<QuestionModel> questions = questionService.getQuestions(quizId);
        Map<Long, String> questionsAnswers = new HashMap<>();

        for (QuestionModel question : questions) {
            try {
                String answer = httpServletRequest.getParameter("response_" + question.getId());
                questionsAnswers.put(question.getId(), answer);
            } catch (NumberFormatException e) {
                httpServletRequest.setAttribute("errorMessage", "No answers inputted for the question: " + question.getQuestion());
                httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }

        QuizGameResponse response = quizService.finishQuiz(questionsAnswers, quizGameId, userId);
        if (response.isSuccess()) {
            httpServletResponse.sendRedirect("QuizResults?id=" + response.getQuizGame().getId());
        } else {
            httpServletRequest.setAttribute("errorMessage", response.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
