<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/reportListCtrl.js"></script>
        <div ng-controller="ReportListCtrl">  
            <table width="100%">
                <tr>
                    <td>
                        <t:filterTable/>
                    </td>
                </tr>
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
                                <th>
                                    Rodzaj pliku
                                </th>
                                <th>
                                    Adres formularza
                                </th>
                            </tr>
                            <tr ng-class="{selectedTableRow: report == selected}" ng-repeat="report in reports | filter:searchText" ng-click="select(report)">
                                <td>
                                    {{report.name}}
                                </td>
                                <td>
                                    {{report.description}}
                                </td>
                                <td>
                                    {{report.mimeType}}
                                </td>
                                <td>
                                    {{report.formCode}}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>                
                <tr ng-show="selected != ''">
                    <td>                        
                        <form action="reportList/download.htm">
                            <input ng-show="false" type="text" value="{{selected.id}}" name="id"/>
                            <input type="submit" value="Pobierz {{selected.name}}"/>
                        </form>
                    </td>
                </tr>
                <tr>
                    <th style="text-align :left; background-color: ${user.bgcolor}" ng-click="uploadFile = !uploadFile"/>
                        Dodawanie pliku (klik)
                    </th>
                </tr>
                <tr ng-show="uploadFile">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    <form action="reportList/upload.htm" method="POST"
                                          enctype="multipart/form-data">
                                        Plik: <input type="file" name="file"/>
                                        Typ pliku: <select name="fileExt">
                                            <option ng-repeat="(key, value) in mimetypes" value="{{value}}">
                                                {{key}}
                                            </option>
                                        </select>
                                        <input type="text" name="formCode"/>
                                        <br/>
                                        Opis:
                                        <textarea style="width: 100%; height: 100%" name="description">
                                            
                                        </textarea>        
                                        <br/>
                                        <input type="submit" value="Zapisz plik"/>
                                    </form>                                    
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:genericTemplate>