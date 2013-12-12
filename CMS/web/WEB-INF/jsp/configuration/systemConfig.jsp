<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/systemConfigCtrl.js"></script>
        <div ng-controller="SystemConfigCtrl"> 
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
            </table>
            <table ng-show="editMode" class="genericTable">
                <tr>
                    <td>
                        Nazwa: <input type="text" ng-model="selected.name"/>
                        Wartość: <input type="text" ng-model="selected.value"/>
                        Opis: <textarea ng-model="selected.description">
                            
                        </textarea>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>
