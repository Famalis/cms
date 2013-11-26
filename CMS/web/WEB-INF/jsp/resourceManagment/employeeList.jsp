<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeeListCtrl.js"></script>
        <form name="myForm">
            <div ng-controller="EmployeeListCtrl">  
                <table width="100%">
                    <tr>
                        <td>
                            <table class="genericTable">
                                <tr>
                                    <th>
                                        Imię i Nazwisko
                                    </th>
                                    <th>
                                        PESEL
                                    </th>
                                    <th>
                                        Telefon
                                    </th>
                                    <th>
                                        Wypłata
                                    </th>
                                    <th>
                                        Stanowisko
                                    </th>
                                    <th>
                                        Wydział
                                    </th>
                                </tr>
                                <tr ng-class="{selectedTableRow: employee == selected}" ng-repeat="employee in employees" ng-click="select(employee)">
                                    <td>
                                        <a href="/CMS/employeePage/{{employee.id}}.htm">
                                            {{employee.name}} {{employee.surname}}
                                        </a>
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
                            <table class="genericTable">
                                <tr>
                                    <td>
                                        Nazwisko: <input type="text" ng-model="selected.surname"/>
                                    </td>
                                    <td>
                                        Imię: <input type="text" ng-model="selected.name"/>
                                    </td>
                                    <td>
                                        PESEL: <input name="pesel" type="text" ng-model="selected.pesel" ng-pattern="/^[0-9]+$/" required="required"  ng-minlength="11" ng-maxlength="11" />
                                        <br/>
                                        <span class="error" ng-show="myForm.pesel.$error.required">Pesel potrzebny!</span>
                                        <span class="error" ng-show="myForm.pesel.$error.pattern"> Proszę wprowadzić tyko cyfry</span>
                                        <span class="error" ng-show="myForm.pesel.$error.minlength"> Za mało cyfr</span>
                                        <span class="error" ng-show="myForm.pesel.$error.maxlength"> Za dużo cyfr</span>
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
        </form>
    </jsp:body>
</t:genericTemplate>