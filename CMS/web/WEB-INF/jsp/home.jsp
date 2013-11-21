<%-- 
    Document   : home
    Created on : 2013-09-30, 19:11:29
    Author     : Sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
        <style>
            td {
                text-align: left; 
            }
            .textLink {
                color: blue;
                text-decoration: underline;
                text-align: center;
            }
        </style>
    </head>
    <body ng-app>
        <div align="center">
            <table>
                <tr ng-hide="newAccount">
                    <td>
                        <form action="/CMS/login.htm" method="POST">
                            Login <input type="text" name="login"/>
                            Hasło <input type="password" name="password"/>
                            <input type="submit" name="loginButton" value="Zaloguj"/>
                        </form>
                    </td>
                </tr>
                <tr ng-show="!newAccount">
                    <td class="textLink" ng-click="newAccount = true">
                        Złóż prośbę o konto ->
                    </td>
                </tr>
                <tr ng-show="newAccount">
                    <td>
                        Nazwa użytkownika:<br/>
                        Hasło:<br/>
                        Imię i nazwisko:<br/>
                        Adres email:<br/>
                    </td>
                    <td>
                        <input type="text" name="username"/><br/>
                        <input type="password" name="password"/><br/>
                        <input type="text" name="nameAndSurname"/><br/>
                        <input type="text" name="email"/><br/>
                    </td>
                </tr>
                <tr ng-show="newAccount">
                    <td colspan="2">
                        <input type="button" value="Wyślij mail"/>
                    </td>
                </tr>
                <tr ng-show="newAccount">
                    <td colspan="2" class="textLink" ng-click="newAccount = false">
                        <- Powrót do strony logowania
                    </td>
                </tr>
            </table>
        </div>
</html>