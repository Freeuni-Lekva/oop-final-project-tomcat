package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Challenge;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.ChallengeModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.ChallengeResponse;
import ge.edu.freeuni.responses.ChallengesResponse;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChallengeService {

    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
    private DAO<Quiz> quizDAO = DAOFactory.getInstance().getDAO(Quiz.class);
    private DAO<Challenge> challengeDAO = DAOFactory.getInstance().getDAO(Challenge.class);
    private final Map<Long, ChallengeModel> allChallenges;


    public ChallengeService(){
        this.allChallenges = getAllChallenges();
    }


    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public void setQuizDAO(DAO<Quiz> quizDAO) {
        this.quizDAO = quizDAO;
    }

    public void setChallengeDAO(DAO<Challenge> challengeDAO) {
        this.challengeDAO = challengeDAO;
    }

    private Map<Long, ChallengeModel> getAllChallenges() {
        try {
            List<Challenge> challenges = challengeDAO.getAll();
            return challenges.stream()
                    .map(EntityToModelBridge::toChallengeModel)
                    .collect(Collectors.toMap(ChallengeModel::getId, Function.identity()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public ChallengeResponse createChallenge(ChallengeModel newChallenge) {
        try {
            if (newChallenge.getSender() == null
                    || newChallenge.getQuiz() == null || newChallenge.getReceiver() == null) {
                return new ChallengeResponse(false, "Invalid challenge credentials", newChallenge);
            }
            Challenge challenge = ModelToEntityBridge.toChallengeEntity(newChallenge);
            Long id = (Long) challengeDAO.create(challenge);
            allChallenges.put(id, newChallenge);
            return new ChallengeResponse(true, null, newChallenge);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengeResponse(false, e.getMessage(), newChallenge);
        }
    }

    public ChallengeModel getById(Long id){
        ChallengeModel challengeModel = allChallenges.get(id);

        return challengeModel;
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

    public ChallengesResponse getAllChallenges(Long senderId) {
        try {
            List<ChallengeModel> challenges = allChallenges.values().stream()
                    .filter(challenge -> Objects.equals(challenge.getSender().getId(), senderId))
                    .collect(Collectors.toList());
            return new ChallengesResponse(true, null, challenges);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengesResponse(false, e.getMessage(), null);
        }
    }
}
