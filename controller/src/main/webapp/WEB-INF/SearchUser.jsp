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
    <%
        String contextPath = (String) session.getServletContext().getContextPath();
    %>
    <form id="searchUserForm" action="<%=contextPath%>/user" method="get">
        <input type="text" style="width: 300px;" id="textInput" name="username" placeholder="Enter username">
        <button type="submit" style="margin-bottom: 15px; margin-left: 20px;">Search User</button>
    </form>
</div>
