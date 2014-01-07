<%-- 
    Document   : contractList
    Created on : 2013-12-04, 17:50:48
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/contractListCtrl.js"></script>
        <!--<form name="myForm">-->
            <div ng-controller="ContractListCtrl">
                <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
                <div class="right-addNew" id="addNew" ng-show="editMode">
                    <div class="addNew-header">Dodaj lub edytuj dane umowy</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">

                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select name="pracownik" required="required" ng-model="selected.employeeId">
                                    <option ng-repeat="employee in employees"
                                            value="{{employee.id}}" 
                                            ng-selected="selected.employeeId == employee.id">
                                        {{employee.surname}} {{employee.name}}
                                    </option>
                                </select>
                            </div>

                        </div>
                        
                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select name="klient" required="required" ng-model="selected.customerId">
                                    <option ng-repeat="customer in customers" 
                                            value="{{customer.id}}"
                                            ng-selected="selected.customerId == customer.id">
                                        {{customer.name}} {{customer.surname}}
                                    </option>
                                </select>
                            </div>

                        </div>

                        <div class="addNew-input">
                            <input class="opis-field" placeholder="Opis..." name="opis" required="required" type="text" ng-model="selected.description"/>

                        </div>

                        

                        

                        <div class="addNew-input">  
                            <input class="cena-field" placeholder="Cena..." name="cena" required="required" ng-pattern="/^[0-9]+$/" type="text" ng-model="selected.price"/>
                        </div>







                        <!--<div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select> 
                                    <option selected="selected">Stanowisko...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div> -->

                        <!--<div class="addNew-input">
                            <div class="wydzial-select"> 
                                <select> 
                                    <option selected="selected">Wydział...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div>-->


                    </div>
                    <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
                </div>

                <t:dataTable/>


                <!--<div ng-show="editMode">

                    <table class="genericTable">
                        <tr>
                            <td>
                                Pracownik:
                                <select name="pracownik" required="required" ng-model="selected.employeeId">
                                    <option ng-repeat="employee in employees"
                                            value="{{employee.id}}" 
                                            ng-selected="selected.employeeId == employee.id">
                                        {{employee.surname}} {{employee.name}}
                                    </option>
                                </select>
                                <br />
                                <span class="error" ng-show="myForm.pracownik.$error.required">Podaj pracownika!</span>
                            </td>
                            <td>
                                Klient:
                                <select name="klient" required="required" ng-model="selected.customerId">
                                    <option ng-repeat="customer in customers" 
                                            value="{{customer.id}}"
                                            ng-selected="selected.customerId == customer.id">
                                        {{customer.name}} {{customer.surname}}
                                    </option>
                                </select>
                                <br />
                                <span class="error" ng-show="myForm.klient.$error.required">Podaj klienta!</span>
                            </td>
                            <td>
                                Opis: <input name="opis" required="required" type="text" ng-model="selected.description"/>
                                <br />
                                <span class="error" ng-show="myForm.opis.$error.required">Dodaj opis!</span>
                            </td>
                            <td>
                                Cena: <input name="cena" required="required" ng-pattern="/^[0-9]+$/" type="text" ng-model="selected.price"/>zł
                                <br />
                                <span class="error" ng-show="myForm.cena.$error.required">Dodaj cenę!</span>
                                <span class="error" ng-show="myForm.cena.$error.pattern">Proszę wprowadzić tyko cyfry</span>
                            </td>
                        </tr>
                    </table>


                </div>
            </div>
        </form>-->
    </jsp:body>
</t:genericTemplate>