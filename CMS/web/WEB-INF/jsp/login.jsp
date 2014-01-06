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
            <c:if test="${user.name != null}">
                <table>
                    <tr>
                        <td rowspan="6">
                            <div align="center">
                                <img src="/CMS/PhotoShowServlet?empId=${user.employeeId}" alt="Brak zdjęcia"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Imię: {{employee.name}}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Nazwisko: {{employee.surname}}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            PESEL: {{employee.pesel}}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Telefon: {{employee.phone}}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Wypłata: {{employee.salary}}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form action="/CMS/uploadPhoto.htm" method="POST"
                                  enctype="multipart/form-data">
                                Plik: <input type="file" name="file"/>
                                <input type="submit" value="Wyslij plik"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </c:if>
            <h2>

                ${error}
            </h2>
            <c:if test="${user.name == null}">
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