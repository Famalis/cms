<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/terminalListCtrl.js"></script>
        <div ng-controller="TerminalListCtrl">
            <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
            <div class="right-addNew" id="addNew" ng-show="editMode">
                    <div class="addNew-header">Dodaj lub edytuj terminal</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">

                        

                        <div class="addNew-input">
                            
                            <input class="opis-field" placeholder="Opis..." type="text" ng-model="selected.description"/>
                        </div>
              




                    </div>
                    <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
                </div>
            
                        <t:dataTable/>
                
                        
                  
                        <!--<div ng-show="editMode">
                    
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Opis: <input type="text" ng-model="selected.description"/>
                                </td>
                            </tr>
                        </table>
                    
                        </div>
                        <div ng-show="selected">
                    
                        <table class="genericTable">                            
                            <tr ng-show="size(selected.timestamps) == 0">
                                <td colspan="2">
                                    Brak logów
                                </td>
                            </tr>
                            <tbody ng-show="size(selected.timestamps) > 0">
                            <tr>
                                <th width="50%">
                                    Timestamp:
                                </th>
                                <th>
                                    Pracownik:
                                </th>
                            </tr>
                            <tr ng-repeat="(timestamp, employee) in selected.timestamps">                                
                                <td>                                    
                                    {{timestamp}}
                                </td>
                                <td>
                                    {{employee}}
                                </td>
                            </tr>
                            </tbody>
                        
                        </table>
                   
        </div>-->
    </jsp:body>
</t:genericTemplate>