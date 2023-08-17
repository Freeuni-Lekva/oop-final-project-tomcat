<%--
  Created by IntelliJ IDEA.
  User: muradi
  Date: 7/25/2023
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>Login</title>
    <style><%@include file="css/styles.css"%></style>
    <style>
        h1 {
            font-size: xxx-large;
            margin-bottom: 90px;
            margin-right: 50px;
        }
        label {
            display: block;
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
            text-align: left;
        }
        button[type="submit"] {
            margin-top: 18px;
        }
    </style>
</head>
<body>
<jsp:include page="ErrorFooter.jsp" />
<h1>Welcome To Exquizite</h1>
<div style="margin-top: 20px;">
    <form action="login" method="post">
        <label for="username" style="display: inline-block; font-size: 18px;">User Name:</label>
        <input type="text" id="username" name="username" >
        <div style="margin-top: 20px;"></div>
        <label for="password" style="font-size: 18px; display: inline-block">Password:</label>
        <input type="password" id="password" name="password" style="margin-bottom: 5px">

        <button type="submit">Login</button>
    </form>
    <div style="margin-top: 20px;"></div>
    <div style="margin-top: 20px;">
        <a href="register" style="color: darkmagenta; text-decoration: underline; font-size: 25px; font-weight: bold">Create New Account</a>
    </div>
</div>
</body>
</html>

