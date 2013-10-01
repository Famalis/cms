<%-- 
    Document   : login
    Created on : 2013-10-01, 14:19:43
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
        <c:if test="${user != null}">
            <p>
                Zalogowany jako ${user.login}
            </p>
            <form action="/CMS/login/logout.htm" method="POST">
                <input type="submit" value="Wyloguj"/>
            </form>
        </c:if>
        <h4>
            ${helloUser}
        </h4>
        <c:if test="${user == null}">
            <form action="/CMS/login.htm" method="POST">
                Login <input type="text" name="login"/>
                Hasło <input type="text" name="password"/>
                <input type="submit" value="Zaloguj"/>
            </form>
        </c:if>
    </body>
</html>
