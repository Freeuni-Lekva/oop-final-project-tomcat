<%--
  Created by IntelliJ IDEA.
  User: muradi
  Date: 8/18/2023
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Notification</title>
    <style>
        <%@include file="css/NotificationsStyles.css"%></style>
    </style>
</head>
<body>
<jsp:include page="UserFooter.jsp" />
<div class="notifications-container">
    <div class="navigation-bar">
        <a class="nav-button" href="/Notifications">Notifications</a>
        <a class="nav-button" href="/FriendRequests">Friend Requests</a>
        <a class="nav-button" href="/Mail">Mail</a>
        <a class="nav-button" href="/Challenges">Challenges</a>
    </div>
    <div class="create-new-button-container">
        <h2 style="margin: 0;">Create New Notification</h2>
    </div>
    <div class="big-button-container">
        <a class="big-button" href="/FriendRequest">New Friend Request</a>
        <a class="big-button" href="/SendChallenge">New Challenge</a>
        <a class="big-button" href="/CreateNewNote">New Note</a>
    </div>
</div>
</body>
</html>

