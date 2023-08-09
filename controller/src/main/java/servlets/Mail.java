package servlets;

import javax.servlet.annotation.WebServlet;

/**
 * on this page, the user sees the list of all notes they have received.
 * they can click on any of them to view the note.
 */
@WebServlet(name = "Mail",urlPatterns = "/Mail")
public class Mail {
}
