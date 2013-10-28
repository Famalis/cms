<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/privilegeKeyListCtrl.js"></script>
        <div ng-controller="PrivilegeKeyListCtrl">  
            <table border="1px" width="100%">
                <tr>
                    <td>
                        <table width="100%" border="1px">
                            <tr>
                                <td>
                                    ID
                                </td>
                                <td>
                                    Kod
                                </td>
                            </tr>
                            <tr ng-repeat="privilegeKey in privilegeKeys" ng-click="select(privilegeKey)">
                                <td>
                                    {{privilegeKey.id}}
                                </td>
                                <td>
                                    {{privilegeKey.code}}
                                </td>
                            </tr>
                            <tr ng-show="editMode">
                                <td>
                                    <input type="text" ng-model="selected.code"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <t:jsonOperations>
                            
                        </t:jsonOperations>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>