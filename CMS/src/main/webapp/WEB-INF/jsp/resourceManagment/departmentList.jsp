<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <!--<form name="myForm">-->
            <script src="/CMS/resources/js/resourceManagment/departmentListCtrl.js"></script>
            <div ng-controller="DepartmentListCtrl">
                <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>

                <div class="right-addNew" id="addNew" ng-show="editMode">
                    <div class="addNew-header">Dodaj lub edytuj dane wydziału</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">

                        <div class="addNew-input">
                            <input class="imie-field" placeholder="Nazwa..." name="nazwa" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" type="text" ng-model="selected.name"/>

                        </div>

                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select name="prowadzacy" required="required" ng-model="selected.managerId">
                                    <option value="-1">Wybierz zarządce...</option>
                                    <option ng-repeat="employee in employees"
                                            value="{{employee.id}}" 
                                            ng-selected="selected.managerId == employee.id">
                                        {{employee.surname}} {{employee.name}}
                                    </option>
                                </select>
                            </div>

                        </div>
                        <div class="addNew-input">
                            <input class="ulica-field" placeholder="Nazwa ulicy..." name="ulica" type="text" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.streetName"/>
                        </div>

                        <div class="addNew-input">
                            <input class="mieszkanie-field" placeholder="Numer mieszkania..." name="mieszkanie" type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="selected.apartmentNumber"/>    
                        </div>

                        <div class="addNew-input">
                            <input class="budynek-field" placeholder="Numer budynku..." name="budynek" type="text" ng-pattern="/^[0-9]+[a-z]{0,1}$/" required="required" ng-model="selected.streetNumber"/>
                        </div>

                        <div class="addNew-input">
                            <input class="miejscowosc-field" placeholder="Miejscowość.." name="miasto" type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.city"/>
                        </div>
                        <div class="addNew-input">  
                            <input class="kraj-field" placeholder="Kraj..." name="kraj" type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.country"/>
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
                                Nazwa: <input name="nazwa" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" type="text" ng-model="selected.name"/>
                                <br />
                                <span class="error" ng-show="myForm.nazwa.$error.required">Nazwa potrzebna!</span>
                                <span class="error" ng-show="myForm.nazwa.$error.pattern">Użyte niepoprawne znaki</span>
                            </td>
                            <td>
                                Prowadzący: <select name="prowadzacy" required="required" ng-model="selected.managerId">
                                    <option ng-repeat="employee in employees"
                                            value="{{employee.id}}" 
                                            ng-selected="selected.managerId == employee.id">
                                        {{employee.surname}} {{employee.name}}
                                    </option>
                                </select>
                                <br />
                                <span class="error" ng-show="myForm.prowadzacy.$error.required">Dodaj prowadzącego!</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kraj: <input name="kraj" type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.country"/>
                                <br />
                                <span class="error" ng-show="myForm.kraj.$error.required">Kraj potrzebny!</span>
                                <span class="error" ng-show="myForm.kraj.$error.pattern">Proszę prowadzić tylko litery</span>
                            </td>
                            <td>
                                Miasto: <input name="miasto" type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.city"/>
                                <br />
                                <span class="error" ng-show="myForm.miasto.$error.required">Miasto potrzebne!</span>
                                <span class="error" ng-show="myForm.miasto.$error.pattern">Proszę prowadzić tylko litery</span>
                            </td>
                            <td>
                                Nazwa ulicy: <input name="ulica" type="text" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.streetName"/>
                                <br />
                                <span class="error" ng-show="myForm.ulica.$error.required">Ulica potrzebna!</span>
                                <span class="error" ng-show="myForm.ulica.$error.pattern">Użyte niepoprawne znaki</span>
                            </td>
                            <td>
                                Numer budynku: <input name="budynek" type="text" ng-pattern="/^[0-9]+[a-z]{0,1}$/" required="required" ng-model="selected.streetNumber"/>
                                <br />
                                <span class="error" ng-show="myForm.budynek.$error.required">Numer potrzebny!</span>
                                <span class="error" ng-show="myForm.budynek.$error.pattern">Zły numer budynku</span>
                            </td>
                            <td>
                                Numer mieszkania: <input name="mieszkanie" type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="selected.apartmentNumber"/>
                                <br />
                                <span class="error" ng-show="myForm.mieszkanie.$error.pattern">Proszę prowadzić tylko cyfry</span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>-->
    </jsp:body>
</t:genericTemplate>
