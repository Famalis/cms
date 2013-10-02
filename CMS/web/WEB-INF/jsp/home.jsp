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
            <h1>
                Witaj ${user.name}!
            </h1>
        </c:if>

        <c:forEach items="${list}" var="page">
            <a href="/CMS/home/${page}.htm">Strona id: ${page}</a> |
        </c:forEach>

    </body>
</html>
