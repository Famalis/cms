<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/positionListCtrl.js"></script>
        <div ng-controller="PositionListCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <t:filterTable/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <th>
                                    Nazwa
                                </th>
                                <th>
                                    Opis
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: position == selected}" ng-repeat="position in positions | filter:searchText | orderBy:orderColumn:reverse" ng-click="select(position)">
                                <td>
                                    {{position.name}}
                                </td>
                                <td>
                                    {{position.description}}
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
                <tr ng-show="editMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwa: <input type="text" ng-model="selected.name"/>
                                </td>
                                <td>
                                    Opis: <input type="text" ng-model="selected.description"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>