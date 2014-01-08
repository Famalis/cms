<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/groupListCtrl.js"></script>
        <div ng-controller="GroupListCtrl">
            <div class="top-right">
                <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

            </div>
            <div class="right-addNew" id="addNew" ng-show="editMode">
                <div class="addNew-header">Dodaj lub edytuj dane grupy</div>
                <div class="addNew-line"></div>
                <div class="addNew-inputs">

                    <div class="addNew-input">
                        <input class="imie-field" placeholder="Nazwa..." type="text" ng-model="selected.name"/>

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


       <table ng-show="editMode" class="genericTable">
               
                <tr ng-show="selected.id">
                    <td>Klucze:
                        <p ng-repeat="groupPrivKey in privilegeKeys" ng-show="selectedGroupHasKey(groupPrivKey.id)">{{groupPrivKey.code}}</p>
                    </td>
                    <td>Dodaj nowy klucz:
                        <select ng-model="newKeyId">
                            <option ng-repeat="privKey in privilegeKeys" ng-hide="selectedGroupHasKey(privKey.id)" value="{{privKey.id}}">{{privKey.code}}</option> 
                        </select>
                        <input type="button" ng-click="addKey()" value="Dodaj"/>
                        <br/>
                        Usuń istniejący klucz:
                        <select ng-model="oldKeyId">
                            <option ng-repeat="privKey in privilegeKeys" ng-show="selectedGroupHasKey(privKey.id)" value="{{privKey.id}}">{{privKey.code}}</option> 
                        </select>
                        <input type="button" ng-click="removeKey()" value="Usuń"/>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>
