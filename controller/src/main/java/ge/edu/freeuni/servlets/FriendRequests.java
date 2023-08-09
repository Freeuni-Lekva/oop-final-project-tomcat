package ge.edu.freeuni.servlets;

import javax.servlet.annotation.WebServlet;

/**
 * this page shows every friend request of the user,
 * a user should be able to click on the link and see the senders profile
 * and they should also be able to accept or reject the request.
 */
@WebServlet(name = "FriendRequests",urlPatterns = "/FriendRequests")
public class FriendRequests {
}
