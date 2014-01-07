<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/systemConfigCtrl.js"></script>
        <div ng-controller="SystemConfigCtrl">
            <div class="top-right">
                <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

            </div>

            <div class="right-addNew" id="addNew" ng-show="editMode">
                <div class="addNew-header">Dodaj lub edytuj ustawienia systemowe</div>
                <div class="addNew-line"></div>
                <div class="addNew-inputs">

                    <div class="addNew-input">
                        <input class="imie-field" placeholder="Nazwa..." type="text" ng-model="selected.name"/>    
                    </div>

                    <div class="addNew-input">
                        
                        <input class="wartosc-field" placeholder="Wartość..." type="text" ng-model="selected.value"/>
                    </div>
                    <div class="addNew-input">
                        <input class="opis-long-field" placeholder="Opis..." type="text" ng-model="selected.description"/>
                    </div>
                   







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
                <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
            </div>

            <t:dataTable/>

           <!-- <table ng-show="editMode" class="genericTable">
                <tr>
                    <td>
                        Nazwa: <input type="text" ng-model="selected.name"/>
                        Wartość: <input type="text" ng-model="selected.value"/>
                        Opis: <textarea ng-model="selected.description">
                            
                        </textarea>
                    </td>
                </tr>
            </table>-->
        </div>
    </jsp:body>
</t:genericTemplate>
