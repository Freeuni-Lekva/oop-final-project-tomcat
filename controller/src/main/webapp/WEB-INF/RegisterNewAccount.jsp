<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register New Account</title>
<style>
    h1 {
        font-size: xxx-large;
        color: indigo;
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
    body {
        background-color: lavenderblush;
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
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
</head>
<body>
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
