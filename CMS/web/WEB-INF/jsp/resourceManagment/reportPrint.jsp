<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/reportPrintCtrl.js"></script>
        <div ng-controller="ReportPrintCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <t:filterTable/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <t:dataTable/>
                    </td>
                </tr>
                <tr ng-show="selected">
                    <td>
                        <div ng-include="selected.formCode">

                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>