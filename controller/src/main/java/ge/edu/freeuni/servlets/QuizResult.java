package ge.edu.freeuni.servlets;

import javax.servlet.annotation.WebServlet;

/**
 * appears after a user has taken a quiz. It shows how well the user performed on the
 * quiz including their score and time. You may also want to list all the user’s answers along
 * with the correct answers here.
 * This page could also include tables showing comparisons to the user’s past performance on
 * the quiz and comparisons with top performers or with the user’s friends.
 */
@WebServlet(name = "QuizResult",urlPatterns = "/QuizResult")
public class QuizResult {
}
