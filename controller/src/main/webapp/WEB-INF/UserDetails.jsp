<%@page import="ge.edu.freeuni.models.UserModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= ((UserModel) request.getAttribute("userdetails")).getUsername() %></title>
    <style><%@include file="css/styles.css"%></style>
    <style>
        h1 {
            font-size: 50px;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <div class="user-details">
        <h1>User Details</h1>
        <% UserModel user = (UserModel) request.getAttribute("userdetails"); %>
        <div class="user-info">
            <p><strong>Username:</strong> <%= user.getUsername() %></p>
            <p><strong>First name:</strong> <%= user.getFirstname() %></p>
            <p><strong>Last name:</strong> <%= user.getLastname() %></p>
        </div>
        <%
            String currentUsername = (String) session.getAttribute("currentUser");
            if(!user.getUsername().equals(currentUsername)) {
        %>
        <form class="friend-request-form" action="/friendrequest" method="post">
            <input type="hidden" name="friendUsername" value="<%= user.getUsername() %>">
            <button class="send-request-btn" type="submit">Send Friend Request</button>
        </form>
        <%
            }
        %>
    </div>
</body>
</html>
