<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/privilegeKeyListCtrl.js"></script>
        <div ng-controller="PrivilegeKeyListCtrl">  
            <h1>Nie zmieniać istniejących</h1>
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
                    <table class="genericTable">
                <tr ng-show="editMode">
                    <td>
                        Nazwa: <input type="text" ng-model="selected.code"/>
                    </td>
                    <td>
                        Opis <textarea ng-model="selected.description"> </textarea>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>