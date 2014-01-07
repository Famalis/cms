<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericTemplate>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/filetListCtrl.js"></script>
        <div ng-init="configFileListDownload = true" ng-controller="FileListCtrl">
            <div class="top-right">
                <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

            </div>
            <div class="right-addNew" id="addNew" ng-show="editMode">
                    <div class="addNew-header">Dodaj nowy plik</div>
                    <div class="addNew-line"></div>
                    <div class="addNew-inputs">

                        <div class="addNew-input">
                            <input class="imie-field" placeholder="Imię..." name="imie" type="text" maxlength="21" required="required" ng-pattern="/^[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" ng-model="selected.name"/>
                          
                        </div>


                        <!--<div class="addNew-input">
                            <div class="stanowisko-select"> 
                                <select> 
                                    <option selected="selected">Stanowisko...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div> -->

                        <!--<div class="addNew-input">
                            <div class="wydzial-select"> 
                                <select> 
                                    <option selected="selected">Wydział...</option>
                                    <option>Firefox</option> 
                                    <option>Webkit</option> 
                                </select> 
                            </div>
                        </div>-->


                    </div>
                    <div style="float:right;padding-right:33px;padding-top: 20px;padding-bottom: 33px;"> <t:jsonOperations/></div>
                </div>
            <table>
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