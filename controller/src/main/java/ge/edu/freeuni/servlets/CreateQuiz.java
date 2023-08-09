package servlets;

import javax.servlet.annotation.WebServlet;

/**
 * on this page the user is able to create a quiz:
 *
 * Random Questions—Allow the creator to set the quiz to either randomize the order of
 * the questions or to always present them in the same order.
 *
 * One Page vs. Multiple Pages—Allow the quiz writer to determine if all the questions
 * should appear on a single webpage, with a single submit button, or if the quiz should
 * display a single question allow the user to submit the answer, then display another
 * question. The one-question-per-page approach will work well for a flash-card style
 * quiz, where the website flashes up an image or photograph and asks for a response,
 * followed by a new page with a new image. Single-page quizzes will be good for
 * most other quiz types.
 *
 * Immediate Correction—For multiple page quizzes, this setting determines whether the
 * user will receive immediate feedback on an answer, or if the quiz will only be graded
 * once all the questions have been seen and responded to. The immediate correction
 *
 * option will work in conjunction with picture-response questions to create a flash-
 * card type quiz. The computer will bring up a flash card (i.e., a picture) the user will
 * respond with the answer and the computer will immediately provide feedback on
 * whether the answer was correct or not.
 *
 * Practice Mode—The quiz author can choose whether or not the quiz can be taken in
 * practice mode. This possible feature is described in the extension section.
 */
@WebServlet(name = "CreateQuiz",urlPatterns = "/CreateQuiz")
public class CreateQuiz {
}
