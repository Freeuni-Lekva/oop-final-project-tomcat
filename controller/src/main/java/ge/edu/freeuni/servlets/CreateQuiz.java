package ge.edu.freeuni.servlets;

import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.enums.QuestionType;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.responses.QuestionResponse;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.services.QuestionService;
import ge.edu.freeuni.services.QuizService;
import ge.edu.freeuni.services.UserService;
import ge.edu.freeuni.util.ModelToEntityBridge;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * on this page the user is able to create a quiz:
 *
 * Random Questions—Allow the creator to set the quiz to either randomize the order of
 * the questions or to always present them in the same order.
 *
 * One Page vs. Multiple Pages—Allow the quiz writer to determine if all the questions
 * should appear on a single webpage, with a single submit button, or if the quiz should
 * display a single question allow the user to submit the answer, then display another
 * question. The one-question-per-page approach will work well for a flash-card style
 * quiz, where the website flashes up an image or photograph and asks for a response,
 * followed by a new page with a new image. Single-page quizzes will be good for
 * most other quiz types.
 *
 * Immediate Correction—For multiple page quizzes, this setting determines whether the
 * user will receive immediate feedback on an answer, or if the quiz will only be graded
 * once all the questions have been seen and responded to. The immediate correction
 *
 * option will work in conjunction with picture-response questions to create a flash-
 * card type quiz. The computer will bring up a flash card (i.e., a picture) the user will
 * respond with the answer and the computer will immediately provide feedback on
 * whether the answer was correct or not.
 *
 * Practice Mode—The quiz author can choose whether or not the quiz can be taken in
 * practice mode. This possible feature is described in the extension section.
 */
@WebServlet(name = "CreateQuiz",urlPatterns = "/CreateQuiz")
public class CreateQuiz extends HttpServlet {
    private final QuestionService questionService = new QuestionService();
    private final QuizService quizService = new QuizService();
    private final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateQuiz.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String randomizeQuestion = request.getParameter("randomizeQuestions") == null ? Bool.FALSE.name() : Bool.TRUE.name();
        String onePage = request.getParameter("onePage") == null ? Bool.FALSE.name() : Bool.TRUE.name();
        String immediateCorrection = request.getParameter("immediateCorrection") == null ? Bool.FALSE.name() : Bool.TRUE.name();
        String practiceMode = request.getParameter("practiceMode") == null ? Bool.FALSE.name() : Bool.TRUE.name();

        String quizTitle = request.getParameter("quizTitle");
        String quizDescription = request.getParameter("quizDescription");
        Integer numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));

        HttpSession session = request.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");

        QuestionType questionType;
        String questionText;

        Quiz quiz = quizService.createQuizEntity(randomizeQuestion,onePage,immediateCorrection,practiceMode,quizTitle,quizDescription,currentUserId);

        if(quiz == null){
            request.setAttribute("errorMessage","this user is currently unable to create this quiz");
            RequestDispatcher quizDisptcher = request.getRequestDispatcher(
                    "WEB-INF/CreateQuiz.jsp");
            quizDisptcher.forward(request,response);
        }

        List<QuestionModel> questionModelList = new ArrayList<>();
        for(int i = 0 ; i< numberOfQuestions;i ++){
            questionType = QuestionType.getByValue(Integer.getInteger(request.getParameter("questionTypeEnum"+i)));
                QuestionResponse questionResponse;
            if(questionType.equals(QuestionType.QUESTION_RESPONSE)){
                questionText = request.getParameter("questionText"+i);
                String answer = request.getParameter("correctAnswer"+i);
                questionResponse = questionService.addResponseQuestion(quiz,questionText,answer);
            }else if(questionType.equals(QuestionType.FILL_IN)){
                String beforeGap = request.getParameter("beforeGap"+i);
                String answer = request.getParameter("correctAnswer"+i);
                String afterGap = request.getParameter("afterGap");
                questionResponse = questionService.addFillInQuestion(quiz,beforeGap,answer,afterGap);
            }else if(questionType.equals(QuestionType.MULTIPLE_CHOICE)){
                questionText = request.getParameter("questionText"+i);
                Integer numOfPossibleAnswers = Integer.getInteger(request.getParameter("numberOfAnswers"+i));
                List<String> answers = new ArrayList<>();
                for( int j = 0 ;j < numOfPossibleAnswers; j++){
                    answers.add((request.getParameter("multipleChiceAnswer"+i+"."+j)));
                }
                Integer indexOfCorrectAnswer =Integer.getInteger(request.getParameter("indexOfCorrectAnswer"+i));
                questionResponse = questionService.addMultipleChoiceQuestion(quiz,questionText, answers, indexOfCorrectAnswer);
            }else{
                questionText = request.getParameter("questionText"+i);
                String answer = request.getParameter("correctAnswer"+i);
                questionResponse = questionService.addImageResonseQuestion(quiz,questionText,answer);
            }
            if(!questionResponse.isSuccess()){
                request.setAttribute("errorMessage",questionResponse.getErrorMessage());
                RequestDispatcher quizDisptcher = request.getRequestDispatcher(
                        "WEB-INF/CreateQuiz.jsp");
                quizDisptcher.forward(request,response);
            }
            questionModelList.add(questionResponse.getQuestionModel());
        }
        List<Question> questions = questionModelList.stream()
                        .map(q -> ModelToEntityBridge.toQuestionEntity(q))
                                .collect(Collectors.toList());
        quiz.setQuestions(questions);
        quiz.setCreationTimestamp(System.currentTimeMillis()/1000L);

        QuizResponse quizResponse = quizService.createQuiz(quiz);
        if(!quizResponse.isSuccess()){
            request.setAttribute("errorMessage",quizResponse.getErrorMessage());
            RequestDispatcher quizDisptcher = request.getRequestDispatcher(
                    "WEB-INF/CreateQuiz.jsp");
            quizDisptcher.forward(request,response);
        }
    }
}
