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
        <c:forEach items="${articles}" var="article">
            <a href="/CMS/article/${article.id}.htm">${article.title}</a><br/>
        </c:forEach>
    </jsp:body>
</t:genericTemplate>