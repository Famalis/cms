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
            <div class="right-addNew" id="addNew" ng-show="editMode">
                <div class="addNew-header">Dodaj lub edytuj dane użytkownika</div>
                <div class="addNew-line"></div>
                <div class="addNew-inputs">

                    <div class="addNew-input">
                        
                        <input class="login-field" placeholder="Login..." type="text" ng-model="selected.login"/>
                    </div>

                    <div class="addNew-input">
                        
                       
                        <input class="haslo-field" placeholder="Hasło..." type="text" ng-model="selected.password"/>
                    </div>
         
                  <div class="addNew-input">
                        <div class="stanowisko-select"> 
                             <select ng-model="selected.groupId">
                                        <option value="-1">Wybierz grupę...</option>
                                        <option ng-repeat="group in groups" value="{{group.id}}" ng-click="selected.groupName = getGroupName(group.id)"
                                                ng-selected="selected.groupId == group.id">
                                            {{group.name}}
                                        </option>
                                    </select>
                        </div>
                    </div>
                     <div class="addNew-input">
                        <div class="wydzial-select"> 
                             <select ng-model="selected.employeeId">
                                        <option value="-1">Wybierz pracownika...</option>
                                        <option ng-repeat="emp in employees" value="{{emp.id}}" ng-selected="selected.employeeId == emp.id"
                                                ng-click="selected.login = generateLogin(emp)">
                                            {{emp.surname}} {{emp.name}}</option>
                                    </select>
                        </div>
                    </div>

                    <!--<div class="addNew-input">
                        <div class="wydzial-select"> 
                            <select> 
                                <option selected="selected">Wydział...</option>
                                <option>Firefox</option> 
                                <option>Webkit</option> 
                            </select> 
                        </div>
                    </div>-->


                </div>
                <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
            </div>
        
            <t:dataTable/>
                
            <!--<table>
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
                            </tr>
                            <tr>
                                <td>
                                    Grupa:
                                    <select ng-model="selected.groupId">
                                        <option value="-1">Wybierz grupę...</option>
                                        <option ng-repeat="group in groups" value="{{group.id}}" ng-click="selected.groupName = getGroupName(group.id)"
                                                ng-selected="selected.groupId == group.id">
                                            {{group.name}}
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    Pracownik posiadający konto:
                                    <select ng-model="selected.employeeId">
                                        <option value="-1">Wybierz pracownika...</option>
                                        <option ng-repeat="emp in employees" value="{{emp.id}}" ng-selected="selected.employeeId == emp.id"
                                                ng-click="selected.login = generateLogin(emp)">
                                            {{emp.surname}} {{emp.name}}</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>-->

        </div>
    </jsp:body>
</t:genericTemplate>
