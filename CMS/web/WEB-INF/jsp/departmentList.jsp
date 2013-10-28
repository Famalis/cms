<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/departmentListCtrl.js"></script>
        <div ng-controller="DepartmentListCtrl">  
            <h1>Department List</h1>
        </div>
    </jsp:body>
</t:genericTemplate>