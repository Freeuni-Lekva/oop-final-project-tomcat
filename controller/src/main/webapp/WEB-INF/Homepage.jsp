<%@page import="java.util.List"%>
<%@page import="ge.edu.freeuni.models.QuizModel"%>
<%@page import="ge.edu.freeuni.responses.QuizzesResponse"%>

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
            font-size: 36px;
            color: indigo;
            margin-bottom: 20px;
            text-align: center;
        }
        h2 {
            font-size: 24px;
            margin-top: 40px;
            margin-bottom: 20px;
        }
        h3 {
            font-size: 15px;
            margin-top: 25px;
            margin-bottom: 10px;
            color: #757575;
            font-style: italic;
        }
        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <div class="contents">
        <h1>Exquizite</h1>

        <div class="quiz-list">
            <div class="quiz-card">
                <h2>Popular Quizzes</h2>
                <ul>
                    <%
                        QuizzesResponse popularQuizzesResponse = (QuizzesResponse) request.getAttribute("popularQuizzes");
                        List<QuizModel> mostPopularQuizzes = popularQuizzesResponse.getQuizzes();
                        if (mostPopularQuizzes != null && !mostPopularQuizzes.isEmpty()) {
                            for (QuizModel quiz : mostPopularQuizzes) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></li>
                    <%
                            }
                        } else {
                    %>
                    <h3><%= popularQuizzesResponse.getErrorMessage() %></h3>
                    <%
                        }
                    %>
                </ul>
            </div>

            <div class="quiz-card">
                <h2>Recent Quizzes</h2>
                <ul>
                    <%
                        QuizzesResponse recentQuizzesResponse = (QuizzesResponse) request.getAttribute("recentQuizzes");
                        List<QuizModel> mostRecentQuizzes = recentQuizzesResponse.getQuizzes();
                        if (mostRecentQuizzes != null && !mostRecentQuizzes.isEmpty()) {
                            for (QuizModel quiz : mostRecentQuizzes) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></li>
                    <%
                            }
                        } else {
                    %>
                    <h3><%= recentQuizzesResponse.getErrorMessage() %></h3>
                    <%
                        }
                    %>
                </ul>
            </div>

            <div class="quiz-card">
                <h2>Your Recent Quizzes</h2>
                <ul>
                    <%
                        QuizzesResponse userRecentQuizzesResponse = (QuizzesResponse) request.getAttribute("userRecentQuizzes");
                        List<QuizModel> userRecentQuizzes = userRecentQuizzesResponse.getQuizzes();
                        if (userRecentQuizzes != null && !userRecentQuizzes.isEmpty()) {
                            for (QuizModel quiz : userRecentQuizzes) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></li>
                    <%
                            }
                        } else {
                    %>
                    <h3><%= userRecentQuizzesResponse.getErrorMessage() %></h3>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
