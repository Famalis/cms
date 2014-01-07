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
            <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
        
            <t:dataTable/>
            
                        <t:jsonOperations/>
                
            <table>
                <tr>
                    <td>
                        <table ng-show="editMode" class="genericTable">
                            <tr>
                                <td>
                                    Login: <input type="text" ng-model="selected.login"/>
                                </td>
                                <td>
                                    Hasło: <input type="text" ng-model="selected.password"/>
                                </td>
                                <td>
                                    Imię: <input type="text" ng-model="selected.name"/>
                                </td>
                                <td>
                                    Nazwisko: <input type="text" ng-model="selected.surname"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Grupa:
                                    <select ng-model="selected.groupId">
                                        <option ng-repeat="group in groups" value="{{group.id}}" ng-click="selected.groupName = getGroupName(group.id)"
                                                ng-selected="selected.groupId == group.id">
                                            {{group.name}}
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    Pracownik posiadający konto:
                                    <select ng-model="selected.employeeId">
                                        <option ng-repeat="emp in employees" value="{{emp.id}}" ng-selected="selected.employeeId == emp.id"
                                                ng-click="selected.name = emp.name; selected.surname = emp.surname; selected.login = generateLogin(emp)">
                                            {{emp.surname}} {{emp.name}}</option>
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
