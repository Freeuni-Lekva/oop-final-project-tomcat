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
    <jsp:include page="SearchUser.jsp" />
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
            Boolean areFriends = (Boolean) request.getAttribute("areFriends");
            Long friendRequestId = (Long) request.getAttribute("friendRequestId");
            if(!user.getUsername().equals(currentUsername)) {
                if(!areFriends) {
                    if(friendRequestId != null) {
        %>
            <form class="remove-request-form" action="RemoveFriendRequest" method="post">
                <input type="hidden" name="requestId" value="<%= friendRequestId %>">
                <input type="hidden" name="location" value="user?username=<%= user.getUsername() %>">
                <button class="send-request-btn" type="submit">Cancel Friend Request</button>
            </form>
        <%
                    } else {
        %>
            <form class="friend-request-form" action="FriendRequest" method="post">
                <input type="hidden" name="receiverUsername" value="<%= user.getUsername() %>">
                <input type="hidden" name="receiverUserId" value="<%= user.getId() %>">
                <button class="send-request-btn" type="submit">Send Friend Request</button>
            </form>
        <%
                    }
                } else {
        %>
            <form class="delete-friend-form" action="deleteFriend" method="post">
                <input type="hidden" name="receiverUsername" value="<%= user.getUsername() %>">
                <button class="send-request-btn" type="submit">Delete Friend</button>
            </form>
        <%
                }
            }
        %>
    </div>
</body>
</html>
