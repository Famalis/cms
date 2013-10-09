<%-- 
    Document   : userList
    Created on : 2013-10-04, 18:50:50
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html ng-app>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
        <script src="/CMS/resources/js/userListCtrl.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-controller="UserListCtrl">
        <t:genericTemplate>
            <jsp:body>
                <c:if test="${userConfig.groupId == 1}">
                    <div>
                        <h1 style="text-align: center" ng-show="status != ''">
                            {{status}}<img src="/CMS/resources/loader.gif" alt="ble"/>
                        </h1>                        
                        <table border="1px" style="width: 100%;">
                            <tr style="background-color: grey">
                                <td>
                                    
                                </td>
                                <td>
                                    Nazwisko
                                </td>
                                <td>
                                    Imię
                                </td>
                                <td>
                                    Kolor tła
                                </td>
                                <td>
                                    Grupa
                                </td>
                            </tr>
                            <tr ng-repeat="user in users">
                                <td>
                                    <input ng-click="selectUser(user.id)" type="radio" name="selectedUser" value="{{user.id}}"/>
                                </td>
                                <td>
                                    {{user.surname}}
                                </td>
                                <td>
                                    {{user.name}}
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
                                    Grupa: <input type="text" ng-model="selectedUser.groupId"/>
                                </td>
                                <td>
                                    Tło :<input type="text" ng-model="selectedUser.bgcolor"/>
                                </td>
                                <td>
                                    <input type="submit" ng-click="save()" value="Zapisz zmiany">
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
                <c:if test="${userConfig.groupId != 1}">
                    <div>
                        <p>
                            Brak uprawnień
                        </p>
                    </div>
                </c:if>
                <a href="/CMS/login.htm">Powrót</a>
            </jsp:body>
        </t:genericTemplate>
    </body>
</html>
