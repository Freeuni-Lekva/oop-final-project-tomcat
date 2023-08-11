<%@page import="java.util.List"%>
<%@page import="ge.edu.freeuni.models.QuizModel"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<html>
<head>
<title>Quiz Website</title>
</head>
<body>

<h1>Homepage</h1>

<h2>Most Popular Quizzes</h2>

<%
List<QuizModel> mostPopularQuizzes = (List<QuizModel>)request.getAttribute("popularQuizzes");
for (QuizModel quiz : mostPopularQuizzes) {
%>
<p><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></p>
<%
}
%>

<h2>Most Recent Quizzes</h2>

<%
List<QuizModel> mostRecentQuizzes = (List<QuizModel>)request.getAttribute("recentQuizzes");
for (QuizModel quiz : mostRecentQuizzes) {
%>
<p><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></p>
<%
}
%>

<h2>Recently Created Quizzes</h2>

<%
List<QuizModel> recentCreateQuizzes = (List<QuizModel>)request.getAttribute("userRecentQuizzes");
for (QuizModel quiz : recentCreateQuizzes) {
%>
<p><a href="<%=request.getContextPath()%>/quizdetails?id=<%=quiz.getId()%>"><%=quiz.getName()%></a></p>
<%
}
%>

</body>
</html>

