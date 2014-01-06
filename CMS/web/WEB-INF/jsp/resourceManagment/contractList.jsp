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
        <form name="myForm">
            <div ng-controller="ContractListCtrl">  
                
                     <t:dataTable/>
 
                     <t:jsonOperations/>

                            <div ng-show="editMode">
                      
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
        </form>
    </jsp:body>
</t:genericTemplate>