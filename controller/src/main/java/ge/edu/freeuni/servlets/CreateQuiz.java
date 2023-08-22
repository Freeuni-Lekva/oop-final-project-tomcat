package ge.edu.freeuni.servlets;

import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.enums.QuestionType;
import ge.edu.freeuni.models.AnswerModel;
import ge.edu.freeuni.models.QuestionModel;
import ge.edu.freeuni.models.QuizModel;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.provider.ServiceFactory;
import ge.edu.freeuni.responses.QuizResponse;
import ge.edu.freeuni.services.QuizService;
import ge.edu.freeuni.services.UserService;
import ge.edu.freeuni.util.StringUtils;

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

/**
 * on this page the user is able to create a quiz:
 * <p>
 * Random Questions—Allow the creator to set the quiz to either randomize the order of
 * the questions or to always present them in the same order.
 * <p>
 * One Page vs. Multiple Pages—Allow the quiz writer to determine if all the questions
 * should appear on a single webpage, with a single submit button, or if the quiz should
 * display a single question allow the user to submit the answer, then display another
 * question. The one-question-per-page approach will work well for a flash-card style
 * quiz, where the website flashes up an image or photograph and asks for a response,
 * followed by a new page with a new image. Single-page quizzes will be good for
 * most other quiz types.
 * <p>
 * Immediate Correction—For multiple page quizzes, this setting determines whether the
 * user will receive immediate feedback on an answer, or if the quiz will only be graded
 * once all the questions have been seen and responded to. The immediate correction
 * <p>
 * option will work in conjunction with picture-response questions to create a flash-
 * card type quiz. The computer will bring up a flash card (i.e., a picture) the user will
 * respond with the answer and the computer will immediately provide feedback on
 * whether the answer was correct or not.
 * <p>
 * Practice Mode—The quiz author can choose whether or not the quiz can be taken in
 * practice mode. This possible feature is described in the extension section.
 */
