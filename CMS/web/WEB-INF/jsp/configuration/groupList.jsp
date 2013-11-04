<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/groupListCtrl.js"></script>
        <div ng-controller="GroupListCtrl">  
            <h1 style="text-align: center" ng-show="status != ''">
                {{status}}<img src="/CMS/resources/loader.gif" alt="ble"/>
            </h1>  
            <table class="genericTable">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwa
                                </td>
                                <td>
                                    Uprawnienia
                                </td>
                            </tr>
                            <tr ng-class="{selectedTableRow: group==selected}" ng-repeat="group in groups" ng-click="select(group)">
                                <td>
                                    {{group.name}}
                                </td>
                                <td>
                                    <span ng-repeat="groupPrivKey in privilegeKeys" ng-show="groupHasKey(group, groupPrivKey.id)">{{groupPrivKey.code}} </span>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>                
                <tr ng-show="editMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwa: <input type="text" ng-model="selected.name"/>
                                </td>
                            </tr>
                            <tr ng-show="selected.privilegeKeyIds!=undefined">
                                <td>Klucze:
                                    <p ng-repeat="groupPrivKey in privilegeKeys" ng-show="selectedGroupHasKey(groupPrivKey.id)">{{groupPrivKey.code}}</p>
                                </td>
                                <td>Dodaj nowy klucz:
                                    <select ng-model="newKeyId">
                                        <option ng-repeat="privKey in privilegeKeys" value="{{privKey.id}}">{{privKey.code}}</option> 
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
                    </td>
                </tr>
                <tr>
                    <td>
                    <t:jsonOperations>

                    </t:jsonOperations>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>
