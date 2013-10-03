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
        <c:if test="${user.name == null}">
            Załóż konto
            <form action="/CMS/newUser.htm" action="POST">
                Login: <input type="text" name="login"/>
                Hasło: <input type="password" name="password"/>
                Imię: <input type="text" name="name"/>
                Nazwisko: <input type="text" name="surname"/>
                Email: <input type="text" name="email"/>
                <input type="submit"/>
            </form>
        </c:if>
        <c:if test="${user.name != null}">
            Zalogowany
        </c:if>
    </body>
</html>
