<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/terminalListCtrl.js"></script>
        <div ng-controller="TerminalListCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <th>
                                    Id
                                </th>
                                <th>
                                    Opis
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: terminal == selected}" ng-repeat="terminal in terminals" ng-click="select(terminal)">
                                <td>
                                    {{terminal.id}}
                                </td>
                                <td>
                                    {{terminal.description}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <t:jsonOperations/>
                    </td>
                </tr>
                <tr ng-show="editMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Opis: <input type="text" ng-model="selected.description"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr ng-show="logMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Log Id:
                                </td>
                                <td>
                                    LogListId:
                                </td>
                                <td>
                                    Timestamp:
                                </td>
                            </tr>
                            <tr  ng-repeat="log in logs" ng-show="log.terminalId == selected.id">
                                <td>
                                    {{log.id}}
                                </td>
                                <td>
                                    {{log.logListId}}
                                </td>
                                <td>
                                    {{log.timestamp}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>