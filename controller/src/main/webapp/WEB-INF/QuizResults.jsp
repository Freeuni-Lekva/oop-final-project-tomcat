<%@page import="ge.edu.freeuni.models.QuizGameModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Summary</title>
    <style><%@include file="css/styles.css"%></style>
    <style>
        h1 {
            font-size: 35px;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <%
        QuizGameModel quizGame = (QuizGameModel) request.getAttribute("quizGameModel");
    %>
    <jsp:include page="UserFooter.jsp" />
    <jsp:include page="SearchUser.jsp" />
    <div class="contents">
        <div class="quiz-details">
            <h1>Your Result: <%= quizGame.getScore() %> Out of <%= quizGame.getMaxScore() %> Points!</h1>
            <form action="SendChallenge" method="get">
                <input type="hidden" name="quizId" value="<%= quizGame.getQuiz().getId()%>">
                <button class="send-challenge-btn" type="submit">Send Challenge</button>
            </form>
        </div>
    </div>
</body>
</html>
