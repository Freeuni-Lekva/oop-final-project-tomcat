<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Challenge</title>
    <style><%@include file="css/styles.css"%></style>
    <style>
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="UserFooter.jsp" />
    <div class="contents">
        <h1>Friend List</h1>

            <form action="sendChallenge" method="post">
                <div class="friend-list">
                    <ul>
                        <%
                            Long quizId = (Long) request.getAttribute("quizId");
                            List<String> friendList = (List<String>) request.getAttribute("friendList");
                            if (friendList != null && !friendList.isEmpty()) {
                                for (String friend : friendList) {
                        %>
                        <li class="friend-row">
                            <label>
                                <input type="checkbox" name="receiverUsernames" value="<%= friend %>">
                                <input type="hidden" name="quizId" value="<%= quizId %>">
                                <a class="username" href="<%= request.getContextPath() %>/user?username=<%= friend %>">
                                    <%= friend %>
                                </a>
                            </label>
                        </li>
                        <%
                                }
                            } else {
                        %>
                        <li>No friends available.</li>
                        <%
                            }
                        %>
                    </ul>
                </div>
                <button type="submit">Send Challenge</button>
            </form>
    </div>
</body>
</html>
