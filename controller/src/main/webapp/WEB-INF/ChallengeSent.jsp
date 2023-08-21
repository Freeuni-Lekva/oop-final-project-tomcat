<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Challenge Notification</title>
    <style><%@include file="css/styles.css"%></style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <jsp:include page="SearchUser.jsp" />
    <div class="contents">
        <div class="notification-container">
            <%
                Long quizId = (Long) request.getAttribute("quizId");
            %>
            <p class="notification-text">
                <% if ((Boolean) request.getAttribute("plural")) { %>
                    You have successfully sent challenges to your friends.
                    They will be notified and can decide whether to accept the challenges.
                <% } else { %>
                    You have successfully sent a challenge to your friend.
                    They will be notified and can decide whether to accept the challenge.
                <% } %>
            </p>
            <a class="back-to-quiz-btn" href="<%=request.getContextPath()%>/quizdetails?id=<%=quizId%>">
                Back to Quiz Details
            </a>
        </div>
    </div>
</body>
</html>


