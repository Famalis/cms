<%-- 
    Document   : newUser
    Created on : 2013-10-01, 15:00:11
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
        <c:if test="${user == null}">
            Załóż konto
            <form action="/CMS/newUser.htm" action="POST">
                
            </form>
        </c:if>
        <c:if test="${user != null}">
            Zalogowany
        </c:if>
    </body>
</html>
