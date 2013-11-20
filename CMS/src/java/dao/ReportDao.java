/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.ReportDTO;
import java.util.ArrayList;
import java.util.List;
import model.Report;

/**
 *
 * @author Sergio
 */
public class ReportDao extends GenericDao<Report>{
    
    public ReportDao() {
        super(Report.class);
    }
    
    public List<ReportDTO> getReportDtos() {
        List<String> params = new ArrayList<>();
        params.add("name");
        params.add("description");
        params.add("mimeType");
        List<Report> reports = this.selectFields("", params);
        List<ReportDTO> dtos = new ArrayList<>();
        for (Report r : reports) {
            ReportDTO dto = new ReportDTO();
            dto.setDescription(r.getDescription());
            dto.setName(r.getName());
            dtos.add(dto);
        }
        return dtos;
    }
}
