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
    <tr>
        <td>
            <input type="button" ng-show="editMode" ng-click="editMode=false" value="Anuluj"</td>
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
            <input type="button" ng-show="editMode" ng-click="delete()" value="Usuń"</td>
        </td>
    </tr>
</table>