<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/privilegeListCtrl.js"></script>
        <div ng-controller="PrivilegeListCtrl">  
            <h1>Privilege List</h1>
        </div>
    </jsp:body>
</t:genericTemplate>