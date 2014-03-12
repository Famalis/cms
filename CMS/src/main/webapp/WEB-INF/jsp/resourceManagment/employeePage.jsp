    
<div class="top-right">


</div>
<div class="right-addNew">
    <div class="addNew-header">Profil pracownika</div>
    <div class="addNew-line"></div>
    <div class="addNew-inputs">
        <div style="float:left;clear:right;margin-bottom: 30px;margin-right: 30px;" class="zdjecie-uzytkownika">

            <img src="/CMS/PhotoShowServlet?pesel={{selected.pesel}}" alt="Brak zdjęcia"/>

        </div>
        <div style="float:left;clear: right;">
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
        <div style="margin-left: 30px;float: left;clear: right;width:1px;height:220px;background-color:#31a984;"></div>

        <div style="float:left;clear: right;margin-top: 0px;margin-left:30px;color: #218164;font-weight: 700;"></div>
        <div>
            <br/>
            Adres
            <select ng-init="getMainAddress()">
                <option ng-repeat="addr in selected.addresses"
                        ng-click="selectAddress(addr)">
                    {{addr.dictName}}
                </option>
            </select>
            Zatrudnienia
            <select>
                <option ng-click="selectEmployment('a')">
                    ---
                </option>
                <option ng-repeat="empl in selected.employments" value="{{empl.id}}" 
                        ng-click="selectEmployment(empl)">
                    {{empl.employmentTypeName}}
                </option>
            </select>
            <table width="100%">
                <tr>
                    <th>
                        Rodzaj umowy:
                    </th>
                    <th>
                        Od:
                    </th>
                    <th>
                        Do:
                    </th>
                </tr>
                <tr>
                    <td>
                        {{selectedEmployment.employmentTypeName}}
                    </td>
                    <td>
                        {{selectedEmployment.dateFrom}}
                    </td>
                    <td>
                        {{selectedEmployment.dateTo}}
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>