/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import model.Report;

/**
 *
 * @author Sergio
 */
public class ReportDTO {
    
    private Long id;
    private String name, description, formCode;
    
    public ReportDTO() {
        
    }
    
    public ReportDTO(Report report) {
        this.id = report.getId();
        this.name = report.getName();
        this.description = report.getDescription();
        this.formCode = report.getFormCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }
    
}
