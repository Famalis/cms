<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeePageCtrl.js"></script>
        <div ng-controller="EmployeePageCtrl">
            <div class="top-right">


            </div>
            <div class="right-addNew" ng-init="empId = '${employee.id}'">
                <div class="addNew-header">Profil pracownika</div>
                <div class="addNew-line"></div>
                <div class="addNew-inputs">
                    <div style="float:left;clear:right;margin-bottom: 30px;margin-right: 30px;" class="zdjecie-uzytkownika">

                        <img src="/CMS/PhotoShowServlet?pesel={{employee.pesel}}" alt="Brak zdjęcia"/>

                    </div>
                    <div style="float:left;clear: right;">
                        <div>
                            Nazwisko i imię: <span style="font-weight: 700;">{{employee.surname}} {{employee.name}}</span>
                        </div>

                        <div style="margin-top: 14px;">
                            PESEL: <span style="font-weight: 700;">{{employee.pesel}}</span>
                        </div>

                        <div style="margin-top: 14px;">
                            Numer telefonu: <span style="font-weight: 700;">{{employee.phone}}</span>
                        </div>

                        <div style="margin-top: 14px;">
                            Stanowisko: <span style="font-weight: 700;">{{employee.positionName}}</span>
                        </div>

                        <div style="margin-top: 14px;">
                            Wydział: <span style="font-weight: 700;">{{employee.departmentName}}</span>
                        </div>
                        <div style="margin-top: 14px;">
                            Kraj: <span style="font-weight: 700;">{{employee.country}}</span>
                        </div>
                        <div style="margin-top: 14px;margin-bottom: 30px;">
                            Adres: <span style="font-weight: 700;">{{employee.city}} {{employee.postalCode}} <br/>
                                {{employee.streetName}} {{employee.streetNumber}}/{{employee.apartmentNumber}}</span>
                        </div>
                    </div>
                    <div style="margin-left: 30px;float: left;clear: right;width:1px;height:220px;background-color:#31a984;"></div>

                    <div style="float:left;clear: right;margin-top: 0px;margin-left:30px;color: #218164;font-weight: 700;"></div>
                    <div>
                        Zatrudnienia
                        <select>
                            <option ng-repeat="empl in employments" value="{{empl.id}}" 
                                    ng-click="selectedEmployment = empl">
                                {{empl.employmentTypeName}}
                            </option>
                        </select>
                        {{selectedEmployment.employmentTypeName}}
                        <table>
                            <tr>
                                <th>
                                    Rodzaj umowy:
                                </th>
                                <th>
                                    Od:
                                </th>
                                <th>
                                    Do:
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    {{selectedEmployment.employmentTypeName}}
                                </td>
                                <td>
                                    {{selectedEmployment.dateFrom}}
                                </td>
                                <td>
                                    {{selectedEmployment.dateTo}}
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericTemplate>