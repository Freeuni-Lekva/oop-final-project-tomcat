<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .error-message {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #ffcccc;
        padding: 10px;
        text-align: center;
        font-size: 25px;
        color: #d8000c;
        z-index: 9999; /* Ensure the message appears on top of other content */
    }
</style>
<div id="header">
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
    <%
        }
    %>
</div>