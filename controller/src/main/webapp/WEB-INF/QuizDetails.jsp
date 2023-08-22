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
        }
    </style>
</head>
<body>
    <%
        QuizModel quiz = (QuizModel) request.getAttribute("quizDetails");
    %>
    <jsp:include page="UserFooter.jsp" />
    <jsp:include page="SearchUser.jsp" />
    <div class="contents">
        <div class="quiz-details">
            <h1><%= quiz.getName() %></h1>
            <div class="quiz-description">
                <%= quiz.getDescription() %>
            </div>
            <div class="owner-link">
                Quiz by <a href="<%=request.getContextPath()%>/user?username=<%=quiz.getOwner().getUsername()%>"><%=quiz.getOwner().getUsername() %></a>
            </div>
            <form action="TakeQuiz" method="post">
                <input type="hidden" name="quizId" value="<%= quiz.getId()%>">
                <button class="start-quiz-btn" type="submit">Start the Quiz</button>
            </form>
            <%
                if("TRUE".equals(quiz.isPracticeMode())) {
            %>
            <form action="TakeQuiz" method="post">
                <input type="hidden" name="quizId" value="<%= quiz.getId()%>">
                <input type="hidden" name="practice" value="<%= true%>">
                <button class="start-quiz-btn" type="submit">Start the Quiz in Practice Mode</button>
            </form>
            <%
                }
            %>
            <form action="SendChallenge" method="get">
                <input type="hidden" name="quizId" value="<%= quiz.getId()%>">
                <button class="send-challenge-btn" type="submit">Send Challenge</button>
            </form>
        </div>
    </div>
</body>
</html>
