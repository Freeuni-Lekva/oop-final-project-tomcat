package servlets;

import javax.servlet.annotation.WebServlet;

/**
 * the user sees the whole content of the note, there should be a "reply" bytton on the screen
 * and by clicking on that they can create a new note and reply to that user.
 */
@WebServlet(name = "Note",urlPatterns = "/Note")
public class Note {
}
