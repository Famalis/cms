<%-- 
    Document   : userList
    Created on : 2013-10-04, 18:50:50
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/userListCtrl.js"></script>
        <div ng-controller="UserListCtrl">  
            <t:filterTable/>
            <t:dataTable/>
            <table>
                <tr>
                    <td>
                        <t:jsonOperations/>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <table ng-show="editMode" class="genericTable">
                            <tr>
                                <td>
                                    Grupa: <input type="text" ng-model="selected.groupId"/>
                                </td>
                                <td>
                                    Tło :<input type="text" ng-model="selected.bgcolor"/>
                                </td>
                                <td>
                                    Pracownik posiadający konto:
                                    <select ng-model="selected.employeeId">
                                        <option ng-repeat="emp in employees" value="{{emp.id}}" ng-selected="selected.employeeId == emp.id">
                                            {{emp.surname}} {{emp.name}}</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="submit" ng-click="save()" value="Zapisz zmiany">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

        </div>
    </jsp:body>
</t:genericTemplate>
