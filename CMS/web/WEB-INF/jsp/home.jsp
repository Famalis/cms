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
        Ostatni artykuł przez ${lastUser.name}:
        <h1>
            ${lastArticle.title}
        </h1>
        <p>
            ${lastArticle.text}
        </p>
    </jsp:body>
</t:genericTemplate>