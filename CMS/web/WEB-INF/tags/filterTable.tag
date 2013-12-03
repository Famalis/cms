<%-- 
    Document   : filterTable
    Created on : 2013-11-27, 09:01:24
    Author     : Sergio
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<table class="filterTable">
    <tr>
        <th colspan="2" ng-click="showFilter = !showFilter">
            Filtrowanie danych
        </th>
    </tr>
    <tr ng-show="showFilter">
        <td>
            Wyszukaj: <input type="text" ng-model="searchText"/>
        </td>
        <td>
            Sortuj po:
            <select ng-model="orderColumn">
                <option ng-repeat="(columnName, columnValue) in columns" value="{{columnName}}">{{columnValue}}</option>
            </select>
            Kolejność: <input type="checkbox" ng-init="reverse=false" ng-model="reverse"/>
            <span ng-show="reverse">Malejąco</span>
            <span ng-hide="reverse">Rosnąco</span>
        </td>
    </tr>
</table>