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
                                        <select ng-model="selected.employeeId">
                                        <option ng-repeat="employee in employees"
                                                value="{{employee.id}}" 
                                                ng-selected="selected.employeeId == employee.id">
                                            {{employee.surname}} {{employee.name}}
                                        </option>
                            </select>
                                    </td>
                                    <td>
                                        Klient:
                                        <select ng-model="selected.customerId">
                                            <option ng-repeat="customer in customers" 
                                                    value="{{customer.id}}"
                                                    ng-selected="selected.customerId == customer.id">
                                                {{customer.name}} {{customer.surname}}
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        Opis: <input type="text" ng-model="selected.description"/>
                                    </td>
                                </tr>
                            </table>
                        
                   
                            </div>
            </div>
        </form>
    </jsp:body>
</t:genericTemplate>