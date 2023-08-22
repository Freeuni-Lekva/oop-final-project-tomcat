package dao;

import ge.edu.freeuni.entities.Answer;
import ge.edu.freeuni.entities.Challenge;
import ge.edu.freeuni.entities.FriendRequest;
import ge.edu.freeuni.entities.Friendship;
import ge.edu.freeuni.entities.Note;
import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.QuizGame;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.enums.QuestionType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {
    @AfterAll
    public static void cleanup() {
        File dbFile = new File("quizdb.db");
        if (dbFile.exists()) {
            boolean deleted = dbFile.delete();
            assertTrue(deleted);
        }
    }

    @Test
    public void testUser() {
        DAO<User> dao = DAOFactory.getInstance().getDAO(User.class);
        User user;

        // test create and read
        Serializable userId = dao.create(new User("username", "first name", "last name", "password"));
        user = dao.read(userId);
        assertEquals("username", user.getUsername());

        // test update
        user.setFirstname("first name 1");
        dao.update(user);
        user = dao.read(userId);
        assertEquals("username", user.getUsername());
        assertEquals("first name 1", user.getFirstname());

        // test delete
        dao.delete(userId);
        user = dao.read(userId);
        assertNull(user);

        // test getAll
        dao.create(new User("username1", "first name", "last name", "password"));
        dao.create(new User("username2", "first name", "last name", "password"));
        dao.create(new User("username3", "first name", "last name", "password"));
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
        users.forEach(
                u -> assertEquals("first name", u.getFirstname()));

        //test getByField
        users = dao.getByField("username", "username1");
        assertEquals(1, users.size());
        assertEquals("username1", users.get(0).getUsername());

        //test getByFields
        String[] fields = {"firstname", "lastname"};
        String[] values = {"first name", "last name"};

        users = dao.getByFields(fields, values, true);
        assertEquals(3, users.size());
        users.forEach(
                u -> {
                    assertEquals("first name", u.getFirstname());
                    assertEquals("last name", u.getLastname());
                });

        dao.clearTable();
    }

    /**
     * this method also tests the cascade creation relationship quiz -> questions -> question -> answers
     * when the former is persisted, the latter is also persisted if it isn't already.
     * cascade creation relationship won't be tested in other methods
     */
    @Test
    public void testQuiz() {
        DAO<Quiz> dao = DAOFactory.getInstance().getDAO(Quiz.class);
        DAO<User> userDao = DAOFactory.getInstance().getDAO(User.class);

        //region setup cascade relationships
        Serializable ownerId = userDao.create(new User("username", "first name", "last name", "password"));
        User owner = userDao.read(ownerId);

        Quiz quiz = new Quiz();

        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        Question question2 = new Question();
        questions.add(question1);
        questions.add(question2);

        List<Answer> answers1 = new ArrayList<>();
        List<Answer> answers2 = new ArrayList<>();
        Answer answer11 = new Answer();
        Answer answer12 = new Answer();
        Answer answer21 = new Answer();
        Answer answer22 = new Answer();
        answers1.add(answer11);
        answers1.add(answer12);
        answers2.add(answer21);
        answers2.add(answer22);

        answer11.setAnswer("answer");
        answer11.setAccuracy(Bool.TRUE);
        answer11.setPoints(1);
        answer11.setQuestion(question1);
        answer12.setAnswer("answer");
        answer12.setAccuracy(Bool.TRUE);
        answer12.setPoints(1);
        answer12.setQuestion(question1);
        answer21.setAnswer("answer");
        answer21.setAccuracy(Bool.TRUE);
        answer21.setPoints(1);
        answer21.setQuestion(question2);
        answer22.setAnswer("answer");
        answer22.setAccuracy(Bool.TRUE);
        answer22.setPoints(1);
        answer22.setQuestion(question2);

        question1.setQuiz(quiz);
        question1.setQuestionType(QuestionType.QUESTION_RESPONSE);
        question1.setAnswers(answers1);
        question1.setQuestion("question");
        question2.setQuiz(quiz);
        question2.setQuestionType(QuestionType.QUESTION_RESPONSE);
        question2.setAnswers(answers2);
        question2.setQuestion("question");

        quiz.setOwner(owner);
        quiz.setName("name");
        quiz.setDescription("description");
        quiz.setCreationTimestamp(System.currentTimeMillis());
        quiz.setQuestions(questions);
        //endregion

        // test create and read
        Serializable quizId = dao.create(quiz);
        quiz = dao.read(quizId);
        System.out.println(quiz.toString());
        assertEquals("name", quiz.getName());
        assertEquals("description", quiz.getDescription());
        assertEquals(quizId, quiz.getId());
        assertEquals(ownerId, quiz.getOwner().getId());

        // test update
        quiz.setQuestions(new ArrayList<>());
        dao.update(quiz);
        quiz = dao.read(quizId);
        assertFalse(quiz.getQuestions().isEmpty());

        // test delete
        dao.delete(quizId);
        quiz = dao.read(quizId);
        assertNull(quiz);

        // test getAll
        dao.create(new Quiz("name1", "description", owner, new ArrayList<>(), null, null, null, null));
        dao.create(new Quiz("name2", "description", owner, new ArrayList<>(), null, null, null, null));
        dao.create(new Quiz("name3", "description", owner, new ArrayList<>(), null, null, null, null));
        List<Quiz> quizzes = dao.getAll();
        assertEquals(3, quizzes.size());
        quizzes.forEach(
                q -> assertEquals(ownerId, q.getOwner().getId()));

        //test getByField
        quizzes = dao.getByField("name", "name1");
        assertEquals(1, quizzes.size());
        assertEquals("name1", quizzes.get(0).getName());

        //test getByFields
        String[] fields = {"description", "owner_id"};
        Serializable[] values = {"description", ownerId};

        quizzes = dao.getByFields(fields, values, true);
        assertEquals(3, quizzes.size());
        quizzes.forEach(
                q -> {
                    assertEquals("description", q.getDescription());
                    assertEquals(ownerId, q.getOwner().getId());
                });

        dao.clearTable();
        userDao.clearTable();
    }

    @Test
    public void testAnswer() {
        DAO<Answer> dao = DAOFactory.getInstance().getDAO(Answer.class);

        Answer answer;

        // test create and read
        Serializable answerId = dao.create(new Answer(null, "answer", Bool.TRUE, 1));
        answer = dao.read(answerId);
        assertEquals("answer", answer.getAnswer());

        // test update
        answer.setAnswer("answer1");
        dao.update(answer);
        answer = dao.read(answerId);
        assertEquals("answer1", answer.getAnswer());

        // test delete
        dao.delete(answerId);
        answer = dao.read(answerId);
        assertNull(answer);

        // test getAll
        dao.create(new Answer(null, "answer1", Bool.TRUE, 1));
        dao.create(new Answer(null, "answer2", Bool.TRUE, 1));
        dao.create(new Answer(null, "answer3", Bool.TRUE, 1));
        List<Answer> answers = dao.getAll();
        assertEquals(3, answers.size());
        answers.forEach(
                a -> assertEquals(1, a.getPoints()));

        //test getByField
        answers = dao.getByField("answer", "answer1");
        assertEquals(1, answers.size());
        assertEquals("answer1", answers.get(0).getAnswer());

        //test getByFields
        String[] fields = {"accuracy", "points"};
        Serializable[] values = {Bool.TRUE, 1};

        answers = dao.getByFields(fields, values, true);
        assertEquals(3, answers.size());
        answers.forEach(
                a -> {
                    assertEquals(Bool.TRUE, a.getAccuracy());
                    assertEquals(1, a.getPoints());
                });

        dao.clearTable();
    }

    @Test
    public void testQuestion() {
        DAO<Question> dao = DAOFactory.getInstance().getDAO(Question.class);

        Question question;

        // test create and read
        Serializable questionId = dao.create(new Question(null, "question", null, null, QuestionType.QUESTION_RESPONSE, null, null));
        question = dao.read(questionId);
        assertEquals(QuestionType.QUESTION_RESPONSE, question.getQuestionType());

        // test update
        question.setImageUrl("image url");
        dao.update(question);
        question = dao.read(questionId);
        assertEquals("image url", question.getImageUrl());

        // test delete
        dao.delete(questionId);
        question = dao.read(questionId);
        assertNull(question);

        // test getAll
        dao.create(new Question(null, "question", null, null, QuestionType.QUESTION_RESPONSE, null, "imageUrl1"));
        dao.create(new Question(null, "question", null, null, QuestionType.QUESTION_RESPONSE, null, "image url"));
        dao.create(new Question(null, "question", null, null, QuestionType.QUESTION_RESPONSE, null, "image url"));
        List<Question> questions = dao.getAll();
        assertEquals(3, questions.size());
        questions.forEach(
                q -> assertEquals(QuestionType.QUESTION_RESPONSE, q.getQuestionType()));

        //test getByField
        questions = dao.getByField("image_url", "imageUrl1");
        assertEquals(1, questions.size());
        assertEquals("imageUrl1", questions.get(0).getImageUrl());

        //test getByFields
        String[] fields = {"question_type", "image_url"};
        Serializable[] values = {QuestionType.QUESTION_RESPONSE.toString(), "image url"};

        questions = dao.getByFields(fields, values, true);
        assertEquals(2, questions.size());
        questions.forEach(
                q -> {
                    assertEquals(QuestionType.QUESTION_RESPONSE, q.getQuestionType());
                    assertEquals("image url", q.getImageUrl());
                });

        dao.clearTable();
    }

    @Test
    public void testFriendShip() {
        DAO<Friendship> dao = DAOFactory.getInstance().getDAO(Friendship.class);
        DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);

        Friendship friendship;
        Serializable user1Id = userDAO.create(new User("username1", "firstname1", "lastname1", "password1"));
        User user1 = userDAO.read(user1Id);
        Serializable user2Id = userDAO.create(new User("username2", "firstname2", "lastname2", "password2"));
        User user2 = userDAO.read(user2Id);
        Serializable user3Id = userDAO.create(new User("username3", "firstname3", "lastname3", "password3"));
        User user3 = userDAO.read(user3Id);
        Serializable user4Id = userDAO.create(new User("username4", "firstname4", "lastname4", "password4"));
        User user4 = userDAO.read(user4Id);

        // test create and read
        Serializable id = dao.create(new Friendship(user1, user2));
        friendship = dao.read(id);
        assertEquals(user1Id, friendship.getFirstUser().getId());
        assertEquals(user2Id, friendship.getSecondUser().getId());

        // test update
        friendship.setSecondUser(user3);
        dao.update(friendship);
        friendship = dao.read(id);
        assertEquals(user1Id, friendship.getFirstUser().getId());
        assertEquals(user3Id, friendship.getSecondUser().getId());

        // test delete
        dao.delete(id);
        friendship = dao.read(id);
        assertNull(friendship);

        // test getAll
        dao.create(new Friendship(user2, user1));
        dao.create(new Friendship(user2, user3));
        dao.create(new Friendship(user2, user4));
        dao.create(new Friendship(user3, user1));
        dao.create(new Friendship(user4, user1));

        List<Friendship> friendships = dao.getAll();
        assertEquals(5, friendships.size());
        friendships.forEach(
                f -> assertTrue(
                        (f.getFirstUser().equals(user2) || f.getFirstUser().equals(user3) || f.getFirstUser().equals(user4))
                                && (f.getSecondUser().equals(user1) || f.getSecondUser().equals(user3) || f.getSecondUser().equals(user4))
                ));

        //test getByField
        friendships = dao.getByField("first_user", user2Id);
        assertEquals(3, friendships.size());
        friendships.forEach(
                f -> assertEquals("username2", f.getFirstUser().getUsername()));

        //test getByFields
        String[] fields = {"first_user", "second_user"};
        Serializable[] values = {user2Id, user1Id};

        friendships = dao.getByFields(fields, values, true);
        assertEquals(1, friendships.size());
        assertEquals("username2", friendships.get(0).getFirstUser().getUsername());
        assertEquals("username1", friendships.get(0).getSecondUser().getUsername());

        dao.clearTable();
        userDAO.clearTable();
    }

    @Test
    public void testFriendRequest() {
        DAO<FriendRequest> dao = DAOFactory.getInstance().getDAO(FriendRequest.class);
        DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);

        FriendRequest friendRequest;
        Serializable user1Id = userDAO.create(new User("username1", "firstname1", "lastname1", "password1"));
        User user1 = userDAO.read(user1Id);
        Serializable user2Id = userDAO.create(new User("username2", "firstname2", "lastname2", "password2"));
        User user2 = userDAO.read(user2Id);
        Serializable user3Id = userDAO.create(new User("username3", "firstname3", "lastname3", "password3"));
        User user3 = userDAO.read(user3Id);
        Serializable user4Id = userDAO.create(new User("username4", "firstname4", "lastname4", "password4"));
        User user4 = userDAO.read(user4Id);

        // test create and read
        Serializable id = dao.create(new FriendRequest(user1, user2));
        friendRequest = dao.read(id);
        assertEquals(user1Id, friendRequest.getSenderUser().getId());
        assertEquals(user2Id, friendRequest.getRecipientUser().getId());

        // test update
        friendRequest.setRecipientUser(user3);
        dao.update(friendRequest);
        friendRequest = dao.read(id);
        assertEquals(user1Id, friendRequest.getSenderUser().getId());
        assertEquals(user3Id, friendRequest.getRecipientUser().getId());

        // test delete
        dao.delete(id);
        friendRequest = dao.read(id);
        assertNull(friendRequest);

        // test getAll
        dao.create(new FriendRequest(user2, user1));
        dao.create(new FriendRequest(user2, user3));
        dao.create(new FriendRequest(user2, user4));
        dao.create(new FriendRequest(user3, user1));
        dao.create(new FriendRequest(user4, user1));

        List<FriendRequest> friendRequests = dao.getAll();
        assertEquals(5, friendRequests.size());
        friendRequests.forEach(
                f -> assertTrue(
                        (f.getSenderUser().equals(user2) || f.getSenderUser().equals(user3) || f.getSenderUser().equals(user4))
                                && (f.getRecipientUser().equals(user1) || f.getRecipientUser().equals(user3) || f.getRecipientUser().equals(user4))
                ));

        //test getByField
        friendRequests = dao.getByField("sender_user", user2Id);
        assertEquals(3, friendRequests.size());
        friendRequests.forEach(
                f -> assertEquals("username2", f.getSenderUser().getUsername()));

        //test getByFields
        String[] fields = {"sender_user", "recipient_user"};
        Serializable[] values = {user2Id, user1Id};

        friendRequests = dao.getByFields(fields, values, true);
        assertEquals(1, friendRequests.size());
        assertEquals("username2", friendRequests.get(0).getSenderUser().getUsername());
        assertEquals("username1", friendRequests.get(0).getRecipientUser().getUsername());

        dao.clearTable();
        userDAO.clearTable();
    }

    @Test
    public void testNotes() {
        DAO<Note> dao = DAOFactory.getInstance().getDAO(Note.class);
        DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);

        Note note;
        Serializable user1Id = userDAO.create(new User("username1", "firstname1", "lastname1", "password1"));
        User user1 = userDAO.read(user1Id);
        Serializable user2Id = userDAO.create(new User("username2", "firstname2", "lastname2", "password2"));
        User user2 = userDAO.read(user2Id);
        Serializable user3Id = userDAO.create(new User("username3", "firstname3", "lastname3", "password3"));
        User user3 = userDAO.read(user3Id);
        Serializable user4Id = userDAO.create(new User("username4", "firstname4", "lastname4", "password4"));
        User user4 = userDAO.read(user4Id);

        // test create and read
        Serializable id = dao.create(new Note(user1, user2, "subject", "message"));
        note = dao.read(id);
        assertEquals(user1Id, note.getFrom().getId());
        assertEquals(user2Id, note.getTo().getId());

        // test update
        note.setTo(user3);
        dao.update(note);
        note = dao.read(id);
        assertEquals(user1Id, note.getFrom().getId());
        assertEquals(user3Id, note.getTo().getId());

        // test delete
        dao.delete(id);
        note = dao.read(id);
        assertNull(note);

        // test getAll
        dao.create(new Note(user2, user1, "subject", "message"));
        dao.create(new Note(user2, user3, "subject", "message"));
        dao.create(new Note(user2, user4, "subject", "message"));
        dao.create(new Note(user3, user1, "subject1", "message1"));
        dao.create(new Note(user4, user1, "subject1", "message1"));

        List<Note> notes = dao.getAll();
        assertEquals(5, notes.size());
        notes.forEach(
                n -> assertTrue(
                        (n.getFrom().equals(user2) || n.getFrom().equals(user3) || n.getFrom().equals(user4))
                                && (n.getTo().equals(user1) || n.getTo().equals(user3) || n.getTo().equals(user4))
                ));

        //test getByField
        notes = dao.getByField("from_id", user2Id);
        assertEquals(3, notes.size());
        notes.forEach(
                n -> assertEquals("username2", n.getFrom().getUsername()));

        //test getByFields
        String[] fields = {"subject", "message"};
        Serializable[] values = {"subject", "message"};

        notes = dao.getByFields(fields, values, true);
        assertEquals(3, notes.size());
        notes.forEach(
                n -> {
                    assertEquals("subject", n.getSubject());
                    assertEquals("message", n.getMessage());
                });

        dao.clearTable();
        userDAO.clearTable();
    }

    @Test
    public void testQuizGame() {
        DAO<QuizGame> dao = DAOFactory.getInstance().getDAO(QuizGame.class);
        DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
        DAO<Quiz> quizDAO = DAOFactory.getInstance().getDAO(Quiz.class);

        QuizGame quizGame;
        Serializable userId = userDAO.create(new User("username1", "firstname1", "lastname1", "password1"));
        User user = userDAO.read(userId);

        Serializable quizId = quizDAO.create(new Quiz("name", "description", user, null, null, null, null, null));
        Quiz quiz = quizDAO.read(quizId);

        // test create and read
        Serializable quizGameId = dao.create(new QuizGame(quiz, user, 20, System.currentTimeMillis() - 20000, System.currentTimeMillis()));
        quizGame = dao.read(quizGameId);
        assertEquals(quizGame.getQuiz().getId(), quizId);

        // test update
        quizGame.setScore(30);
        dao.update(quizGame);
        quizGame = dao.read(quizGameId);
        assertEquals(30, quizGame.getScore());

        // test delete
        dao.delete(quizGameId);
        quizGame = dao.read(quizGameId);
        assertNull(quizGame);

        // test getAll
        dao.create(new QuizGame(quiz, user, 30, System.currentTimeMillis() - 30000, System.currentTimeMillis()));
        dao.create(new QuizGame(quiz, user, 40, System.currentTimeMillis() - 30000, System.currentTimeMillis()));
        dao.create(new QuizGame(quiz, user, 50, System.currentTimeMillis() - 30000, System.currentTimeMillis()));
        List<QuizGame> quizGames = dao.getAll();
        assertEquals(3, quizGames.size());
        quizGames.forEach(
                q -> assertEquals(q.getQuiz().getId(), quizId));

        //test getByField
        quizGames = dao.getByField("score", 40);
        assertEquals(1, quizGames.size());
        assertEquals(40, quizGames.get(0).getScore());

        //test getByFields
        String[] fields = {"quiz_id", "player_id"};
        Serializable[] values = {quizId, userId};

        quizGames = dao.getByFields(fields, values, true);
        assertEquals(3, quizGames.size());
        quizGames.forEach(
                q -> {
                    assertEquals(quizId, q.getQuiz().getId());
                    assertEquals(userId, q.getPlayer().getId());
                });

        dao.clearTable();
        userDAO.clearTable();
        quizDAO.clearTable();
    }

    @Test
    public void testChallenge() {
        DAO<Challenge> dao = DAOFactory.getInstance().getDAO(Challenge.class);
        DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);
        DAO<Quiz> quizDAO = DAOFactory.getInstance().getDAO(Quiz.class);

        Challenge challenge;
        Serializable user1Id = userDAO.create(new User("username1", "firstname1", "lastname1", "password1"));
        User user1 = userDAO.read(user1Id);
        Serializable user2Id = userDAO.create(new User("username2", "firstname2", "lastname2", "password2"));
        User user2 = userDAO.read(user2Id);
        Serializable user3Id = userDAO.create(new User("username3", "firstname3", "lastname3", "password3"));
        User user3 = userDAO.read(user3Id);
        Serializable user4Id = userDAO.create(new User("username4", "firstname4", "lastname4", "password4"));
        User user4 = userDAO.read(user4Id);
        Serializable quizId = quizDAO.create(new Quiz("name", "description", user1, null, null, null, null, null));
        Quiz quiz = quizDAO.read(quizId);

        // test create and read
        Serializable challengeId = dao.create(new Challenge(quiz, user1, user2, 40));
        challenge = dao.read(challengeId);
        assertEquals(challenge.getQuiz().getId(), quizId);

        // test update
        challenge.setBestScore(50);
        dao.update(challenge);
        challenge = dao.read(challengeId);
        assertEquals(50, challenge.getBestScore());

        // test delete
        dao.delete(challengeId);
        challenge = dao.read(challengeId);
        assertNull(challenge);

        // test getAll
        dao.create(new Challenge(quiz, user1, user2, 40));
        dao.create(new Challenge(quiz, user1, user3, 40));
        dao.create(new Challenge(quiz, user1, user4, 40));

        List<Challenge> challenges = dao.getAll();
        assertEquals(3, challenges.size());
        challenges.forEach(
                c -> assertEquals(c.getSender().getId(), user1Id));

        //test getByField
        challenges = dao.getByField("sender_best_score", 40);
        assertEquals(3, challenges.size());
        challenges.forEach(
                c -> assertEquals(40, c.getBestScore()));

        //test getByFields
        String[] fields = {"from_id", "sender_best_score"};
        Serializable[] values = {user1Id, 40};

        challenges = dao.getByFields(fields, values, true);
        assertEquals(3, challenges.size());
        challenges.forEach(
                c -> {
                    assertEquals(user1Id, c.getSender().getId());
                    assertEquals(40, c.getBestScore());
                });

        dao.clearTable();
        userDAO.clearTable();
        quizDAO.clearTable();
    }
}
