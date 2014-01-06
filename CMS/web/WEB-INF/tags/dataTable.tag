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

</table>
<div class="footer">
    
    <input type="button" class="anuluj-button" ng-show="editMode && checkEditPrivileges()" ng-click="cancel()" value="ANULUJ">

    <input type="button" class="dodaj-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" value="DODAJ">
    <input type="button" class="edytuj-button" ng-show="selected && !editMode && checkEditPrivileges()" ng-click="edit()" value="EDYTUJ">
    <input type="button" class="zapisz-button" ng-show="editMode && checkEditPrivileges()" ng-click="save()" value="ZAPISZ">

    <input type="button" ng-show="selected.id != undefined && editMode && checkEditPrivileges()" ng-click="delete()" value="Usuń">
    <input type="button" class="wyswietl-button" ng-show="displayPage && selected" onclick="location.href ='{{displayPageName}}/{{selected.id}}.htm'" value="WYŚWIETL">

        <div class="pageMax">
        <input ng-show="pageMin > 0" type="button" class="wstecz-button" ng-click="pageMax = pageMax - 10;
                pageMin = pageMin - 10" value="WSTECZ"/>
        <input ng-show="checkMax()" type="button" class="dalej-button" ng-click="pageMax = pageMax + 10;
                pageMin = pageMin + 10" value="DALEJ"/>
        
        </div> 
        <div class="pageMax-tekst">
        wyświetlane wpisy<br>
        <span style="font-weight:700;float: right;">{{pageMin + 1}}-{{pageMax + 1}}</span>
        </div>
    
</div>