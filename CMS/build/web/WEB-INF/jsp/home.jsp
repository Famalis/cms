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
        
        <c:forEach items="${list}" var="page">
            <a href="/CMS/home/${page}.htm">Strona id: ${page}</a> |
        </c:forEach>
            
            <h4>
                ${helloUser}
            </h4>
            <form action="/CMS/home.htm" method="POST">
                Login <input type="text" name="login"/>
                Hasło <input type="text" name="password"/>
                <input type="submit"/>
            </form>
    </body>
</html>
