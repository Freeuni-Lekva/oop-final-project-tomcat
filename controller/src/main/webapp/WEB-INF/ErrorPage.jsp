<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Occurred</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #ffcccc;
            font-family: Arial, sans-serif;
        }

        #errorText {
            font-size: 50px;
            color: #d8000c;
            text-align: center;
            margin-bottom: 40px;
        }

        #returnButton {
            margin-top: 40px;
            background-color: #d8000c;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 40px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div id="errorText">
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div>
                <%= errorMessage %>
            </div>
        <%
            }
        %>
    </div>
    <a href="<%=request.getContextPath()%>/home">
        <button id="returnButton">Return to Home</button>
    </a>
</body>
</html>
