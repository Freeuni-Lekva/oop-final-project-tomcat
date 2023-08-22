<%@page import="ge.edu.freeuni.models.QuizModel"%>
<%@page import="ge.edu.freeuni.models.QuizGameModel"%>
<%@page import="ge.edu.freeuni.models.QuestionModel"%>
<%@page import="ge.edu.freeuni.models.AnswerModel"%>
<%@page import="ge.edu.freeuni.enums.QuestionType"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Play Quiz</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <jsp:include page="SearchUser.jsp" />
    <jsp:include page="UserFooter.jsp" />
    <div class="quiz-container">
        <%
            QuizGameModel quizGameModel = (QuizGameModel) request.getAttribute("quizGameModel");
            QuizModel quizModel = quizGameModel.getQuiz();
        %>
        <h1 style="margin-bottom: 1px;"><%=quizModel.getName()%></h1>
        <h2><%=quizModel.getQuestions().size()%> Questions</h2>
        <div class="quiz-content">
            <form action="SubmitQuiz" method="post">
                <%
                    if (quizModel != null) {
                        List<QuestionModel> questionList = quizModel.getQuestions();
                        for (QuestionModel question : questionList) {
                %>
                <div class="quiz-question">

                    <div class="quiz-options">
                        <%
                            if (question.getQuestionType() == QuestionType.QUESTION_RESPONSE) {
                        %>
                        <p class="quiz-question-text"><%= question.getQuestion() %></p>
                        <input class="quiz-response-input" type="text" name="response_<%= question.getId() %>" required>
                        <%
                            } else if (question.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
                        %>
                        <p class="quiz-question-text"><%= question.getQuestion() %></p>
                        <%
                                List<AnswerModel> answerList = question.getAnswers();
                                for (AnswerModel answer : answerList) {
                        %>
                        <label class="quiz-option">
                            <input type="radio" name="response_<%= question.getId() %>" value="<%= answer.getId() %>">
                            <%= answer.getAnswer() %>
                        </label>
                        <%
                                }
                            } else if (question.getQuestionType() == QuestionType.PICTURE_RESPONSE) {
                        %>
                        <p class="quiz-question-text"><%= question.getQuestion() %></p>
                        <input class="quiz-response-input" type="text" name="response_<%= question.getId() %>" required style="width: 97.6%">
                        <img class="quiz-image" src="<%= question.getImageUrl() %>" alt="Image">
                        <%
                            } else if (question.getQuestionType() == QuestionType.FILL_IN) {
                                int n = question.getAnswers().get(0).getAnswer().length();
                                n = n + n / 2 +1;
                                String placeholder = "";
                                for (int i = 0; i < n; i++) placeholder += '_';
                        %>
                        <p class="quiz-question-text"><%= question.getBeforeBlank() + " " + placeholder + " " + question.getAfterBlank() %></p>
                        <input class="quiz-response-input" type="text" name="response_<%= question.getId() %>" required>
                        <%
                            }
                        %>
                    </div>
                </div>
                <%
                        }
                    }
                %>
                <input type="hidden" name="quizGameId" value="<%= quizGameModel.getId() %>">
                <input type="hidden" name="quizId" value="<%= quizModel.getId() %>">
                <button class="finish-quiz-button" type="submit">Finish Quiz</button>
            </form>
        </div>
    </div>
</body>
</html>
