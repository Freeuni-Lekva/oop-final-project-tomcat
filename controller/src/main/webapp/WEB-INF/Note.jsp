<%--
  Created by IntelliJ IDEA.
  User: muradi
  Date: 8/18/2023
  Time: 8:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Note Details</title>
  <style>
    <%@include file="css/NotificationsStyles.css"%></style>
  </style>
</head>
<body>
<div class="note-container">
  <div class="note-header">Note Details</div>
  <div class="note-sender">From: <%= request.getAttribute("noteSender") %></div>
  <div class="note-subject">Subject: <%= request.getAttribute("noteSubject") %></div>
  <div class="note-timestamp">Sent at: <%= request.getAttribute("noteTimeStamp") %></div>
  <div class="note-content"><%= request.getAttribute("noteContent") %></div>
  <a class="reply-button" href="/CreateNewNote">Reply</a>
</div>
</body>
</html>
