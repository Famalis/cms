<%-- 
    Document   : empListReportTemplate
    Created on : 2014-01-08, 10:25:37
    Author     : sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onLoad="window.print(); window.location='/CMS/reportPrint.htm#/reportPrint.htm';">
        <h1 style='text-align: center'>
            Lista osób w wydziale: ${deptName}
        </h1>
        <table border='1px' width='100%'>
            <tr>
                <th>
                    Imię
                </th>
                <th>
                    Nazwisko
                </th>
                <th>
                    PESEL
                </th>
                <th>
                    Stanowisko
                </th>
                <th>
                    Wypłata
                </th>
            </tr>
        <c:forEach var='emp'items="${employees}">
            <tr>
                <td>
                    ${emp.name}
                </td>
                <td>
                    ${emp.surname}
                </td>
                <td>
                    ${emp.PESEL}
                </td>
                <td>
                    ${emp.positionName}
                </td>
                <td>
                    ${emp.salary}
                </td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
