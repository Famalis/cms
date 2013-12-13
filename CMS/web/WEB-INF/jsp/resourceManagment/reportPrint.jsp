<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/reportPrintCtrl.js"></script>
        <div ng-controller="ReportPrintCtrl">  
           
                   
                        <t:dataTable/>
                
                
                <div ng-show="selected">
                    <td>
                        <div ng-include="selected.formCode">

                        </div>
                </div>   
        </div>
    </jsp:body>
</t:genericTemplate>