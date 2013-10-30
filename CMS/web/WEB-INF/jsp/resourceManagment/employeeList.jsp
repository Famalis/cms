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
                            </tr>
                            <tr ng-class="{selectedTableRow: employee == selected}" ng-repeat="employee in employees" ng-click="select(employee)">
                                <td>
                                    {{employee.surname}}
                                </td>
                                <td>
                                    {{employee.name}}
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
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>