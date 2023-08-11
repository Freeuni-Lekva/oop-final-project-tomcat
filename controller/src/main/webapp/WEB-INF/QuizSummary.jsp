<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Summary</title>
</head>
<body>
    <h1>Quiz Summary</h1>
    <h2>${quiz.name}</h2>
    <p>Description: ${quiz.description}</p>
    <p>Owner: ${quiz.owner}</p>

    <form action="startQuiz" method="post">
        <input type="hidden" name="quizId" value="${quiz.quizId}">
        <input type="submit" value="Start the Quiz">
    </form>
</body>
</html>