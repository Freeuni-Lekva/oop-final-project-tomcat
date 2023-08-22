package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Challenge;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.QuizGame;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.ChallengeModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.ChallengeResponse;
import ge.edu.freeuni.responses.ChallengesResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChallengeService {

    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
    private DAO<Quiz> quizDAO = DAOFactory.getInstance().getDAO(Quiz.class);
    private DAO<QuizGame> quizGameDAO = DAOFactory.getInstance().getDAO(QuizGame.class);
    private DAO<Challenge> challengeDAO = DAOFactory.getInstance().getDAO(Challenge.class);
    private final Map<Long, ChallengeModel> allChallenges;


    public ChallengeService(){
        this.allChallenges = getSentChallenges();
    }


    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public void setQuizDAO(DAO<Quiz> quizDAO) {
        this.quizDAO = quizDAO;
    }

    public void setQuizGameDAO(DAO<QuizGame> quizGameDAO) {
        this.quizGameDAO = quizGameDAO;
    }

    public void setChallengeDAO(DAO<Challenge> challengeDAO) {
        this.challengeDAO = challengeDAO;
    }

    private Map<Long, ChallengeModel> getSentChallenges() {
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

    public ChallengeResponse createChallenge(String senderUsername, String receiverUsername, Long quizId) {
        try {
            Challenge challenge = generateChallenge(senderUsername, receiverUsername, quizId);
            Long id = (Long) challengeDAO.create(challenge);
            ChallengeModel newChallenge = EntityToModelBridge.toChallengeModel(challenge);
            allChallenges.put(id, newChallenge);

            return new ChallengeResponse(true, null, newChallenge);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengeResponse(false, "Error while sending a challenge. Try again later", null);
        }
    }

    public ChallengesResponse bulkCreateChallenges(String senderUsername, String[] receiverUsernames, Long quizId) {
        try {
            List<Challenge> challenges = new ArrayList<>();
            for (String receiverUsername : receiverUsernames) {
                challenges.add(generateChallenge(senderUsername, receiverUsername, quizId));
            }
            challengeDAO.bulkCreate(challenges);
            List<ChallengeModel> newChallenges = challenges.stream()
                    .map(EntityToModelBridge::toChallengeModel).collect(Collectors.toList());
            newChallenges.forEach(challenge -> allChallenges.put(challenge.getId(), challenge));
            return new ChallengesResponse(true, null, newChallenges);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengesResponse(false, "Error while sending challenges. Try again later", null);
        }
    }

    private Challenge generateChallenge(String senderUsername, String receiverUsername, Long quizId) throws RuntimeException {
        try {
            if (senderUsername == null || senderUsername.isEmpty() || receiverUsername == null || receiverUsername.isEmpty()
                    || quizId == null) {
                throw new RuntimeException("Invalid challenge credentials");
            }

            // extract quiz
            Quiz quiz = quizDAO.read(quizId);
            if (quiz == null) {
                throw new RuntimeException("Invalid quiz parameters");
            }

            // extract sender
            List<User> potentialSenders = userDAO.getByField("username", senderUsername);
            if (potentialSenders == null || potentialSenders.size() != 1) {
                throw new RuntimeException("Invalid sender parameters");
            }
            User sender = potentialSenders.get(0);

            // extract receiver
            List<User> potentialReceivers = userDAO.getByField("username", receiverUsername);
            if (potentialReceivers == null || potentialReceivers.size() != 1) {
                throw new RuntimeException("Invalid receiver parameters");
            }
            User receiver = potentialReceivers.get(0);

            //extract best score of the sender
            String[] fields = {"quiz_id", "player_id"};
            Serializable[] values = {quiz.getId(), sender.getId()};
            String aggregateFunction = "max";
            String aggregateField = "score";
            Integer bestScore = (Integer) quizGameDAO.getByFieldsAndAggregate(fields, values, aggregateFunction, aggregateField);

            return new Challenge(quiz, sender, receiver, bestScore);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ChallengeModel getById(Long id) {
        return allChallenges.get(id);
    }

    public ChallengeResponse getChallenge(Long id) {
        try {
            Challenge challenge = challengeDAO.read(id);
            if (challenge == null) {
                return new ChallengeResponse(false, "Challenge doesn't exist", null);
            }
            ChallengeModel challengeModel = EntityToModelBridge.toChallengeModel(challenge);
            return new ChallengeResponse(true, null, challengeModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengeResponse(false, "This challenge can not be viewed right now,\\n\" +\n" +
                    "                    \"please, try again later", null);
        }
    }

    public ChallengesResponse getSentChallenges(Long senderId) {
        try {
            List<ChallengeModel> challenges = allChallenges.values().stream()
                    .filter(challenge -> Objects.equals(challenge.getSender().getId(), senderId))
                    .sorted(Comparator.comparingLong(ChallengeModel::getTimestamp).reversed())
                    .collect(Collectors.toList());
            return new ChallengesResponse(true, null, challenges);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengesResponse(false,"your sent challenges can not be viewed right now,\\n\" +\n" +
                    "                    \"please, try again later", null);
        }
    }
    public ChallengesResponse getReceivedChallenges(Long receiverId) {
        try {
            List<ChallengeModel> challenges = allChallenges.values().stream()
                    .filter(challenge -> Objects.equals(challenge.getReceiver().getId(), receiverId))
                    .sorted(Comparator.comparingLong(ChallengeModel::getTimestamp).reversed())
                    .collect(Collectors.toList());
            return new ChallengesResponse(true, null, challenges);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ChallengesResponse(false, "your received challenges can not be viewed right now,\n" +
                    "please, try again later", null);
        }
    }

    public ChallengeResponse deleteChallenge(Long challengeId) {
        List<Challenge> challengeInList = challengeDAO.getByField("id",challengeId);
        if(challengeInList == null || challengeInList.isEmpty()){
            return new ChallengeResponse(false,"the challenge could not be found",null);
        }
        try {
            challengeDAO.delete(challengeId);
            return new ChallengeResponse(true,null,EntityToModelBridge.toChallengeModel(challengeInList.get(0)));
        } catch(Exception e){
            return new ChallengeResponse(false, "was unable to access the challenge",null);
        }
    }
}
