package ge.edu.freeuni;

import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.QuizGame;
import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.enums.QuestionType;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.services.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class QuizServiceTest {
    private QuizService quizService;

    @Mock
    private DAO<User> mockUserDAO;

    @Mock
    private DAO<Quiz> mockQuizDAO;

    @Mock
    private DAO<QuizGame> mockQuizGameDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        quizService = new QuizService();
        quizService.setUserDAO(mockUserDAO);
        quizService.setQuizDAO(mockQuizDAO);
        quizService.setQuizGameDAO(mockQuizGameDAO);
    }

    @Test
    public void testCreateValidQuiz() {
        QuizModel quizModel = new QuizModel(1L);
        quizModel.setName("Test Quiz");
        quizModel.setDescription("A test quiz");

        List<QuestionModel> questions = new ArrayList<>();
        questions.add(new QuestionModel(1L, 1L, QuestionType.QUESTION_RESPONSE, new ArrayList<>(), null));
        quizModel.setQuestions(questions);

        UserModel owner = new UserModel(1L, "username", "firstname", "lastname", "password");
        quizModel.setOwner(owner);

        when(mockQuizDAO.create(any(Quiz.class))).thenReturn(1L);
        ServiceActionResponse response = quizService.createQuiz(quizModel);
        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testGetQuiz() {
        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setName("Sample Quiz");
        quiz.setQuestions(new ArrayList<>());
        User user = new User();
        quiz.setOwner(user);
        when(mockQuizDAO.read(1L)).thenReturn(quiz);
        when(mockUserDAO.read(any())).thenReturn(user);
        QuizResponse response = quizService.getQuiz(1L);
        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessage());
        assertNotNull(response.getQuiz());
        assertEquals("Sample Quiz", response.getQuiz().getName());
    }
}
