/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.SystemFileDTO;
import java.util.ArrayList;
import java.util.List;
import model.SystemFile;

/**
 *
 * @author Sergio
 */
public class SystemFileDao extends GenericDao<SystemFile>{
    
    public SystemFileDao() {
        super(SystemFile.class);
    }
    
    public List<SystemFileDTO> getReportDtos() {
        List<String> params = new ArrayList<>();
        params.add("name");
        params.add("description");
        params.add("mimeType");
        List<SystemFile> reports = this.selectFields("", params);
        List<SystemFileDTO> dtos = new ArrayList<>();
        for (SystemFile r : reports) {
            SystemFileDTO dto = new SystemFileDTO();
            dto.setId(r.getId());
            dto.setDescription(r.getDescription());
            dto.setName(r.getName());
            dto.setMimeType(r.getMimeType());
            dtos.add(dto);
        }
        return dtos;
    }
}
