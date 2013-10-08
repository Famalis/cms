<%-- 
    Document   : userList
    Created on : 2013-10-04, 18:50:50
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
        <script src="/CMS/resources/js/userListCtrl.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-controller="UserListCtrl">
        <c:if test="${userConfig.groupId == 1}">
            <div>
                {{status}}
                <table border="1px" style="width: 100%;">
                    <tr>
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
        </c:if>
        <c:if test="${userConfig.groupId != 1}">
            <div>
                <p>
                    Brak uprawnień
                </p>
            </div>
        </c:if>
        <a href="/CMS/login.htm">Powrót</a>
    </body>
</html>
