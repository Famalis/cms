<%-- 
    Document   : login
    Created on : 2013-10-01, 14:19:43
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <c:if test="${user.name != null}">
            <p style="background-color: ${userConfig.backgroundColor}">
                Zalogowany jako ${user.login}
            </p>
            <form action="/CMS/logout.htm" method="POST">
                <input type="submit" name="logoutButton" value="Wyloguj"/>
            </form>
            <form action="/CMS/bgcolor.htm" method="GET">
                Kolor tła: <input type="text" value="${userConfig.backgroundColor}" name="color"/>
                <input type="submit" name="bgcolorButton"/>
            </form>
        </c:if>
        <h2>
            ${helloUser}
        </h2>
        <c:if test="${user.name == null}">
            <form action="/CMS/login.htm" method="POST">
                Login <input type="text" name="login"/>
                Hasło <input type="password" name="password"/>
                <input type="submit" name="loginButton" value="Zaloguj"/>
            </form>
        </c:if>
        <a href="/CMS/newUser.htm">Załóż konto</a>
        <c:if test="${userConfig.groupId == 1}">
            <a href="/CMS/userList.htm">Lista użytkowników</a>            
        </c:if>
        <a href="/CMS/articleCreation.htm">Stwórz artykuł</a>
    </jsp:body>
</t:genericTemplate>