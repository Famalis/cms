/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.general.DatabaseObject;

/**
 *
 * @author sergio
 */
public class Employment extends DatabaseObject{

    public String 
            dateFrom,
            dateTo,
            employmentTypeId,
            employeeId;
    
    public Employment() {
        super("employment");
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(String employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
}
