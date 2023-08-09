package ge.edu.freeuni.servlets;

import javax.servlet.annotation.WebServlet;

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
@WebServlet(name = "Homepage",urlPatterns = "/Homepage")
public class Homepage {
}
