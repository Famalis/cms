<div class="addNew-header">Profil pracownika</div>
<div class="addNew-line"></div>
<div>
    <table style="position: absolute; margin-top: 100px">
        <tr>
            <td width="30%">
                <div>
                    <img src="/CMS/PhotoShowServlet?pesel={{selected.pesel}}" alt="Brak zdjęcia"/>
                </div>
                <div>
                    <div>
                        Nazwisko i imię: <span style="font-weight: 700;">{{selected.surname}} {{selected.name}}</span>
                    </div>

                    <div style="margin-top: 14px;">
                        PESEL: <span style="font-weight: 700;">{{selected.pesel}}</span>
                    </div>

                    <div style="margin-top: 14px;">
                        Numer telefonu: <span style="font-weight: 700;">{{selected.phone}}</span>
                    </div>

                    <div style="margin-top: 14px;">
                        Stanowisko: <span style="font-weight: 700;">{{selected.positionName}}</span>
                    </div>

                    <div style="margin-top: 14px;">
                        Wydział: <span style="font-weight: 700;">{{selected.departmentName}}</span>
                    </div>
                    <div style="margin-top: 14px;">
                        Kraj: <span style="font-weight: 700;">{{selectedAddress.country}}</span>
                    </div>
                    <div style="margin-top: 14px;margin-bottom: 30px;">
                        Adres: <span style="font-weight: 700;">{{selectedAddress.city}} {{selectedAddress.postalCode}} <br/>
                            {{selectedAddress.streetName}} {{selectedAddress.streetNumber}}/{{selectedAddress.apartmentNumber}}</span>
                    </div>
                </div>
            </td>
            <td width="70%" valign="top">
                <div>
                    <table ng-init="selectedTab = 'address'" width="100%" height="100%">
                        <tr>
                            <th style="width:10%" ng-click="selectedTab = 'address'">
                                Adresy
                            </th>
                            <th style="width:10%" ng-click="selectedTab = 'contracts'">
                                Umowy
                            </th>
                        </tr>
                        <tr>
                            <td colspan="5" ng-show="selectedTab == 'address'">
                                <select ng-init="getMainAddress()">
                                    <option ng-repeat="addr in selected.addresses"
                                            ng-click="selectAddress(addr)">
                                        {{addr.dictName}}
                                    </option>
                                </select>
                                <br/>
                                Kraj: <input type="text" ng-model="selectedAddress.country"/>
                                Miasto: <input type="text" ng-model="selectedAddress.city"/>
                                Ulica: <input type="text" ng-model="selectedAddress.streetName"/>
                                Numer budynku: <input type="text" ng-model="selectedAddress.streetNumber"/>
                                Numer lokalu: <input type="text" ng-model="selectedAddress.apartmentNumber"/>
                                Kod pocztowy: <input type="text" ng-model="selectedAddress.postalCode"/>
                                <input type="button" value="Ustaw jako główny" ng-click="selected.mainAddressId=selectedAddress.id"/>
                            </td>
                            <td colspan="5" ng-show="selectedTab == 'contracts'">
                                <select>
                                    <option ng-click="selectEmployment('a')">
                                        ---
                                    </option>
                                    <option ng-repeat="empl in selected.employments" value="{{empl.id}}" 
                                            ng-click="selectEmployment(empl)">
                                        {{empl.employmentTypeName}}
                                    </option>
                                </select>
                                <br/>
                                Rodzaj umowy: <input type="text" ng-model="selectedEmployment.employmentTypeName"/>
                                Data od: <input type="date" ng-model="selectedEmployment.dateFrom"/>
                                Data do: <input type="date" ng-model="selectedEmployment.dateTo"/>
                            </td>
                        </tr>
                    </table>  
                </div>
            </td>
        </tr>
    </table>
</div>