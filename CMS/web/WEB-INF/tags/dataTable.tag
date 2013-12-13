<%-- 
    Document   : dataTable
    Created on : 2013-12-04, 07:10:10
    Author     : Sergio
--%>

<%@tag description="Tabela z danymi" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<script>

</script>
<table width="100%" ><!--Poprzednia wersja class="genericTable"-->
    <thead>
        <tr>
            <th width="2px">
                Lp.
            </th>
            <!--Poprzednia wersja <th ng-class="{selectedTableSort: attr == orderColumn, columnClasses attr != orderColumn}"-->
            <th ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'"
                class = "{columnClasses[attr]}">
                <a ng-click="$parent.orderColumn = attr;
                        $parent.reverse = !$parent.reverse">{{$parent.columns[attr]}}</a>
            </th>        
        </tr>
    </thead>
    <tbody>
        <tr ng-class="{selectedTableRow: obj == selected}" ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse">
            <td>
                {{$index + 1}}
            </td>
            <td ng-repeat="attr in attributes" ng-click="$parent.select(obj)">
                {{obj[attr]}}
            </td>
        </tr>
    </tbody>
</table>