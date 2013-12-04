<%-- 
    Document   : dataTable
    Created on : 2013-12-04, 07:10:10
    Author     : Sergio
--%>

<%@tag description="Tabela z danymi" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<table class="genericTable">
    <thead>
        <tr>
            <th ng-class="{selectedTableSort: columnName == orderColumn, genericTableHeader: columnName != orderColumn}" ng-repeat="(columnName, columnValue) in columns">
                <a ng-click="$parent.orderColumn = columnName; $parent.reverse = !$parent.reverse">{{columnValue}}</a>
            </th>        
        </tr>
    </thead>
    <tbody>
        <tr ng-class="{selectedTableRow: obj == selected}" ng-repeat="obj in objects | filter:searchText | orderBy:orderColumn:reverse">
            <td ng-repeat="(columnName, columnValue) in columns" ng-click="$parent.select(obj)">
                {{obj[columnName]}}
            </td>
        </tr>
    </tbody>
</table>