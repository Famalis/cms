<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/reportListCtrl.js"></script>
        <div ng-controller="ReportListCtrl">  
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
                                <th>
                                    Hash
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: report == selected}" ng-repeat="report in reports" ng-click="select(report)">
                                <td>
                                    {{report.name}}
                                </td>
                                <td>
                                    {{report.description}}
                                </td>
                                <td>
                                    {{report.hash}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    <form action="reportList/upload.htm" method="POST"
                                          commandName="fileCmd">
                                        <input type="file" name="file"/>
                                        <input type="text" value="aName" name="name"/>
                                        <input type="submit"/>
                                    </form>                                    
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>