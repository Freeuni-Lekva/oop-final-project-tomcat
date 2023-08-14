<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<style><%@include file="css/styles.css"%></style>
<div class="user-footer">
    <div class="user-buttons">
        <%
            String currentUser = (String) session.getAttribute("currentUser");
            String contextPath = (String) session.getServletContext().getContextPath();
        %>
        <form id="homeForm" action="<%=contextPath%>/home" method="get">
            <button type="submit">Home</button>
        </form>
        <form id="profileForm" action="<%=contextPath%>/user" method="get">
            <input type="hidden" name="username" value="<%= currentUser %>">
            <button type="submit">Profile</button>
        </form>
        <form id="logoutForm" action="<%=contextPath%>/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>
