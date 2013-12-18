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
<table class="genericTable">
    <tr class="table-header">

        <th class="numer-header">
            #
        </th>
        <th ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'"
            class = "{{columnClasses[attr]}}">
            <a ng-click="$parent.orderColumn = attr;
                    $parent.reverse = !$parent.reverse">{{$parent.columns[attr]}}</a>
        </th>   

    </tr>
    <tbody>
        <tr ng-class="{selectedTableRow: obj == selected}" ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse" 
            ng-show="indexOnPage($index)">
            <td class="numer">
                {{$index + 1}}
            </td>
            <td ng-repeat="attr in attributes" ng-click="$parent.select(obj)">
                {{obj[attr]}}
            </td>
        </tr>
    </tbody>
    <tbody>
        <tr>
            <td>
                <input ng-show="checkMax()" type="button" ng-click="pageMax = pageMax + 10;
                    pageMin = pageMin + 10" value="Dalej"/>
                <input ng-show="pageMin > 0" type="button" ng-click="pageMax = pageMax - 10;
                    pageMin = pageMin - 10" value="Wstecz"/>
                Pokazywanie wpisów od {{pageMin+1}} {{pageMax+1}}
            </td>
        </tr>
    </tbody>
</table>