<%-- 
    Document   : customerPage
    Created on : 2013-12-04, 19:29:58
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/customerPageCtrl.js"></script>
        <div ng-controller="CustomerPageCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwisko i imię: ${customer.surname} ${customer.name}
                                </td>
                                <td rowspan="4">
                                    PHOTO HERE
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Telefon: ${customer.phone}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Email: ${customer.email}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Firma: ${customer.companyName}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Kraj: ${customer.country}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Adres: ${customer.city} ${customer.postalCode} <br/>
                                    ${customer.streetName} ${customer.streetNumber}/${customer.apartmentNumber}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table class="genericTable">
                                <tr>
                                    <th>
                                        Pracownik
                                    </th>
                                    <th>
                                        Data
                                    </th>
                                    <th>
                                        Opis Umowy
                                    </th>
                                    <th>
                                        Cena
                                    </th>
                                </tr>
                            <c:forEach items="${contracts}" var="contract">
                                <tr>
                                    <td>
                                        ${contract.employeeName} ${contract.employeeSurname}
                                    </td>
                                    <td>
                                        ${contract.date}
                                    </td>
                                    <td>
                                        ${contract.description}
                                    </td>
                                    <td>
                                        ${contract.price}
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </table> 
        </div>
    </jsp:body>
</t:genericTemplate>