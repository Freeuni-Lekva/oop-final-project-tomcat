package servlets;

import javax.servlet.annotation.WebServlet;

/**
 * this page should show all the friends of the user and
 * the user must be able to either send mail, unfriend, send challenge.
 * (any time a user is listed it should be a link to that user's profile)
 */
@WebServlet(name = "Friends",urlPatterns = "/Friends")
public class Friends {
}
