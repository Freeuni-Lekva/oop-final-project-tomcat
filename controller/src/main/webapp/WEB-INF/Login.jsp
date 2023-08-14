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
<title>Login</title>
<style>
    h1 {
        font-size: xxx-large;
        color: indigo;
        margin-bottom: 90px;
        margin-right: 50px;
    }
    body {
        background-color: lavenderblush;
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    label {
        display: block;
        font-size: 14px;
        color: #555;
        margin-bottom: 5px;
        text-align: left;
    }
    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-bottom: 15px;
        font-size: 14px;
    }
    button[type="submit"] {
        background-color: indigo;
        color: white;
        border-radius: 5px;
        padding: 10px 15px;
        font-size: 14px;
        cursor: pointer;
    }
</style>
<body>
<jsp:include page="ErrorHeader.jsp" />
<h1>Welcome To Our Quiz Page</h1>
<div style="margin-top: 20px;">
    <form action="login" method="post">
        <label for="username" style="display: inline-block; font-size: 14px;">User Name:</label>
        <input type="text" id="username" name="username" >
        <div style="margin-top: 20px;"></div>
        <label for="password" style="font-size: 14px; display: inline-block">Password:</label>
        <input type="password" id="password" name="password" style="margin-bottom: 5px">

        <button type="submit">Login</button>
    </form>
    <div style="margin-top: 20px;"></div>
    <div style="margin-top: 20px;">
        <a href="register" style="color: darkmagenta; text-decoration: underline">Create New Account</a>
    </div>
</div>
</body>
</html>

