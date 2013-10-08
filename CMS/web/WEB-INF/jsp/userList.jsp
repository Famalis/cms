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
        <script src="/CMS/resources/js/userListCtrl.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-controller="UserListCtrl">
        <div>
        ${json}<br/>
        ${someVar}<br/>
        <p ng-repeat="user in users">
            {{user.id}} {{user.name}}
        </p>
        {{testVar}}
        </div>
    </body>
</html>
