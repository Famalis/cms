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
                        <table class="genericTable">
                            <tr>
                                <th>
                                    Nazwa
                                </th>
                                <th>
                                    Opis
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: report == selected}" ng-repeat="report in reports" ng-click="select(report)">
                                <td width="30%">
                                    {{report.name}}
                                </td>
                                <td>
                                    {{report.description}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr ng-show="selected">
                    <td>
                        <div ng-hide="selected.formCode">
                            <form action="reportPrint/print.htm">
                                <input ng-show="false" type="text" value="{{selected.id}}" name="id"/>
                                <input type="submit" value="Drukuj {{selected.name}}"/>
                            </form>
                        </div>
                        <div ng-show="selected.formCode" ng-include="selected.formCode">

                        </div>
                    </td>
                </tr>
                <tr>                    
                    <td>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>