package ge.edu.freeuni.servlets;

import javax.servlet.annotation.WebServlet;

/**
 * here, the user is actually taking the quiz, so every question is shown and they write answers
 * at the end of the quiz there is a "submit" button, that transfers them to "QuizResult" page.
 * This servlet checks and considers the way creator has decided to display the quiz:
 * check the CreateQuiz.java for details.
 *
 */
@WebServlet(name = "Quiz",urlPatterns = "/Quiz")
public class Quiz {
}
