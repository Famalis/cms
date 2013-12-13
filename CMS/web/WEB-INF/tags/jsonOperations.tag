<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="SaveEditDelete" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
        
    <span ng-show="status != null"><img src="resources/loader.gif" alt="brak obrazka"/> Ładowanie danych</span>
        
    <span ng-show="status == 'Błąd'">Błąd podczas ładowania danych</span>
       


<input type="button" ng-show="editMode" ng-click="cancel()" value="Anuluj">

<input type="button" ng-show="!selected && !editMode" ng-click="create()" value="Dodaj">
<input type="button" ng-show="selected && !editMode" ng-click="edit()" value="Edytuj">
<input type="button" ng-show="editMode" ng-click="save()" value="Zapisz">

<input type="button" ng-show="selected.id != undefined && editMode" ng-click="delete()" value="Usuń">

<input type="button" ng-show="displayPage" onclick="location.href ='{{displayPageName}}/{{selected.id}}.htm'" value="Wyświetl">

