
<div ng-controller="CustomerPageCtrl">
    <div class="top-right">


    </div>
    <div class="right-addNew" id="addNew">
        <div class="addNew-header">Profil pracownika</div>
        <div class="addNew-line"></div>
        <div class="addNew-inputs">

            <div style="float:left;clear: right;">
                <div>
                    Nazwisko i imię: <span style="font-weight: 700;">${customer.surname} ${customer.name}</span>
                </div>

                <div style="margin-top: 14px;">
                    Telefon: <span style="font-weight: 700;">${customer.phone}</span>
                </div>

                <div style="margin-top: 14px;">
                    Email: <span style="font-weight: 700;">${customer.email}</span>
                </div>

                <div style="margin-top: 14px;">
                    Firma: <span style="font-weight: 700;">${customer.companyName}</span>
                </div>

                <div style="margin-top: 14px;">
                    Kraj: <span style="font-weight: 700;">${customer.country}</span>
                </div>
                <div style="margin-top: 14px;margin-bottom: 30px;">
                    Adres: <span style="font-weight: 700;">${customer.city} ${customer.postalCode} <br/>
                        ${customer.streetName} ${customer.streetNumber}/${customer.apartmentNumber}</span>
                </div>

            </div>
            <div style="margin-left: 30px;float: left;clear: right;width:1px;height:220px;background-color:#31a984;"></div>

            <div style="float:left;clear: right;margin-top: 0px;margin-left:30px;color: #218164;font-weight: 700;"></div>





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

        <!-- <table width="100%">
             <tr>
                 <td>
                     <table class="genericTable">
                         <tr>
                             <td>
                                 Nazwisko i imię: ${customer.surname} ${customer.name}
                             </td>
                            
                         </tr>
                         <tr>
                             <td>
                                 Telefon: ${customer.phone}
                             </td>
                         </tr>
                         <tr>
                             <td>
                                 Email: ${customer.email}
                             </td>
                         </tr>
                         <tr>
                             <td>
                                 Firma: ${customer.companyName}
                             </td>
                         </tr>
                         <tr>
                             <td>
                                 Kraj: ${customer.country}
                             </td>
                         </tr>
                         <tr>
                             <td>
                                 Adres: ${customer.city} ${customer.postalCode} <br/>
        ${customer.streetName} ${customer.streetNumber}/${customer.apartmentNumber}
    </td>
</tr>
</table>
</td>
</tr>
<tr>
<td>

</td>
</tr>
</table> -->
    </div>
    <table class="genericTable">
        <tr class="table-header">
            <th>
                Pracownik
            </th>
            <th>
                Data
            </th>
            <th>
                Opis Umowy
            </th>
            <th>
                Cena
            </th>
        </tr>

        <c:forEach items="${contracts}" var="contract">
            <tr>
                <td>
                    ${contract.employeeName} ${contract.employeeSurname}
                </td>
                <td>
                    ${contract.date}
                </td>
                <td>
                    ${contract.description}
                </td>
                <td>
                    ${contract.price}
                </td>
            </tr>
        </c:forEach>
    </table>