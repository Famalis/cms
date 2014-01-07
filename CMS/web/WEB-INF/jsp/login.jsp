<%-- 
    Document   : login
    Created on : 2013-10-01, 14:19:43
    Author     : Sergio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/loginCtrl.js"></script>
        <div ng-controller="LoginCtrl">
            <div class="top-right">
                <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>


            </div>
            <c:if test="${user.employeeId != null}">
                <div class="right-addNew" id="addNew">
                    <div class="addNew-header">Twój profil</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">
                        <div style="float:left;clear:right;margin-bottom: 30px;margin-right: 30px;" class="zdjecie-uzytkownika">

                            <img src="/CMS/PhotoShowServlet?empId=${user.employeeId}" alt="Brak zdjęcia"/>

                        </div>
                        <div style="float:left;clear: right;">
                            <div>
                                <input class="imie-field" placeholder="Imię..." type="text" ng-model="employee.name"/>
                            </div>

                            <div style="margin-top: 14px;">
                                <input class="nazwisko-field" placeholder="Nazwisko..." type="text" ng-model="employee.surname"/>
                            </div>

                            <div style="margin-top: 14px;">
                                <input class="pesel-field" placeholder="{{employee.pesel}}" type="text" disabled="disabled"/>
                            </div>

                            <div style="margin-top: 14px;">

                                <input class="telefon-field" placeholder="Telefon..." type="text" ng-model="employee.phone"/>
                            </div>
                        </div>
                            <div style="margin-left: 30px;float: left;clear: right;width:1px;height:220px;background-color:#31a984;"></div>
                       
                        <div style="float:left;clear: right;margin-top: 0px;margin-left:30px;color: #218164;font-weight: 700;">
                            <form action="/CMS/uploadPhoto.htm" method="POST" enctype="multipart/form-data">
                                Plik: <input type="file" name="file"/>
                                <input type="submit" value="Wyslij plik"/>
                            </form>
                        </div>
                        <div style="float: right;clear: both;margin-bottom: 30px;"><input class="zapisz-dane" type="button" ng-click="save()" value="ZAPISZ"/></div>

                        <!--<div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select> 
                                    <option selected="selected">Stanowisko...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div> -->

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

                </div>

            </c:if>
            <h2>

                ${error}
            </h2>
            <c:if test="${user.employeeId == null}">
                <form action="/CMS/login.htm" method="POST">
                    Login <input type="text" name="login"/>
                    Hasło <input type="password" name="password"/>
                    <input type="text" name="stayLogged" ng-hide="true" value="false"/>
                    <input type="submit" name="loginButton" value="Zaloguj"/>
                </form>
            </c:if>        
            <c:if test="${user == null}">
                <a href="/CMS/newUser.htm">Załóż konto</a>
            </c:if>
        </div>
    </jsp:body>
</t:genericTemplate>