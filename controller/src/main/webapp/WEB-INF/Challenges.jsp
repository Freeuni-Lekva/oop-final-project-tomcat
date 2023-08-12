<%--
  Created by IntelliJ IDEA.
  User: zuragrdzelidze
  Date: 12.08.23
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Challenges</title>
</head>
<body>
<h1>Challenges</h1>

<%-- Check if 'Challenges' attribute exists --%>
<c:if test="${not empty Challenges}">
    <ul>
        <c:forEach items="${Challenges}" var="challenge">
            <li>
                <p><strong>Challenge from: ${challenge.senderName}</strong></p>
                <p>Quiz Name: <a href="QuizSummary?quizId=${challenge.quizId}">${challenge.quizName}</a></p>
                <p><a href="StartQuiz?quizId=${challenge.quizId}">Start the Quiz</a></p>
                <form action="DeclineChallenge" method="post">
                    <input type="hidden" name="challengeId" value="${challenge.challengeId}">
                    <button type="submit">Decline</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>

<%-- If 'Challenges' attribute is empty, show an error message --%>
<c:if test="${empty Challenges}">
    <p>No challenges available.</p>
</c:if>

<a href="index.jsp">Back to Home</a>
</body>
</html>
