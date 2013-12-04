<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/groupListCtrl.js"></script>
        <div ng-controller="GroupListCtrl"> 
            <table width="100%">
                <tr>
                    <td>
                        <t:filterTable/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <t:dataTable/>
                    </td>
                </tr>           
                <tr>
                    <td>
                        <t:jsonOperations/>
                    </td>
                </tr>
            </table>
            <table ng-show="editMode" class="genericTable">
                <tr>
                    <td>
                        Nazwa: <input type="text" ng-model="selected.name"/>
                    </td>
                </tr>
                <tr ng-show="selected.privilegeKeyIds != undefined">
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
