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
    <title>Welcome <%=session.getAttribute("currentUser")%></title>
</head>
<body>
<h1>Welcome <%=session.getAttribute("currentUser")%></h1>
</body>
</html>

