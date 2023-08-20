<%--
  Created by IntelliJ IDEA.
  User: zuragrdzelidze
  Date: 20.08.23
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<style><%@include file="css/styles.css"%></style>
<div class="search-user">

    <div class="search-button">
        <%
            String contextPath = (String) session.getServletContext().getContextPath();
        %>
        <form id="profileForm" action="<%=contextPath%>/user" method="get">
            <label for="textInput">Username: </label>
            <input type="text" id="textInput" name="username">
            <button type="submit">Search User</button>
        </form>

    </div>
</div>
