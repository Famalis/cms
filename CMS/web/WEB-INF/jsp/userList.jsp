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
        <script src="/CMS/resources/js/userListCtrl.js"></script>
        <div ng-controller="UserListCtrl">            
                <div>
                    <h1 style="text-align: center" ng-show="status != ''">
                        {{status}}<img src="/CMS/resources/loader.gif" alt="ble"/>
                    </h1>                        
                    <table ng-init="predicate='surname'" border="1px" style="width: 100%; text-">
                        <tr style="background-color: grey; color: lightgrey; font-weight: bold">
                            <td ng-click="predicate='surname'">
                                Nazwisko
                            </td>
                            <td ng-click="predicate='name'">
                                Imię
                            </td>
                            <td ng-click="predicate='login'">
                                Login
                            </td>
                            <td ng-click="predicate='bgcolor'">
                                Kolor tła
                            </td>
                            <td ng-click="predicate='groupId'">
                                Grupa
                            </td>
                        </tr>
                        <tr ng-repeat="user in users | orderBy:predicate" ng-click="select(user)">
                            <td>
                                {{user.surname}}
                            </td>
                            <td>
                                {{user.name}}
                            </td>
                            <td>
                                {{user.login}}
                            </td>
                            <td>
                                {{user.bgcolor}}
                            </td>
                            <td>
                                {{user.groupId}}
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <table>
                        <tr>
                            <td>
                                Grupa: <input type="text" ng-model="selected.groupId"/>
                            </td>
                            <td>
                                Tło :<input type="text" ng-model="selected.bgcolor"/>
                            </td>
                            <td>
                                <input type="submit" ng-click="save()" value="Zapisz zmiany">
                            </td>
                        </tr>
                    </table>
                </div>
        </div>
    </jsp:body>
</t:genericTemplate>
