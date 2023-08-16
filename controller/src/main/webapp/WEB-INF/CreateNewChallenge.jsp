<%@ page import="ge.edu.freeuni.models.QuizModel" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.edu.freeuni.services.QuizService" %>
<%@ page import="ge.edu.freeuni.servlets.Quiz" %><%--
  Created by IntelliJ IDEA.
  User: zuragrdzelidze
  Date: 14.08.23
  Time: 04:17
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Create New Challenge</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Create a New Challenge</h1>--%>
<%--<form action="your_action_url_here" method="post">--%>
<%--    <!-- Your form fields go here -->--%>
<%--    <!-- For example: -->--%>
<%--    <label for="challengeName">Challenge Name:</label>--%>
<%--    <input type="text" id="challengeName" name="challengeName"><br><br>--%>
<%--    <label for="description">Description:</label>--%>
<%--    <textarea id="description" name="description"></textarea><br><br>--%>
<%--    <button type="submit">Create Challenge</button>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Challenge</title>
</head>
<body>
<h1>Create New Challenge</h1>

<form action="/CreateChallenge" method="post">

    <p>Select the quiz you want to challenge:</p>
    <select name="quiz_id">
        <%
            QuizService quizService = new QuizService();
            List<QuizModel> quizzes = (List<QuizModel>) quizService.getAllQuizzes();
            for (QuizModel quiz : quizzes) {
        %>
        <option value="<%= quiz.getId() %>"><%= quiz.getName() %></option>
        <%
            }
        %>
    </select>

    <p>Enter a message to the challenged user:</p>
    <input type="text" name="message" />

    <input type="submit" value="Create Challenge" />

</form>
</body>
</html>


