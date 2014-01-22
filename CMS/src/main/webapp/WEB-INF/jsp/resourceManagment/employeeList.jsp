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
            <div>
                <table>
                    <tr>
                        <th ng-click="showEmpContracts = !showEmpContracts">
                            Umowy
                        </th>
                    </tr>
                    <tr>
                        <td ng-show="showEmpContracts">
                            <table>
                                <tr>
                                    <td>
                                        <select>
                                            <option ng-repeat="empl in selected.employments" value="{{empl.id}}">
                                                {{empl.employmentTypeName}}
                                            </option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </jsp:body>
    </t:genericTemplate>