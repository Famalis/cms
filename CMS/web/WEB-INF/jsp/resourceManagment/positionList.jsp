<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/positionListCtrl.js"></script>
        <div ng-controller="PositiontListCtrl">  
            <h1>Positions List</h1>
        </div>
    </jsp:body>
</t:genericTemplate>