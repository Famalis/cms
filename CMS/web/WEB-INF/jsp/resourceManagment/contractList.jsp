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
                <table width="100%">
                    <tr>
                        <td>
                            <t:filterTable/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <t:dataTable/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <t:jsonOperations/>
                        </td>
                    </tr>
                    <tr ng-show="editMode">
                        <td>
                            <table class="genericTable">
                                <tr>
                                    <td>
                                        Pracownik: {{selected.employeeId}}
                                        <select ng-model="selected.employeeId">
                                            <option ng-repeat="employee in employees" value="{{employee.id}}">
                                                {{employee.name}} {{employee.surname}}
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        Klient: {{selected.customerId}}
                                        <select ng-model="selected.customerId">
                                            <option ng-repeat="customer in customers" value="{{customer.id}}">
                                                {{customer.name}} {{customer.surname}}
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        Opis: <input type="text" ng-model="selected.description"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </jsp:body>
</t:genericTemplate>