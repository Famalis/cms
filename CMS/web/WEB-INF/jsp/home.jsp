<%-- 
    Document   : home
    Created on : 2013-09-30, 19:11:29
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h4>${content}</h4>
        <c:if test="${user.name != null}" > 
            <h1 style="background-color: ${userConfig.backgroundColor}">
                Witaj ${user.name}!
            </h1>
        </c:if>
        <a href="/CMS/login.htm">Zarządzanie kontem</a>
    </body>
</html>
