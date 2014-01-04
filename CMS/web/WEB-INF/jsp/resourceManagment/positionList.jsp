<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/positionListCtrl.js"></script>
        <div ng-controller="PositionListCtrl">  
         
                        <t:dataTable/>
                   
                        <t:jsonOperations/>
                  
                <div ng-show="editMode">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    Nazwa: <input type="text" ng-model="selected.name"/>
                                </td>
                                <td>
                                    Opis: <input type="text" ng-model="selected.description"/>
                                </td>
                                <td>
                                    {{positions.length}}
                                    {{ble}}
                                </td>
                            </tr>
                        </table>
                </div>
        </div>
    </jsp:body>
</t:genericTemplate>