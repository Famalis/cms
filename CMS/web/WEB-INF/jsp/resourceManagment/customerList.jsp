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