<%-- 
    Document   : testReportTemplate
    Created on : 2014-01-08, 08:11:03
    Author     : sergio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onLoad="window.print(); window.location='/CMS/reportPrint.htm#/reportPrint.htm';">
        <h1>Tekst w polu 1: ${msg1}</h1>
        <h1>Tekst w polu 2: ${msg2}</h1>
        <h1>Tekst w polu 3: ${msg3}</h1>
    </body>
</html>
