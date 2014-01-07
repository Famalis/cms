<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/systemConfigCtrl.js"></script>
        <div ng-controller="SystemConfigCtrl">
            <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
         
                        <t:dataTable/>
                   
                        <t:jsonOperations/>
                
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
