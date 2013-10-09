<%-- 
    Document   : logout
    Created on : 2013-10-01, 14:53:07
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <h1>
        <c:if test="${user.name == null}">
            Wylogowanie powiodło się
        </c:if>
        <c:if test="${user.name != null}">
            Wylogowanie nie powiodło się
        </c:if>
    </h1>
</t:genericTemplate>
