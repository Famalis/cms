<%-- 
    Document   : customerList
    Created on : 2013-12-04, 16:57:20
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/customerListCtrl.js"></script>

        <div ng-controller="CustomerListCtrl">  
            <div class="top-right">
    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

</div>
            <div class="right-addNew" id="panel">
                    <div class="addNew-header">Dodaj nowego pracownika</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">
                        <div class="addNew-input"><input class="imie-field" type="text" placeholder="Imię..."/></div>
                        <div class="addNew-input"><input class="nazwisko-field" type="text" placeholder="Nazwisko..."/></div>
                        <div class="addNew-input"><input class="pesel-field" type="text" placeholder="Numer PESEL..."/></div>
                        <div class="addNew-input"><input class="telefon-field" type="text" placeholder="Telefon..."/></div>
                        <div class="addNew-input"><input class="kraj-field" type="text" placeholder="Kraj..."/></div>
                        <div class="addNew-input"><input class="ulica-field" type="text" placeholder="Nazwa ulicy..."/></div>
                        <div class="addNew-input"><input class="budynek-field" type="text" placeholder="Numer budynku..."/></div>
                        <div class="addNew-input"><input class="mieszkanie-field" type="text" placeholder="Numer mieszkania..."/></div>
                        <div class="addNew-input"><input class="miejscowosc-field" type="text" placeholder="Miejscowość..."/></div>

                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select> 
                                    <option selected="selected">Stanowisko...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div>

                        <div class="addNew-input">
                            <div class="wydzial-select"> 
                                <select> 
                                    <option selected="selected">Wydział...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div>


                    </div>
                </div>
            
            <t:dataTable/>
            
            <t:jsonOperations/>
            <div ng-show="editMode">
                <table class="genericTable">
                    <tr>
                        <td>
                            Imię: <input type="text" ng-model="selected.name"/>
                        </td>
                        <td>
                            Nazwisko: <input type="text" ng-model="selected.surname"/>
                        </td>
                        <td>
                            Telefon: <input type="text" ng-model="selected.phone"/>
                        </td>
                        <td>
                            Email: <input type="text" ng-model="selected.email"/>
                        </td>
                        <td>
                            Firma: <input type="text" ng-model="selected.companyName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kraj: <input type="text" ng-model="selected.country"/>
                        </td>
                        <td>
                            Miasto: <input type="text" ng-model="selected.city"/>
                        </td>
                        <td>
                            Nazwa ulicy: <input type="text" ng-model="selected.streetName"/>
                        </td>
                        <td>
                            Numer budynku: <input type="text" ng-model="selected.streetNumber"/>
                        </td>
                        <td>
                            Numer mieszkania: <input type="text" ng-model="selected.apartmentNumber"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </jsp:body>
</t:genericTemplate>