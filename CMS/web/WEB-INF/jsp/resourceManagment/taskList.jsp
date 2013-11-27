<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/taskListCtrl.js"></script>
        <div ng-controller="TaskListCtrl">  
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
                                    Przydzielone do:
                                </th>
                                <th>
                                    Opis
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: task == selected}" ng-repeat="task in tasks | filter:searchText" ng-click="select(task)">
                                <td>
                                    {{task.empName}} {{task.empSurname}}
                                </td>
                                <td>
                                    {{task.description}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr ng-show="editMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Prowadzący: <select ng-model="selected.managerId">
                                        <option ng-repeat="employee in employees"
                                                value="{{employee.id}}">
                                            {{employee.surname}} {{employee.name}}
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Kraj: <input type="text" ng-model="selected.country"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>