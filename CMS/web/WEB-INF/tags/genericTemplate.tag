<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html ng-app>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>        
        <title>bleble</title>
    </head>
    <body>
        <table style="width: 100%; height: 100%">
            <tr>
                <td colspan="2" style="height: 1%; background-color: lightgrey">
                    <div id="pageheader">
                        <p style="text-align: center">Zajebisty CMS</p>
                        <jsp:invoke fragment="header"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="2" style="vertical-align: top; width: 10%; background-color: lightgrey">
                    <div id="menu">
                        <a href="/CMS/home.htm">Strona główna</a><br/>
                        <a href="/CMS/login.htm">Zarządzanie kontem</a><br/>
                        <a href="/CMS/articles.htm">Lista artykułów</a><br/>
                    </div>
                </td>
                <td style="vertical-align: top">
                    <div id="body">
                        <jsp:doBody/>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="height: 1%; background-color: lightgrey">
                    <div id="pagefooter">
                        <p style="text-align: right">Powered by Spring MVC | JSP | AngularJS | MySQL</p>
                        <jsp:invoke fragment="footer"/>
                    </div>
                </td>
            </tr>
        </table>
    </body>
</html>