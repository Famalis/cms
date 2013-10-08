<%-- 
    Document   : userList
    Created on : 2013-10-04, 18:50:50
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-controller="./resources/js/userListCtrl.js" ng-init="osoba = ${json}">
        <p>{{osoba.name}}</p>
        <img src="/WEB-INF/resources/pic.jpg"/>
    </body>
</html>
