<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/employeeListCtrl.js"></script>
        <div ng-controller="EmployeeListCtrl">  
            <table border="1px" width="100%">
                <tr>
                    <td>
                        <table border="1px" width="100%">
                            <tr>
                                <td>
                                    Nazwisko
                                </td>
                                <td>
                                    Imię
                                </td>
                                <td>
                                    PESEL
                                </td>
                                <td>
                                    Telefon
                                </td>
                                <td>
                                    Wypłata
                                </td>
                                <td>
                                    Stanowisko
                                </td>
                                <td>
                                    Wydział
                                </td>
                            </tr>
                            <tr ng-class="{selectedTableRow: employee == selected}" ng-repeat="employee in employees" ng-click="select(employee)">
                                <td>
                                    {{employee.surname}}
                                </td>
                                <td>
                                    {{employee.name}}
                                </td>
                                <td>
                                    {{employee.pesel}}
                                </td>
                                <td>
                                    {{employee.phone}}
                                </td>
                                <td>
                                    {{employee.salary}}
                                </td>
                                <td>
                                    {{getPositionName(employee.positionId)}}
                                </td>
                                <td>
                                    {{getDepartmentName(employee.departmentId)}}
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
                        <table border="1px" width="100%">
                            <tr>
                                <td>
                                    Nazwisko: <input type="text" ng-model="selected.surname"/>
                                </td>
                                <td>
                                    Imię: <input type="text" ng-model="selected.name"/>
                                </td>
                                <td>
                                    PESEL: <input type="text" ng-model="selected.pesel"/>
                                </td>
                                <td>
                                    Telefon: <input type="text" ng-model="selected.phone"/>
                                </td>
                                <td>
                                    Wypłata: <input type="text" ng-model="selected.salary"/>
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
                            <tr>
                                <td>
                                    Stanowisko: {{selected.positionId}}
                                    <select ng-model="selected.positionId">
                                        <option ng-repeat="position in positions" value="{{position.id}}">
                                            {{position.name}}
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    Wydział: {{selected.departmentId}}
                                    <select ng-model="selected.departmentId">
                                        <option ng-repeat="department in departments" value="{{department.id}}">
                                            {{department.name}}
                                        </option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>