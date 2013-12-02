<%-- 
    Document   : newUser
    Created on : 2013-10-01, 15:00:11
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>    
    <jsp:body>
        <c:if test="${user.name == null && emailSent == null}">
            Załóż konto
            <form action="/CMS/newUser.htm" method="POST">
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
        <c:if test="${emailSent == 'y'}">
            Prośba wysłana!
        </c:if>
        <c:if test="${emailSent != 'y' && emailSent != null}">
            Błąd przy wysyłaniu wiadomości email. Prosze spróbować ponownie później.
        </c:if>
        <h3>
            ${error}
        </h3>
    </jsp:body>
</t:genericTemplate>