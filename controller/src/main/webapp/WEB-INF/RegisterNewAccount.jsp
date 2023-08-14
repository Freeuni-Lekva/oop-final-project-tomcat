<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>Register New Account</title>
    <style><%@include file="css/styles.css"%></style>
</head>
<body>
<jsp:include page="ErrorFooter.jsp" />
<h1>Create New Account</h1>
<div style="margin-top: 20px;">
    <form action="register" method="post">
        <label for="firstName" style="display: inline-block; font-size: 14px;">Firstname:</label>
        <input type="text" id="firstName" name="firstName">
        <div style="margin-top: 10px;"></div>
        <label for="lastName" style="display: inline-block; font-size: 14px;">Lastname:</label>
        <input type="text" id="lastName" name="lastName">
        <div style="margin-top: 10px;"></div>
        <label for="username" style="display: inline-block; font-size: 14px;">Username:</label>
        <input type="text" id="username" name="username">
        <div style="margin-top: 10px;"></div>
        <label for="password" style="font-size: 14px; display: inline-block">Password:</label>
        <input type="password" id="password" name="password" style="margin-bottom: 5px">
        <div style="margin-top: 10px;"></div>

        <button type="submit">Register</button>
    </form>
    <div style="margin-top: 20px;"></div>
</div>
</body>
</html>
