<%--
  Created by IntelliJ IDEA.
  User: muradi
  Date: 7/25/2023
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<h1>Welcome To Our Quiz Page</h1>
<div style="font-size: 17px;">Please log in.</div>
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
</div>
<a href="register" style="color:blue; text-decoration: underline">Create New Account</a>
</body>
</html>
