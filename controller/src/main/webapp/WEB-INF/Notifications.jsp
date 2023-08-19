<%@ page import="ge.edu.freeuni.models.InboxModel" %>
<%@ page import="ge.edu.freeuni.models.NotificationModel" %>
<%@ page import="com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notifications</title>
    <style>
        <%@include file="css/NotificationsStyles.css"%></style>
    </style>
</head>
<body>
<jsp:include page="UserFooter.jsp" />
<div class="notifications-container">
    <div class="navigation-bar">
        <a class="nav-button <%= "Notifications".equals(request.getAttribute("location")) ? "active" : "" %>" href="./Notifications">Notifications</a>
        <a class="nav-button <%= "FriendRequests".equals(request.getAttribute("location")) ? "active" : "" %>" href="./FriendRequests">Friend Requests</a>
        <a class="nav-button <%= "Mail".equals(request.getAttribute("location")) ? "active" : "" %>" href="./Mail">Mail</a>
        <a class="nav-button <%= "Challenges".equals(request.getAttribute("location")) ? "active" : "" %>" href="./Challenges">Challenges</a>
    </div>
    <div class="create-new-button-container">
        <h2 style="margin: 0;">Notifications</h2>
        <a class="create-new-button" href="<%= request.getAttribute("createNewURL") %>">Create New</a>
    </div>
    <div class="notifications-list">
    <% if (((InboxModel)request.getAttribute("inboxModel")).getNotificationModelList().isEmpty()) { %>
    <div class="no-notifications">
        You have no notifications.
    </div>
    <% } else { %>
    <%
        for (NotificationModel notification : ((InboxModel)request.getAttribute("inboxModel")).getNotificationModelList()) {
    %>
        <a href = "/Notification?notificationType=<%=notification.getNotificationType()%>&notificationId=<%= notification.getId() %>">
            <div class="notification">
                <p class="notification-label-main"><%= notification.getNotificationLabel().get(0) %></p>
                <p class="notification-label-small"><%= notification.getNotificationLabel().get(1) %></p>
            </div>
        </a>
    <%
        }
    %>
    <% } %>
</div>
</div>
</body>
</html>
