<%@page import="ge.edu.freeuni.models.QuizModel"%>
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
            color: indigo;
        }
    </style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <div class="quiz-details">
        <% QuizModel quiz = (QuizModel) request.getAttribute("quizDetails"); %>
        <h1><%= quiz.getName() %></h1>
        <div class="quiz-description">
            <%= quiz.getDescription() %>
        </div>
        <div class="owner-link">
            Quiz by <a href="<%=request.getContextPath()%>/user?username=<%=quiz.getOwner().getUsername()%>"><%=quiz.getOwner().getUsername() %></a>
        </div>
        <form action="startQuiz" method="post">
            <input type="hidden" name="quizId" value="${quiz.quizId}">
            <button class="start-quiz-btn" type="submit">Start the Quiz</button>
        </form>
    </div>
</body>
</html>
