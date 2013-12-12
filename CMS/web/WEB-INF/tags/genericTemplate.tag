<%@tag import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html ng-app = "cms">
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>  
        <script src="/CMS/resources/js/services.js"></script>  
        <style type="text/css">a.ui-dialog-titlebar-close { display:none }</style>
        <LINK href="/CMS/resources/css/genericCSS.css" rel="stylesheet" type="text/css">
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" type="text/css" rel="stylesheet" />
        <title>Easy HR</title>       
    </head>
    <body>

        <%

            UserDTO user = (UserDTO) jspContext.getAttribute("user", PageContext.SESSION_SCOPE);
            if (user == null) {
                jspContext.setAttribute("user", new UserDTO());
            }
        %> 
        
        <c:if test="${user.id != null}">                                         
                        
        <div id="dialog" title="Your session is about to expire!">
                <p>
                        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
                        You will be logged off in <span id="dialog-countdown" style="font-weight:bold"></span> seconds.
                </p>

                <p>Do you want to continue your session?</p>
        </div>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
        <script src="/CMS/resources/js/idleTimeout.js" type="text/javascript"></script>
        <script src="/CMS/resources/js/idleTimer.js" type="text/javascript"></script>

        <script type="text/javascript">
        $("#dialog").dialog({
                autoOpen: false,
                modal: true,
                width: 400,
                height: 200,
                closeOnEscape: false,
                draggable: false,
                resizable: false,
                buttons: {
                        'Yes, Keep Working': function(){
                                $(this).dialog('close');
                        },
                        'No, Logoff': function(){

                                $.idleTimeout.options.onTimeout.call(this);
                        }
                }
        });

        var $countdown = $("#dialog-countdown");

        $.idleTimeout('#dialog', 'div.ui-dialog-buttonpane button:first', {
                idleAfter: 600,
                pollingInterval: 2,

                onTimeout: function(){
                        $.ajax({
                    type: "POST",
                    cache: false,
                    url: "/CMS/logout.htm"                
                });
                window.location = "/CMS/home.htm";
                },
                onIdle: function(){
                        $(this).dialog("open");
                },
                onCountdown: function(counter){
                        $countdown.html(counter);
                    }      
        });

        </script>        
        </c:if>
        <div>          
            <table style="width: 100%; height: 100%">
                <tr>
                    <td colspan="2" style="height: 1%; background-color: ${user.bgcolor}">
                        <div id="pageheader">
                            <p style="text-align: center">Easy HR</p>
                            <jsp:invoke fragment="header"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td rowspan="2" style="vertical-align: top; width: 15%; background-color: ${user.bgcolor}">
                        <div id="menu">
                            <a href="/CMS/login.htm">Zarządzanie kontem</a><br/>  
                            <a href="/CMS/testingPage.htm">Strona do testów</a><br/>  
                            <h3>Zarządzanie zasobami</h3> 
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ViewEmployees')}">                                         
                                  <a href="/CMS/employeeList.htm">Zarządzanie pracownikami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ViewDepartments')}">
                                  <a href="/CMS/departmentList.htm">Zarządzanie wydziałami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ViewPositions')}">
                                  <a href="/CMS/positionList.htm">Zarządzanie stanowiskami</a><br/>
                            </c:if>                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ViewTerminals')}">
                                  <a href="/CMS/terminalList.htm">Zarządzanie terminalami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageCustomers')}">
                                  <a href="/CMS/customerList.htm">Zarządzanie klientami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageContracts')}">
                                  <a href="/CMS/contractList.htm">Zarządzanie umowami</a><br/>
                            </c:if>     
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ReportsPrint')}">
                                  <a href="/CMS/reportPrint.htm">Drukowanie raportów</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('FileList')}">
                                  <a href="/CMS/fileList.htm">Pobieranie plików</a><br/>
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
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ManageFiles')}">
                                  <a href="/CMS/fileListUpload.htm">Zarządzanie plikami</a><br/>
                            </c:if>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('SystemConfig')}">
                                  <a href="/CMS/systemConfig.htm">Wartości konfiguracyjne</a><br/>
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
                    <td style="height: 1%; background-color: ${user.bgcolor}">
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