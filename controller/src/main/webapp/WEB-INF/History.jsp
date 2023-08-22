<%@page import="java.util.List"%>
<%@page import="ge.edu.freeuni.models.QuizModel"%>
<%@page import="ge.edu.freeuni.models.QuizGameModel"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Exquizite</title>
    <style><%@include file="css/styles.css"%></style>
    <style>
        h1 {
            font-size: 40px;
            margin-bottom: 20px;
            text-align: center;
        }
        h2 {
            font-size: 30px;
            margin-top: 40px;
            margin-bottom: 20px;
            color: indigo;
        }
        h3 {
            font-size: 18px;
            margin-top: 25px;
            margin-bottom: 10px;
            color: #757575;
            font-style: italic;
        }
        li {
            border-radius: 10px;
            background-color: lavenderblush;
        }
    </style>
</head>
<body>
    <jsp:include page="SearchUser.jsp" />
    <jsp:include page="UserFooter.jsp" />
    <%
        List<QuizModel> allQuizzes = (List<QuizModel>) request.getAttribute("allQuizzes");
        List<QuizGameModel> allGames = (List<QuizGameModel>) request.getAttribute("allQuizGames");
    %>
    <div class="contents">
        <h1>Exquizite</h1>

        <div class="quiz-list">
            <div class="quiz-card">
                <h2>Your Quizzes</h2>
                <ul>
                    <%
                        if (allQuizzes != null && !allQuizzes.isEmpty()) {
                            for (QuizModel quiz : allQuizzes) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>

            <div class="quiz-card">
                <h2>Your Game History</h2>
                <ul>
                    <%
                        if (allGames != null && !allGames.isEmpty()) {
                            for (QuizGameModel game : allGames) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/QuizResults?id=<%=game.getId()%>"><%=game.getQuiz().getName()%></a></li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
