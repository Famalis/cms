<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeePageCtrl.js"></script>
        <div ng-controller="EmployeePageCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwisko i imię: ${employee.surname} ${employee.name}
                                </td>
                                <td rowspan="4">
                                    <img src="/CMS/PhotoShowServlet?pesel=${employee.PESEL}" alt="Brak zdjęcia"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    PESEL: ${employee.PESEL}                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Stanowisko: ${employee.positionName}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Wydział: ${employee.departmentName}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>