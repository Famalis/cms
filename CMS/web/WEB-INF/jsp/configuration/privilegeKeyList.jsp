<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/privilegeKeyListCtrl.js"></script>
        <div ng-controller="PrivilegeKeyListCtrl">  
            <h1>Nie zmieniać istniejących</h1>
            <table width="100%">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <th>
                                    ID
                                </th>
                                <th>
                                    Kod
                                </th>
                                <th width="60%">
                                    Opis
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: privilegeKey == selected}" ng-repeat="privilegeKey in privilegeKeys" ng-click="select(privilegeKey)">
                                <td>
                                    {{privilegeKey.id}}
                                </td>
                                <td>
                                    {{privilegeKey.code}}
                                </td>
                                <td>
                                    {{privilegeKey.description}}
                                </td>
                            </tr>                            
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <t:jsonOperations/>
                    </td>
                </tr>
            </table>
                    <table class="genericTable">
                <tr ng-show="editMode">
                    <td>
                        Nazwa: <input type="text" ng-model="selected.code"/>
                    </td>
                    <td>
                        Opis <textarea ng-model="selected.description"> </textarea>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>