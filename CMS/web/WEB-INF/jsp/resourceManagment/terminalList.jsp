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
                        <t:filterTable/>
                    </td>
                </tr>
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
                            <tr ng-class="{selectedTableRow: terminal == selected}" ng-repeat="terminal in terminals | filter:searchText | orderBy:orderColumn:reverse" ng-click="select(terminal)">
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
                <tr ng-show="selected">
                    <td>
                        <table class="genericTable">                            
                            <tr ng-show="size(selected.timestamps) == 0">
                                <td colspan="2">
                                    Brak logów
                                </td>
                            </tr>
                            <tbody ng-show="size(selected.timestamps) > 0">
                            <tr>
                                <th width="50%">
                                    Timestamp:
                                </th>
                                <th>
                                    Pracownik:
                                </th>
                            </tr>
                            <tr ng-repeat="(timestamp, employee) in selected.timestamps">                                
                                <td>                                    
                                    {{timestamp}}
                                </td>
                                <td>
                                    {{employee}}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>