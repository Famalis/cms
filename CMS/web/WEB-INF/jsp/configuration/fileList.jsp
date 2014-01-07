<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/filetListCtrl.js"></script>
        <div ng-init="configFileListDownload" ng-controller="FileListCtrl">
            <div class="top-right">
                    <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                    <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

                </div>
          
                        <t:dataTable/>
                <tr>
                    <th style="text-align :left; " ng-click="uploadFile = !uploadFile"/>
                        Dodawanie pliku (klik)
                    </th>
                </tr>
                <tr ng-show="uploadFile">
                    <td>
                        <table class="genericTable">
                            <tr>
                                <td>
                                    <form action="fileListUpload/upload.htm" method="POST"
                                          enctype="multipart/form-data">
                                        Plik: <input type="file" name="file"/>
                                        Typ pliku: <select name="fileExt">
                                            <option ng-repeat="(key, value) in mimetypes" value="{{value}}">
                                                {{key}}
                                            </option>
                                        </select>
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