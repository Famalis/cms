<%@tag import="dto.UserDTO"%>
<%@tag import="services.SystemConfigurationService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html ng-app = "cms">
    <head>
        <!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>-->
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js"></script>  
        <script src="/CMS/resources/js/services.js"></script>  
        <style type="text/css">a.ui-dialog-titlebar-close { display:none }</style>
        <link href="/CMS/resources/css/genericCSS.css" rel="stylesheet" type="text/css">
        <link href="/CMS/resources/css/hr-full.css" rel="stylesheet" type="text/css">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,600,700,300,800,400&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" type="text/css" rel="stylesheet" />

        <title>HR System</title>       
    </head>
    <body>

        <%

            UserDTO user = (UserDTO) jspContext.getAttribute("user", PageContext.SESSION_SCOPE);
            SystemConfigurationService sysConfig = (SystemConfigurationService) jspContext.getAttribute("sysConfig", PageContext.SESSION_SCOPE);
            if (user == null) {
                jspContext.setAttribute("user", new UserDTO());
            }
            if (sysConfig == null) {
                jspContext.setAttribute("sysConfig", new SystemConfigurationService());
            }

        %> 

        <c:if test="${user.id != null}">

            <div id="idletimeout">
                <div class="idletimeout-top">
                    <div class="idletimeout-sign"><img src="/CMS/resources/images/warning.png" alt=""/></div>
                    <span style="font-weight:700;" class="idletimeout-header">Twoja sesja wkrótce wygaśnie...</span></div>

                <div class="idletimeout-tekst">Zostaniesz wylogowany za <span style="font-weight:700;" id="odliczanie"><!-- countdown place holder --></span> sekund z powodu braku aktywności.
                    <br>Aby kontynuować pracę <a id="idletimeout-resume" href="#">kliknij tutaj</a> i zapomnij o sprawie... na jakiś czas... :)</div>



            </div>



            <!--<div id="dialog" title="Your session is about to expire!">
                <p>
                    <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
                    You will be logged off in <span id="dialog-countdown" style="font-weight:bold"></span> seconds.
                </p>

                <p>Do you want to continue your session?</p>
            </div>-->

            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js" type="text/javascript"></script>
            <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
            <script src="/CMS/resources/js/idleTimeout.js" type="text/javascript"></script>
            <script src="/CMS/resources/js/jquery.blockUI.js"></script>
            <script src="/CMS/resources/js/idleTimer.js" type="text/javascript"></script>
            <script type="text/javascript">
                $.idleTimeout('#idletimeout', '#idletimeout a', {
                    idleAfter: ${sysConfig.idleTimeout},
                    pollingInterval: 2,
                    serverResponseEquals: 'OK',
                    onTimeout: function() {
                        $(this).fadeOut();
                        window.location = "/CMS/logout.htm";
                    },
                    onIdle: function() {
                        $(this).fadeIn(); // show the warning bar
                        $.blockUI({message: null});

                    },
                    onCountdown: function(counter) {
                        $(this).find("#odliczanie").html(counter); // update the counter
                    },
                    onResume: function() {
                        $(this).fadeOut(); // hide the warning bar
                        $.unblockUI();
                    }
                });
            </script>
            <!--<script type="text/javascript">
                $("#dialog").dialog({
                    autoOpen: false,
                    modal: true,
                    width: 400,
                    height: 200,
                    closeOnEscape: false,
                    draggable: false,
                    resizable: false,
                    buttons: {
                        'Yes, Keep Working': function() {
                            $(this).dialog('close');
                        },
                        'No, Logoff': function() {

                            $.idleTimeout.options.onTimeout.call(this);
                        }
                    }
                });

                var $countdown = $("#dialog-countdown");

                $.idleTimeout('#dialog', 'div.ui-dialog-buttonpane button:first', {
                    idleAfter: 5,
                    pollingInterval: 2,
                    onTimeout: function() {
                        window.location = "/CMS/logout.htm";
                    },
                    onIdle: function() {
                        $(this).dialog("open");
                    },
                    onCountdown: function(counter) {
                        $countdown.html(counter);
                    }
                });

            </script>-->   
        </c:if>

        <div class="container">
            <div class="top-green"></div>
            <div class="top-nav">
                <div class="logo"></div>

                <div class="logout-button">
                    <form action="/CMS/logout.htm" method="POST">
                        <input type="submit" name="logoutButton" value=""/>
                    </form>
                </div>

                <div class="acc-config">
                    <div class="user-img"><img src="/CMS/PhotoShowServlet?empId=${user.employeeId}" style="border: 3px solid #6e7884;border-radius: 50%" width="64" height="64"  alt=""/></div>

                    <div class="user-welcome">witaj<br>
                        <span style="font-weight:700;">${user.name} ${user.surname}</span>
                    </div>
                    <div class="separator"></div>
                    <a href="/CMS/login.htm"><div class="user-edit">zobacz<br>
                            <span style="font-weight:700;">Swój profil</span>
                        </div></a>
                </div>

            </div> <!-- end of top-nav -->






            <div class="content">
                <div class="left-nav">


                    <ul>
                        <li id="nav14">Zarządzanie zasobami</li>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ViewCustomers')}">
                            <a href="/CMS/customerList.htm"><li id="nav0">Klienci</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewEmployees')}">  
                            <a href="/CMS/employeeList.htm"><li id="nav1">Pracownicy</li></a>
                                </c:if>

                        <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                      user.privilegeKeyCodes.contains('FileList')}"> 
                              <a href="/CMS/fileList.htm"><li id="nav11">Pobieranie plików</li></a>
                                  </c:if>

                        <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                      user.privilegeKeyCodes.contains('ViewPositions')}">
                              <a href="/CMS/positionList.htm"><li id="nav2">Stanowiska</li></a>
                                  </c:if>

                        <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                      user.privilegeKeyCodes.contains('ViewTerminals')}">
                              <a href="/CMS/terminalList.htm"><li id="nav3">Terminale</li></a>
                                  </c:if>

                        <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                      user.privilegeKeyCodes.contains('ViewContracts')}">
                              <a href="/CMS/contractList.htm"><li id="nav12">Umowy</li></a>
                                  </c:if>

                        <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                      user.privilegeKeyCodes.contains('ViewDepartments')}">
                              <a href="/CMS/departmentList.htm"><li id="nav4">Wydziały</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                                user.privilegeKeyCodes.contains('ReportsPrint')}">
                              <a href="/CMS/reportPrint.htm"><li id="nav5">Wydruk raportów</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all')}"><li id="nav6">Konfiguracja systemu</li></c:if>
                              <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                            user.privilegeKeyCodes.contains('ManageGroups')}">
                              <a href="/CMS/groupList.htm"><li id="nav9">Grupy</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                                user.privilegeKeyCodes.contains('ManagePrivilegeKeys')}">
                              <a href="/CMS/privilegeKeyList.htm"><li id="nav8">Klucze</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                                user.privilegeKeyCodes.contains('SystemConfig')}">
                              <a href="/CMS/systemConfig.htm"><li id="nav13">Ustawienia systemowe</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                                user.privilegeKeyCodes.contains('ManageUsers')}">
                              <a href="/CMS/userList.htm"><li id="nav7">Użytkownicy</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                                user.privilegeKeyCodes.contains('ManageFiles')}">
                              <a href="/CMS/fileListUpload.htm"><li id="nav10">Zarządzanie plikami</li></a>
                                  </c:if>
                                  <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                                user.privilegeKeyCodes.contains('ViewDictType')}">
                              <a href="/CMS/dictionaryTypeList.htm"><li id="nav10">Rodzaje słowników</li></a>
                                  </c:if>
                    </ul>


                </div> 
                <div class="content-table">
                    <jsp:doBody/>



                </div><!-- end of content-table -->

            </div> <!-- end of content --> 
        </div>




        <!--<div>          
            <table style="width: 100%; height: 100%">
                <tr>
                    <td colspan="2" style="height: 1%; background-color: n12">
                        <div id="pageheader">
                            <p style="text-align: center">Easy HR</p>
        <jsp:invoke fragment="header"/>
    </div>
</td>
</tr>
<tr>
<td rowspan="2" style="vertical-align: top; width: 15%; background-color: n12">
    <div id="menu">
        <a href="/CMS/login.htm">Zarządzanie kontem</a><br/>  
<<<<<<< HEAD





=======
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
>>>>>>> origin/master


    </div>
</td>
<td style="vertical-align: top">                        



</td>
</tr>
<tr>
<td style="height: 1%; background-color: n12">
    <div id="pagefooter">
        <p style="text-align: right">Powered by Spring MVC | JSP | AngularJS | MySQL</p>
        <jsp:invoke fragment="footer"/>
    </div>
</td>
</tr>
</table>
</div>-->
    </body>
</html>
