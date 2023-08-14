<%@page import="ge.edu.freeuni.models.QuizModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Summary</title>
    <style><%@include file="css/styles.css"%></style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <%
        QuizModel quiz = (QuizModel) request.getAttribute("quizDetails");

        if (quiz != null) {
    %>
        <p>Name: <%= quiz.getName() %></p>
        <p>Description: <%= quiz.getDescription() %></p>
        <p><a href="<%=request.getContextPath()%>/user?username=<%=quiz.getOwner().getUsername()%>"><%=quiz.getOwner().getUsername() %></a></p>
    <%
        } else {
    %>
        <p>No quiz found.</p>
    <%
        }
    %>

    <form action="startQuiz" method="post">
        <input type="hidden" name="quizId" value="${quiz.quizId}">
        <input type="submit" value="Start the Quiz">
    </form>
</body>
</html>