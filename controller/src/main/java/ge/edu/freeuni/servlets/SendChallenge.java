package ge.edu.freeuni.servlets;


import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.AllFriendshipsResponse;
import ge.edu.freeuni.responses.ChallengesResponse;
import ge.edu.freeuni.services.ChallengeService;
import ge.edu.freeuni.services.FriendshipService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(name = "SendChallenge", urlPatterns = "/sendChallenge")
public class SendChallenge extends HttpServlet {

    private final ChallengeService challengeService = ServiceFactory.getInstance().getService(ChallengeService.class);
    private final FriendshipService friendshipService = ServiceFactory.getInstance().getService(FriendshipService.class);

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Long currentUserId = (Long) httpServletRequest.getSession().getAttribute("currentUserId");
        Long quizId = null;
        try {
            quizId = Long.parseLong(httpServletRequest.getParameter("quizId"));
        } catch (NumberFormatException e) {
            httpServletRequest.setAttribute("errorMessage", "Invalid URL format");
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }

        AllFriendshipsResponse allFriendshipsResponse = friendshipService.getAllFriends(currentUserId);
        if (!allFriendshipsResponse.isSuccess()) {
            httpServletRequest.setAttribute("errorMessage", allFriendshipsResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        List<String> allFriends = allFriendshipsResponse.getAllFriends().stream()
                .map(friendship ->
                        (Objects.equals(friendship.getUser1().getId(), currentUserId) ? friendship.getUser2().getUsername() : friendship.getUser1().getUsername()))
                .collect(Collectors.toList());

        httpServletRequest.setAttribute("quizId", quizId);
        httpServletRequest.setAttribute("friendList", allFriends);
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/CreateNewChallenge.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }


    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String senderUsername = (String) session.getAttribute("currentUser");
        String[] receiverUsernames = httpServletRequest.getParameterValues("friendUsername");
        Long quizId = null;

        try {
            quizId = Long.parseLong(httpServletRequest.getParameter("quizId"));
        } catch (NumberFormatException e) {
            httpServletRequest.setAttribute("errorMessage", "Invalid URL format");
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }

        ChallengesResponse challengeResponse = challengeService.bulkCreateChallenges(senderUsername, receiverUsernames, quizId);

        if (challengeResponse.isSuccess()) {
            httpServletRequest.setAttribute("quizId", quizId);
            RequestDispatcher mailDispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/ChallengeSent.jsp");
            mailDispatcher.forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.setAttribute("errorMessage", challengeResponse.getErrorMessage());
            httpServletRequest.getRequestDispatcher("WEB-INF/ErrorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }

    }
}
