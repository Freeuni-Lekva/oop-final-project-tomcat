<%--
  Created by IntelliJ IDEA.
  User: zuragrdzelidze
  Date: 04.08.23
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Challenge Sent</title>
</head>
<body>
<h1>Challenge Sent</h1>
<p>Your challenge has been successfully sent to the <%=session.getAttribute("to")%></p>
<a href="index.jsp">Back to Home</a>
</body>
</html>


