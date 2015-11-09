package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.SourceDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppSourceEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author djimenez
 */
public abstract class AppSourceConverter {
    
    private AppSourceConverter() {
    }
    
    public static SourceDTO refEntity2DTO(AppSourceEntity entity) {
        if (entity != null) {
            SourceDTO dto = new SourceDTO();
            dto.setId(entity.getId());
            dto.setUrl(entity.getUrl());
            dto.setVersion(entity.getVersion());
            dto.setApp(AppConverter.refEntity2DTO(entity.getApp()));
            return dto;
        }
        return null;
    }
    
    public static List<SourceDTO> listEntity2DTO(List<AppSourceEntity> entities) {
        List<SourceDTO> dtos = new ArrayList<SourceDTO>();
        if (entities != null) {
            for (AppSourceEntity entity : entities) {
                dtos.add(refEntity2DTO(entity));
            }
        }
        return dtos;
    }
}