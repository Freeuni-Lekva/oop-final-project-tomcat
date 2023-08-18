<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Note</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .notifications-container {
            width: 90%;
            max-width: 800px;
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .navigation-bar {
            display: flex;
            justify-content: space-around;
            align-items: center;
            background-color: indigo;
            padding: 10px 0;
        }
        .nav-button {
            color: white;
            text-decoration: none;
            font-weight: bold;
            font-size: 16px;
        }
        .create-new-button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        .create-new-button {
            background-color: blueviolet;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            padding: 8px 15px;
        }
        .create-new-form {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        button[type="submit"] {
            background-color: blueviolet;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            padding: 8px 15px;
        }
    </style>
</head>
<body>
<jsp:include page="ErrorFooter.jsp" />
<jsp:include page="UserFooter.jsp" />
<div class="notifications-container">
    <div class="navigation-bar">
        <a class="nav-button" href="/Notifications">Notifications</a>
        <a class="nav-button" href="/FriendRequests">Friend Requests</a>
        <a class="nav-button" href="/Mail">Mail</a>
        <a class="nav-button" href="/Challenges">Challenges</a>
    </div>

    <div class="create-new-form">
        <form action="/CreateNewNote" method="post">
            <label for="noteRecipient">Recipient Username:</label>
            <input type="text" id="noteRecipient" name="noteRecipient" required value="<%=request.getAttribute("noteRecipient")%>">
            <br>
            <label for="noteSubject">Subject:</label>
            <input type="text" id="noteSubject" name="noteSubject" required value="<%=request.getAttribute("noteSubject")%>">
            <br>
            <label for="noteContent">Content:</label>
            <textarea id="noteContent" name="noteContent" rows="4" required><%=request.getAttribute("noteContent")%></textarea>
            <br>
            <button type="submit">Send Note</button>
        </form>
    </div>

    <c:choose>
    </c:choose>
</div>
</body>
</html>
