<%--
  Created by IntelliJ IDEA.
  User: muradi
  Date: 7/25/2023
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register New Account</title>
</head>
<body>
<h1>Create New Account</h1>
<div style="font-size: 17px;">Failed to register.</div>
<div style="font-size: 17px;">Please make sure you are giving the right information.</div>
<div style="margin-top: 20px;">
    <form action="register new account" method="post">
        <label for="firstName" style="display: inline-block; font-size: 14px;">Name:</label>
        <input type="text" id="firstName" name="firstName">
        <div style="margin-top: 20px;"></div>
        <label for="lastName" style="display: inline-block; font-size: 14px;">User Name:</label>
        <input type="text" id="lastName" name="lastName">
        <div style="margin-top: 20px;"></div>
        <label for="username" style="display: inline-block; font-size: 14px;">User Name:</label>
        <input type="text" id="username" name="username">
        <div style="margin-top: 20px;"></div>
        <label for="password" style="font-size: 14px; display: inline-block">Password:</label>
        <input type="password" id="password" name="password" style="margin-bottom: 5px">

        <button type="submit">Login</button>
    </form>
    <div style="margin-top: 20px;"></div>
</div>
</body>
</html>
