<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/fileListCtrl.js"></script>
        <div ng-controller="FileListCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <table class="genericTable">
                            <tr>
                                <th>
                                    Nazwa
                                </th>
                                <th>
                                    Opis
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: file == selected}" ng-repeat="file in files" ng-click="select(file)">
                                <td width="30%">
                                    {{file.name}}
                                </td>
                                <td>
                                    {{file.description}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr ng-show="selected">
                    <td>
                        <form action="fileList/download.htm">
                            <input ng-hide="true" type="text" name="id" value="{{selected.id}}">
                            <input type="submit" value="Pobierz {{selected.name}}"/>
                        </form>
                    </td>
                </tr>
                <tr>                    
                    <td>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>