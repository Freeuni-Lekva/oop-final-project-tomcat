package ge.edu.freeuni.models;

import ge.edu.freeuni.entities.Challenge;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.ChallengeResponse;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

public class ChallengeService {

    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
    private DAO<Quiz> quizDAO = DAOFactory.getInstance().getDAO(Quiz.class);
    private DAO<Challenge> challengeDAO = DAOFactory.getInstance().getDAO(Challenge.class);


    public ChallengeService(){}


    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public void setQuizDAO(DAO<Quiz> quizDAO) {
        this.quizDAO = quizDAO;
    }

    public void setChallengeDAO(DAO<Challenge> challengeDAO) {
        this.challengeDAO = challengeDAO;
    }



    public ChallengeResponse getChallenge(Long id){
        try{
            Challenge challenge = challengeDAO.read(id);
            if (challenge == null) {
                return new ChallengeResponse(false, "Challenge doesn't exist", null);
            }
            ChallengeModel challengeModel = EntityToModelBridge.toChallengeModel(challenge);
            return new ChallengeResponse(true, null, challengeModel);
        }catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengeResponse(false, e.getMessage(), null);
        }
    }
}
