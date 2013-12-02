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
        <link href="//fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800" rel="stylesheet" type="text/css">
        <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame 
        Remove this if you use the .htaccess -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />

        <link rel="icon" href="/images/favicon.png" type="image/png" />
        <link rel="shortcut icon" href="/favicon.ico" />

        <script type="text/javascript" src="/CMS/resources/js/jquery-2.0.3.min.js"></script>
        <link href="/CMS/resources/stylesheets/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />

    </head>
    <body ng-app>
        <div class="container">

            <div class="top-container">
                <div class="logo"><img src="/CMS/resources/images/hr-logo.png" width="113" height="40"  alt=""/></div>

                <div id="tekst-bg">
                    <div class="button-tekst">
                        nie masz konta?<br>
                        <span style="font-weight:700;" ng-click="newAccount = !newAccount"><a href="">wyślij prośbę</a></span></div>
                </div>
                <div id="strzalka-button"><div class="strzalka"><img src="/CMS/resources/images/strzalka.png" width="11" height="18"  alt=""/></div></div>





            </div> <!-- end of top-container -->
            <div class="form-container">
                <form ng-hide="newAccount" class="formularz" action="/CMS/login.htm" method="POST">
                    <div class="user-icon"><img src="/CMS/resources/images/user-icon.png" width="18" height="18"  alt=""/></div>


                    <input type="text" class="user-name" name="login" placeholder="Nazwa użytkownika" maxlength="32" autocomplete="off" autofocus/>


                    <div class="password-icon"><img src="/CMS/resources/images/password-icon.png" width="18" height="18"  alt=""/></div>


                    <input type="password" class="password-input" name="password" placeholder="Hasło" maxlength="32"/>


                    <input type="submit" class="zaloguj-button" name="loginButton" value="ZALOGUJ">                    
                    <c:if test="${mailSent == true}">
                        Wysłano prośbę
                    </c:if>
                    ${error}
                </form>
                <form ng-show="newAccount" action="/CMS/home.htm" method="POST">
                    <c:if test="${mailSent != true}">
                        <table>
                            <tr>
                                <td>
                                    Nazwa użytkownika:<br/>
                                    Hasło:<br/>
                                    Imię:<br/>
                                    Nazwisko:<br/>
                                    Adres email:<br/>
                                </td>
                                <td>
                                    <input type="text" name="login"/><br/>
                                    <input type="password" name="password"/><br/>
                                    <input type="text" name="name"/><br/>
                                    <input type="text" name="surname"/><br/>
                                    <input type="text" name="email"/><br/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Wyślij mail"/>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${mailSent == true}">
                        Wysłano już prośbę o konto.
                    </c:if>
                </form>

            </div> <!-- end of form-container -->
        </div> <!-- end of container -->      







        <!--<div align="center">
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
        </div>-->
    </body>
</html>