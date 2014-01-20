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
public class EmploymentType extends DatabaseObject{

    public String
            dictId;
            
    
    public EmploymentType() {
        super("employment_type");
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }
    
}
