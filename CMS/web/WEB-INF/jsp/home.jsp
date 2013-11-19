<%-- 
    Document   : home
    Created on : 2013-09-30, 19:11:29
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <body>
        <div align="center">
        <form action="/CMS/login.htm" method="POST">
            Login <input type="text" name="login"/>
            Hasło <input type="password" name="password"/>
            <input type="submit" name="loginButton" value="Zaloguj"/>
        </form>
        </div>
</html>