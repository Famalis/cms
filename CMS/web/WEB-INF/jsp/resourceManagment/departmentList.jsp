<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/departmentListCtrl.js"></script>
        <div ng-controller="DepartmentListCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <th>
                                    Nazwa
                                </th>
                                <th>
                                    Zarządca
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: deparmtnet == selected}" ng-repeat="deparmtnet in departments" ng-click="select(deparmtnet)">
                                <td>
                                    {{deparmtnet.name}}
                                </td>
                                <td>
                                    {{deparmtnet.managerName}}
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
                                    Nazwa wydziału: <input type="text" ng-model="selected.name"/>
                                </td>
                                <td>
                                    Prowadzący: <select ng-model="selected.managerId">
                                        <option ng-repeat="employee in employees"
                                                value="{{employee.id}}" 
                                                ng-selected="selected.managerId==employee.id">
                                            {{employee.surname}} {{employee.name}}
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Kraj: <input type="text" ng-model="selected.country"/>
                                </td>
                                <td>
                                    Miasto: <input type="text" ng-model="selected.city"/>
                                </td>
                                <td>
                                    Nazwa ulicy: <input type="text" ng-model="selected.streetName"/>
                                </td>
                                <td>
                                    Numer budynku: <input type="text" ng-model="selected.streetNumber"/>
                                </td>
                                <td>
                                    Numer mieszkania: <input type="text" ng-model="selected.apartmentNumber"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>