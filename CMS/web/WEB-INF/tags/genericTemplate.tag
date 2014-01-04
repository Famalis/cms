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
        <link href="/CMS/resources/css/genericCSS.css" rel="stylesheet" type="text/css">
        <link href="/CMS/resources/css/hr-full.css" rel="stylesheet" type="text/css">
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" type="text/css" rel="stylesheet" />
   
        <title>HR System</title>       
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
                    idleAfter: 600,
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

            </script>        
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
                    <div class="user-img"><img src="/CMS/resources/images/photo.png" width="70" height="70"  alt=""/></div>

                    <div class="user-welcome">witaj<br>
                        <span style="font-weight:700;">${user.name} ${user.surname}</span>
                    </div>
                    <div class="separator"></div>
                    <a href="/CMS/login.htm"><div class="user-edit">edytuj<br>
                        <span style="font-weight:700;">Swój profil</span>
                    </div></a>
                </div>

            </div> <!-- end of top-nav -->

            <div class="top-search">

                <div class="zasoby">
                    <div class="zasoby-ico"><img src="/CMS/resources/images/zarzadanie-ico.png" width="22" height="26"  alt=""/></div>
                    <div class="zasoby-tekst">Zarządzanie zasobami</div>
                </div>

                <div class="more-button" id="flip"></div>
                <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

            </div> <!-- end of top-search -->

            <div class="content">
                <div class="left-nav">

                    <ul>

                        <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                      user.privilegeKeyCodes.contains('ManageCustomers')}">
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
                                      user.privilegeKeyCodes.contains('ManageContracts')}">
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
                    </ul>


                </div> <!-- end of left-nav -->
                <div class="right-addNew" id="panel">
                    <div class="addNew-header">Dodaj nowego pracownika</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">
                        <div class="addNew-input"><input class="imie-field" type="text" placeholder="Imię..."/></div>
                        <div class="addNew-input"><input class="nazwisko-field" type="text" placeholder="Nazwisko..."/></div>
                        <div class="addNew-input"><input class="pesel-field" type="text" placeholder="Numer PESEL..."/></div>
                        <div class="addNew-input"><input class="telefon-field" type="text" placeholder="Telefon..."/></div>
                        <div class="addNew-input"><input class="kraj-field" type="text" placeholder="Kraj..."/></div>
                        <div class="addNew-input"><input class="ulica-field" type="text" placeholder="Nazwa ulicy..."/></div>
                        <div class="addNew-input"><input class="budynek-field" type="text" placeholder="Numer budynku..."/></div>
                        <div class="addNew-input"><input class="mieszkanie-field" type="text" placeholder="Numer mieszkania..."/></div>
                        <div class="addNew-input"><input class="miejscowosc-field" type="text" placeholder="Miejscowość..."/></div>

                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select> 
                                    <option selected="selected">Stanowisko...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div>

                        <div class="addNew-input">
                            <div class="wydzial-select"> 
                                <select> 
                                    <option selected="selected">Wydział...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div>


                    </div>
                </div> <!-- end of right-addNew -->
                <div class="content-table">
                    <jsp:doBody/>


                </div> <!-- end of content-table -->
            </div><!-- end of content -->

        </div> <!-- end of container --> 





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
