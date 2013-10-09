<%-- 
    Document   : home
    Created on : 2013-09-30, 19:11:29
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
            <h4>${content}</h4>
            <c:if test="${user.name != null}" > 
                <h1 style="background-color: ${userConfig.backgroundColor}">
                    Witaj ${user.name}!
                </h1>
            </c:if>
            <c:if test="${user.name == null}" > 
                <h1 style="background-color: ${userConfig.backgroundColor}">
                    Witaj Gościu!
                </h1>
            </c:if>
            <a href="/CMS/login.htm">Zarządzanie kontem</a>
            <a href="/CMS/articles.htm">Lista artykułów</a>
        </jsp:body>
    </t:genericTemplate>