<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/fileListCtrl.js"></script>
        <div ng-controller="FileListCtrl">
            <div class="top-right">
                    
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
           
                        <t:dataTable/>
                  
                <div ng-show="selected">
                    <td>
                        <form action="fileList/download.htm">
                            <input ng-hide="true" type="text" name="id" value="{{selected.id}}">
                            <input type="submit" value="Pobierz {{selected.name}}"/>
                        </form>
                </div>
        </div>
    </jsp:body>
</t:genericTemplate>