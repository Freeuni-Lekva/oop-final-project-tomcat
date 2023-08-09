package ge.edu.freeuni.servlets;

import javax.servlet.annotation.WebServlet;

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
@WebServlet(name = "QuizSummary",urlPatterns = "/QuizSummary")
public class QuizSummary {
}