@WebServlet(name = "CreateQuiz", urlPatterns = "/CreateQuiz")
public class CreateQuiz extends HttpServlet {
    private final QuizService quizService = ServiceFactory.getInstance().getService(QuizService.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateQuiz.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String randomizeQuestion = request.getParameter("randomizeQuestions") == null ? Bool.FALSE.name() : Bool.TRUE.name();
        String onePage = request.getParameter("onePage") == null ? Bool.FALSE.name() : Bool.TRUE.name();
        String immediateCorrection = request.getParameter("immediateCorrection") == null ? Bool.FALSE.name() : Bool.TRUE.name();
        String practiceMode = request.getParameter("practiceMode") == null ? Bool.FALSE.name() : Bool.TRUE.name();

        String quizTitle = request.getParameter("quizTitle");
        String quizDescription = request.getParameter("quizDescription");

        int numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
        if (numberOfQuestions == 0) {
            request.setAttribute("errorMessage", "no questions written");
            RequestDispatcher quizDisptcher = request.getRequestDispatcher(
                    "WEB-INF/ErrorPage.jsp");
            quizDisptcher.forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Long currentUserId = (Long) session.getAttribute("currentUserId");

        QuizModel quizModel = new QuizModel(null, quizTitle, quizDescription, null, null, randomizeQuestion, onePage, immediateCorrection, practiceMode, null);

        List<QuestionModel> questionModelList = new ArrayList<>();
        QuestionType questionType;
        String questionText;
        List<AnswerModel> answerModels;
        List<String> correctAnswers;
        String answer;
        int points;
        for (int i = 0; i < numberOfQuestions; i++) {
            if (request.getParameter("questionTypeEnum" + i) == null) {
                request.setAttribute("errorMessage", "you have to choose a question type for the question " + i);
                RequestDispatcher quizDispatcher = request.getRequestDispatcher(
                        "WEB-INF/ErrorPage.jsp");
                quizDispatcher.forward(request, response);
                return;
            }

            questionType = QuestionType.getByValue(Integer.parseInt(request.getParameter("questionTypeEnum" + i)));
            QuestionModel questionModel = null;

            switch (questionType) {
                case QUESTION_RESPONSE:
                    questionText = request.getParameter("questionText" + i);
                    answer = request.getParameter("answerText" + i);
                    points = Integer.parseInt(request.getParameter("points" + i));

                    questionModel = new QuestionModel(null);
                    questionModel.setQuestion(questionText);
                    questionModel.setQuestionType(questionType);

                    answerModels = new ArrayList<>();
                    correctAnswers = StringUtils.splitAndTrim(answer);
                    for (String correctAnswer : correctAnswers) {
                        answerModels.add(new AnswerModel(null, null, correctAnswer, true, points));
                    }
                    questionModel.setAnswers(answerModels);
                    break;
                case FILL_IN:
                    String beforeGap = request.getParameter("beforeGap" + i);
                    answer = request.getParameter("answer" + i);
                    String afterGap = request.getParameter("afterGap" + i);
                    points = Integer.parseInt(request.getParameter("points" + i));

                    questionModel = new QuestionModel(null);
                    questionModel.setBeforeBlank(beforeGap);
                    questionModel.setAfterBlank(afterGap);
                    questionModel.setQuestionType(questionType);

                    answerModels = new ArrayList<>();
                    correctAnswers = StringUtils.splitAndTrim(answer);
                    for (String correctAnswer : correctAnswers) {
                        answerModels.add(new AnswerModel(null, null, correctAnswer, true, points));
                    }
                    questionModel.setAnswers(answerModels);
                    break;
                case MULTIPLE_CHOICE:
                    questionText = request.getParameter("questionText" + i);
                    int numOfPossibleAnswers = Integer.parseInt(request.getParameter("numOfOptions" + i));
                    points = Integer.parseInt(request.getParameter("points" + i));

                    questionModel = new QuestionModel(null);
                    questionModel.setQuestion(questionText);
                    questionModel.setQuestionType(questionType);
                    answerModels = new ArrayList<>();

                    int indexOfCorrectAnswer = Integer.parseInt(request.getParameter("correctOption" + i));
                    for (int j = 1; j <= numOfPossibleAnswers; j++) {
                        answer = request.getParameter("option" + i + "_text" + j);
                        answerModels.add(new AnswerModel(null, null, answer, indexOfCorrectAnswer == j, indexOfCorrectAnswer == j ? points : 0));
                    }
                    questionModel.setAnswers(answerModels);
                    break;
                case PICTURE_RESPONSE:
                    questionText = request.getParameter("questionText" + i);
                    answer = request.getParameter("answerText" + i);
                    String imageUrl = request.getParameter("imageUrl" + i);
                    points = Integer.parseInt(request.getParameter("points" + i));

                    questionModel = new QuestionModel(null);
                    questionModel.setQuestion(questionText);
                    questionModel.setQuestionType(questionType);
                    questionModel.setImageUrl(imageUrl);

                    answerModels = new ArrayList<>();
                    correctAnswers = StringUtils.splitAndTrim(answer);
                    for (String correctAnswer : correctAnswers) {
                        answerModels.add(new AnswerModel(null, null, correctAnswer, true, points));
                    }
                    questionModel.setAnswers(answerModels);
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid question type ");
                    RequestDispatcher quizDispatcher = request.getRequestDispatcher(
                            "WEB-INF/ErrorPage.jsp");
                    quizDispatcher.forward(request, response);
                    return;
            }

            questionModelList.add(questionModel);
        }
        quizModel.setQuestions(questionModelList);
        QuizResponse quizResponse = quizService.createQuiz(quizModel, currentUserId);

        if (!quizResponse.isSuccess()) {
            request.setAttribute("errorMessage", quizResponse.getErrorMessage());
            RequestDispatcher quizDispatcher = request.getRequestDispatcher(
                    "WEB-INF/ErrorPage.jsp");
            quizDispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/quizdetails?id=" + quizResponse.getQuiz().getId());
        }
    }
}
