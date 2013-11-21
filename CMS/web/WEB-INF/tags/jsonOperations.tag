<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="SaveEditDelete" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<table>
    <tr ng-show="status != null">
        <td>
            <h1><img src="resources/loader.gif" alt="brak obrazka"/> Ładowanie danych</h1>
        </td>
    </tr>
    <tr ng-show="status == 'Błąd'">
        <td>
            Błąd podczas ładowania danych
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" ng-show="editMode" ng-click="cancel()" value="Anuluj"</td>
        </td>
        <td>
            <input type="button" ng-show="!selected && !editMode" ng-click="create()" value="Dodaj"</td>
        </td>
        <td>
            <input type="button" ng-show="selected && !editMode" ng-click="edit()" value="Edytuj"</td>
        </td>
        <td>
            <input type="button" ng-show="editMode" ng-click="save()" value="Zapisz"</td>
        </td>
        <td>
            <input type="button" ng-show="selected.id!=undefined && editMode" ng-click="delete()" value="Usuń"</td>
        </td>
    </tr>
</table>
