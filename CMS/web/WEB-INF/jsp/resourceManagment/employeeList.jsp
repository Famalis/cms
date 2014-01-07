<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeeListCtrl.js"></script>
        <!--<form name="myForm">-->
            <div ng-controller="EmployeeListCtrl">
                <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
                <div class="right-addNew" id="addNew" ng-show="editMode">
                    <div class="addNew-header">Dodaj lub edytuj dane pracownika</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">

                        <div class="addNew-input">
                            <input class="imie-field" placeholder="Imię..." name="imie" type="text" maxlength="21" required="required" ng-pattern="/^[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" ng-model="selected.name"/>

                        </div>

                        <div class="addNew-input">
                            <input class="nazwisko-field" placeholder="Nazwisko..." name="nazwisko" type="text" required="required" ng-pattern="/^[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+(-[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+){0,1}$/" ng-model="selected.surname"/>   
                        </div>

                        <div class="addNew-input">
                            <input class="pesel-field" placeholder="Numer PESEL..." name="pesel" type="text" ng-model="selected.pesel" ng-pattern="/^[0-9]+$/" required="required"  ng-minlength="11" ng-maxlength="11" />
                        </div>

                        <div class="addNew-input">
                            <input class="telefon-field" placeholder="Telefon..." name="tel" type="text" ng-pattern="/^[0-9]+$/" maxlength="9" required="required" ng-model="selected.phone"/>
                        </div>

                        <div class="addNew-input">
                            <input class="wynagrodzenie-field" placeholder="Wynagrodzenie..." name="wyplata" type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="selected.salary"/>
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
                            <input class="kod-field" placeholder="Kod pocztowy..." maxlength="6" name="kod" ng-pattern="/^[0-9]{2}-[0-9]{3}$/" required="required"  type="text" ng-model="selected.postalCode"/>
                        </div>

                        <div class="addNew-input">  
                            <input class="kraj-field" placeholder="Kraj..." name="kraj" type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.country"/>
                        </div>







                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select name="stanowisko" ng-model="selected.positionId" required="required" ng-click='selected.positionName = getPositionName(selected.positionId)'> 
                                    <option value="-1">Stanowisko...</option>
                                    <option ng-repeat="position in positions" ng-selected="selected.positionId == position.id" value="{{position.id}}">
                                        {{position.name}}
                                    </option>
                                </select>

                            </div>
                        </div>

                        <div class="addNew-input">
                            <div class="wydzial-select"> 
                                <select name="wydzial" ng-model="selected.departmentId" required="required" ng-click='selected.departmentName = getDepartmentName(selected.departmentId)'> 
                                    <option value="-1">Wydział...</option>
                                    <option ng-repeat="department in departments" ng-selected="selected.departmentId == department.id" value="{{department.id}}">
                                        {{department.name}}
                                    </option>
                                </select>
                            </div>
                        </div>


                    </div>
                    <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
                </div>

                <t:dataTable/>

                

                <!--<div ng-show="editMode">

                    <table class="genericTable">
                        <tr>
                            <td>
                                Nazwisko: <input name="nazwisko" type="text" required="required" ng-pattern="/^[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+(-[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+){0,1}$/" ng-model="selected.surname"/>
                                <br />
                                <span class="error" ng-show="myForm.nazwisko.$error.required">Nazwisko potrzebne!</span>
                                <span class="error" ng-show="myForm.nazwisko.$error.pattern">Proszę prowadzić tylko litery</span>
                            </td>
                            <td>
                                Imię: <input name="imie" type="text" required="required" ng-pattern="/^[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" ng-model="selected.name"/>
                                <br />
                                <span class="error" ng-show="myForm.imie.$error.required">Imię potrzebne!</span>
                                <span class="error" ng-show="myForm.imie.$error.pattern">Proszę prowadzić tylko litery</span>
                            </td>
                            <td>
                                PESEL: <input name="pesel" type="text" ng-model="selected.pesel" ng-pattern="/^[0-9]+$/" required="required"  ng-minlength="11" ng-maxlength="11" />
                                <br/>
                                <span class="error" ng-show="myForm.pesel.$error.required">Pesel potrzebny!</span>
                                <span class="error" ng-show="myForm.pesel.$error.pattern"> Proszę wprowadzić tyko cyfry</span>
                                <span class="error" ng-show="myForm.pesel.$error.minlength"> Za mało cyfr</span>
                                <span class="error" ng-show="myForm.pesel.$error.maxlength"> Za dużo cyfr</span>
                            </td>    
                            <td>
                                Telefon: <input name="tel" type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="selected.phone"/>
                                <br />
                                <span class="error" ng-show="myForm.tel.$error.required">Telefon potrzebny!</span>
                                <span class="error" ng-show="myForm.tel.$error.pattern"> Proszę wprowadzić tyko cyfry</span>
                            </td>
                            <td>
                                Wypłata: <input name="wyplata" type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="selected.salary"/>zł
                                <br />
                                <span class="error" ng-show="myForm.wyplata.$error.required">Wypłata potrzebna!</span>
                                <span class="error" ng-show="myForm.wyplata.$error.pattern">Proszę wprowadzić tyko cyfry</span>
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
                                Nazwa ulicy: <input name="ulica" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" type="text" required="required" ng-model="selected.streetName"/>
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
                            <td>
                                Kod pocztowy: <input name="kod" ng-pattern="/^[0-9]{2}-[0-9]{3}$/" required="required"  type="text" ng-model="selected.postalCode"/>
                                <br />
                                <span class="error" ng-show="myForm.kod.$error.required">Dodaj kod pocztowy!</span>
                                <span class="error" ng-show="myForm.kod.$error.pattern">Zły format</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Stanowisko:
                                <select name="stanowisko" ng-model="selected.positionId" required="required" ng-click='selected.positionName = getPositionName(selected.positionId)'>
                                    <option ng-repeat="position in positions" ng-selected="selected.positionId == position.id" value="{{position.id}}">
                                        {{position.name}}
                                    </option>
                                </select>
                                <br />
                                <span class="error" ng-show="myForm.stanowisko.$error.required">Stanowisko potrzebne!</span>
                            </td>
                            <td>
                                Wydział:
                                <select name="wydzial" ng-model="selected.departmentId" required="required" ng-click='selected.departmentName = getDepartmentName(selected.departmentId)'>
                                    <option ng-repeat="department in departments" ng-selected="selected.departmentId == department.id" value="{{department.id}}">
                                        {{department.name}}
                                    </option>
                                </select>
                                <br />
                                <span class="error" ng-show="myForm.wydzial.$error.required">Wydział potrzebny!</span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>-->
    </jsp:body>
</t:genericTemplate>