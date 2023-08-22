<%@ page import="ge.edu.freeuni.models.InboxModel" %>
<%@ page import="ge.edu.freeuni.models.NotificationModel" %>
<%@ page import="ge.edu.freeuni.models.ChallengeModel" %>
<%@ page import="ge.edu.freeuni.models.FriendRequestModel" %>
<%@ page import="ge.edu.freeuni.models.NoteModel" %>
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
<jsp:include page="SearchUser.jsp" />
<div class="notifications-container">
    <%
        String location = (String) request.getAttribute("location");
    %>
    <div class="navigation-bar">
        <a class="nav-button <%= "Notifications".equals(location) ? "active" : "" %>" href="Notifications">Notifications</a>
        <a class="nav-button <%= "FriendRequests".equals(location) ? "active" : "" %>" href="FriendRequests">Friend Requests</a>
        <a class="nav-button <%= "Mail".equals(location) ? "active" : "" %>" href="Mail">Mail</a>
        <a class="nav-button <%= "Challenges".equals(location) ? "active" : "" %>" href="Challenges">Challenges</a>
    </div>
    <div class="create-new-button-container">
        <h2 style="margin: 0;">Notifications</h2>
        <%
            if(location.equals("Mail")) {
        %>
        <a class="create-new-button" href="CreateNewNote">Create New</a>
        <%
            }
        %>
    </div>
    <div class="notifications-list">
    <%
    if (((InboxModel)request.getAttribute("inboxModel")).getNotificationModelList().isEmpty()) {
    %>
    <div class="no-notifications">
        You have no notifications.
    </div>
    <%
    } else {
    %>
    <%
        for (NotificationModel notification : ((InboxModel)request.getAttribute("inboxModel")).getNotificationModelList()) {
            if(notification instanceof ChallengeModel) {
                ChallengeModel challenge = (ChallengeModel) notification;
    %>
        <div class="notification">
            <a href="quizdetails?id=<%= challenge.getQuiz().getId() %>">
                <div class="friend-request">
                    <div class="child">
                        <p class="notification-label-main"><%= challenge.getSender().getUsername() %> sent you a challenge to the quiz: <%= challenge.getQuiz().getName() %></p>
                        <p class="notification-label-small"><%= challenge.getDatetime() %></p>
                    </div>
                    <div class="child">
                        <form id="acceptChallenge" action="AcceptChallenge" method="post">
                            <input type="hidden" name="challengeId" value="<%= challenge.getId() %>">
                            <input type="hidden" name="location" value="<%= location %>">
                            <button type="submit" style="font-size: 16px; background: #004500; margin-bottom: 3px;">Accept</button>
                        </form>
                        <form id="declineChallenge" action="DeclineChallenge" method="post">
                            <input type="hidden" name="challengeId" value="<%= challenge.getId() %>">
                            <input type="hidden" name="location" value="<%= location %>">
                            <button type="submit" style="font-size: 16px; background: #610000; margin-top: 3px;">Decline</button>
                        </form>
                    </div>
                </div>
            </a>
        </div>
    <%
            } else if (notification instanceof FriendRequestModel) {
                FriendRequestModel friendRequest = (FriendRequestModel) notification;
    %>
        <div class="notification">
            <div class="friend-request">
                <div class="child">
                    <p class="notification-label-main"><%= friendRequest.getSender().getUsername() %> sent you a friend request</p>
                    <p class="notification-label-small"><%= friendRequest.getDatetime() %></p>
                </div>
                <div class="child">
                    <form id="acceptFriendRequest" action="AcceptFriendRequest" method="post">
                        <input type="hidden" name="requestId" value="<%= friendRequest.getId() %>">
                        <input type="hidden" name="location" value="<%= location %>">
                        <button type="submit" style="font-size: 16px; background: #004500; margin-bottom: 3px;">Accept</button>
                    </form>
                    <form id="declineFriendRequest" action="RemoveFriendRequest" method="post">
                        <input type="hidden" name="requestId" value="<%= friendRequest.getId() %>">
                        <input type="hidden" name="location" value="<%= location %>">
                        <button type="submit" style="font-size: 16px; background: #610000; margin-top: 3px;">Decline</button>
                    </form>
                </div>
            </div>
        </div>
    <%
            } else if (notification instanceof NoteModel) {
                NoteModel note = (NoteModel) notification;
    %>
        <a href = "Note?noteId=<%= note.getId() %>">
            <div class="notification">
                <p class="notification-label-main"><%= note.getFrom().getUsername() %> sent you a note: <%= note.getSubject() %></p>
                <p class="notification-label-small"><%= note.getDatetime() %></p>
            </div>
        </a>
    <%
            }
        }
     }
    %>
</div>
</div>
</body>
</html>
