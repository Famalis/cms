<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/fileListCtrl.js"></script>
        <div ng-init="fileListDownload = true" ng-controller="FileListCtrl">
            <div  class="top-right">

                <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

            </div>

            <t:dataTable/>
        </div>
    </jsp:body>
</t:genericTemplate>