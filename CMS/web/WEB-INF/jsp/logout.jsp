<%-- 
    Document   : logout
    Created on : 2013-10-01, 14:53:07
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
        <h1>
            <c:if test="${user.name == null}">
                Wylogowanie powiodło się
            </c:if>
            <c:if test="${user.name != null}">
                Wylogowanie nie powiodło się
            </c:if>
        </h1>
        <a href="/CMS/login.htm">Powrót</a>
    </body>
</html>
