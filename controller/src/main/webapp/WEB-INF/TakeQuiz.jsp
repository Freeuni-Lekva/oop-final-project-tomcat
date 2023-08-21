<%@page import="ge.edu.freeuni.models.QuizModel"%>
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
            QuizModel quizModel = (QuizModel) request.getAttribute("quizModel");
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
                    <p class="quiz-question-text"><%= question.getQuestion() %></p>
                    <div class="quiz-options">
                        <%
                            if (question.getQuestionType() == QuestionType.QUESTION_RESPONSE) {
                        %>
                        <input class="quiz-response-input" type="text" name="response_<%= question.getId() %>" required>
                        <%
                            } else if (question.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
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
                        <input class="quiz-response-input" type="text" name="response_<%= question.getId() %>" required style="width: 97.6%">
                        <img class="quiz-image" src="<%= question.getImageUrl() %>" alt="Image">
                        <%
                            }
                        %>
                    </div>
                </div>
                <%
                        }
                    }
                %>
                <button class="finish-quiz-button" type="submit">Finish Quiz</button>
            </form>
        </div>
    </div>
</body>
</html>
