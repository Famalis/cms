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
        <script src="/CMS/resources/js/articleCreationCtrl.js"></script>
        <div ng-controller="ArticleCreationCtrl">
            <c:if test="${user.name != null}">
            Tytuł:<input type="text" ng-model="article.title"/><br/>
            Treść:<textarea ng-model="article.text">

            </textarea><br/>
            <input type="submit" value="Stwórz" ng-click="save()"/>
            </c:if>
            <c:if test="${user.name == null}">
                Zaloguj się
            </c:if>
        </div>

    </jsp:body>
</t:genericTemplate>
