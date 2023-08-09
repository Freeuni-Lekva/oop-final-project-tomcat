package servlets;

import javax.servlet.annotation.WebServlet;

/**
 * on this pages user sees all the challenges other people have sent to him.
 * there are links for each challenge.
 * each one of them will have "start the quiz"  button which will automatically transfer the user to the quiz page
 * and the quiz will start,
 * there should also be a decline button, so the challenge will no longer appear in this list,
 * the quiz name should also be a link to the summary of the quz so the user will be able to see the summary
 * before accepting ro declining the challenge.
 */
@WebServlet(name = "Challenges",urlPatterns = "/Challenges")
public class Challenges {
}
