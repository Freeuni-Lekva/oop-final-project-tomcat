<%@page import="ge.edu.freeuni.models.UserModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= ((UserModel) request.getAttribute("userdetails")).getUsername() %></title>
    <style><%@include file="css/styles.css"%></style>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .user-details {
            max-width: 600px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 28px;
            margin-bottom: 10px;
            text-align: center;
            color: indigo;
        }
        .user-info {
            font-size: 16px;
            line-height: 1.6;
            margin-bottom: 20px;
            text-align: center;
        }
        .friend-request-form {
            text-align: center;
            margin-top: 10px;
        }
        .send-request-btn {
            padding: 10px 20px;
            background-color: #337ab7;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .send-request-btn:hover {
            background-color: #23527c;
        }
    </style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <div class="user-details">
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
