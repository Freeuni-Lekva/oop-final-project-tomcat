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
    <div class="contents">
        <div class="notification-container">
            <%
                String username = (String) request.getAttribute("receiverUsername");
            %>
            <p class="notification-text">
                    You have successfully sent a friend request to <%= username %>.
                    They will be notified and can decide whether to accept the request.
            </p>
            <a class="back-to-user-btn" href="<%=request.getContextPath()%>/user?username=<%= username %>">
                Back to User Details
            </a>
        </div>
    </div>
</body>
</html>


