<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <!--<form name="myForm">-->
            <script src="/CMS/resources/js/resourceManagment/positionListCtrl.js"></script>
            <div ng-controller="PositionListCtrl">
                <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
                <div class="right-addNew" id="addNew" ng-show="editMode">
                    <div class="addNew-header">Dodaj lub edytuj stanowisko</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">

                        <div class="addNew-input">
                            <input class="imie-field" placeholder="Nazwa..." name="nazwa" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" type="text" ng-model="selected.name"/>
                        </div>

                        <div class="addNew-input">
                            <input class="opis-field" placeholder="Opis..." name="opis" required="required" type="text" ng-model="selected.description"/>
                        </div>
              

                        <div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select ng-model="selected.hierarhy">
                                        <option value="1">Pracownik</option>
                                        <option value="2">Menadżer</option>
                                        <option value="3">Prezes</option>
                                    </select>

                            </div>
                        </div>



                    </div>
                    <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
                </div>

                <t:dataTable/>

                <!--

                <div ng-show="editMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwa: <input name="nazwa" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" type="text" ng-model="selected.name"/>
                                    <br />
                                    <span class="error" ng-show="myForm.nazwa.$error.required">Nazwa potrzebna!</span>
                                    <span class="error" ng-show="myForm.nazwa.$error.pattern">Użyte niepoprawne znaki</span>
                                </td>
                                <td>
                                    Opis: <input name="opis" required="required" type="text" ng-model="selected.description"/>
                                    <br />
                                    <span class="error" ng-show="myForm.opis.$error.required">Dodaj opis!</span>
                                </td>
                                <td>
                                    <select ng-model="selected.hierarhy">
                                        <option value="1">Pracownik</option>
                                        <option value="2">Menadżer</option>
                                        <option value="3">Prezes</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                </div>
            </div>
        </form>-->
    </jsp:body>
</t:genericTemplate>