<%@tag import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html ng-app>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>  
        <LINK href="/CMS/resources/css/genericCSS.css" rel="stylesheet" type="text/css">
        <title>Zajebisty Tytuł CMS</title>       
    </head>
    <body>

        <%

            UserDTO user = (UserDTO) jspContext.getAttribute("user", PageContext.SESSION_SCOPE);
            if (user == null) {
                jspContext.setAttribute("user", new UserDTO());
            }
        %> 

        <script src="/CMS/resources/js/genericCtrl.js"></script>
        <div ng-controller="GenericCtrl">          
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
                    <td rowspan="2" style="vertical-align: top; width: 15%; background-color: lightgrey">
                        <div id="menu">
                            <a href="/CMS/home.htm">Strona główna</a><br/>
                            <a href="/CMS/login.htm">Zarządzanie kontem</a><br/>  
                            <h3>Zarządzanie zasobami</h3> 
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageEmployees')}">                                         
                                  <a href="/CMS/employeeList.htm">Zarządzanie pracownikami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageDepartments')}">
                                  <a href="/CMS/departmentList.htm">Zarządzanie wydziałami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManagePositions')}">
                                  <a href="/CMS/positionList.htm">Zarządzanie stanowiskami</a><br/>
                            </c:if>
                            <h3>Konfiguracja systemu</h3>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageUsers')}">
                                  <a href="/CMS/userList.htm">Zarządzanie użytkownikami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManagePrivilegeKeys')}">
                                  <a href="/CMS/privilegeKeyList.htm">Zarządzanie kluczami</a><br>
                            </c:if>                                                        
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageGroups')}">
                                  <a href="/CMS/groupList.htm">Zarządzanie grupami</a><br/>
                            </c:if>


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
        </div>
    </body>
</html>