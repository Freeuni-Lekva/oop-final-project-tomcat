package ge.edu.freeuni.servlets;


import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.ChallengeModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.responses.ChallengeResponse;
import ge.edu.freeuni.responses.NoteResponse;
import ge.edu.freeuni.services.ChallengeService;
import ge.edu.freeuni.services.UserService;
import ge.edu.freeuni.util.EntityToModelBridge;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CreateChallenge",urlPatterns = "/CreateChallenge")
public class CreateChallenge extends HttpServlet {

    private final ChallengeService challengeService = new ChallengeService();

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/CreateNewChallenge.jsp");
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }


    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Long id = (Long)session.getAttribute("id");

        ChallengeModel challengeModel = challengeService.getById(id);
        ChallengeResponse challengeResponse = challengeService.createChallenge(challengeModel);

        if(challengeResponse.isSuccess()){
            RequestDispatcher mailDispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/ChallengeSent.jsp");
            mailDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/CreateNewChallenge.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }
    }
}
