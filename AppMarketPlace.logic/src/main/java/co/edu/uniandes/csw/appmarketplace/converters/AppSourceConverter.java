package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.SourceDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
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
    
    public static AppSourceEntity basicDTO2Entity(SourceDTO dto) {
        if (dto != null) {
            AppSourceEntity entity = new AppSourceEntity();
            entity.setId(dto.getId());
            entity.setUrl(dto.getUrl());
            entity.setVersion(dto.getVersion());
            entity.setApp(AppConverter.refDTO2Entity(dto.getApp()));
            
            return entity;
        } else {
            return null;
        }
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
    
    public static AppSourceEntity childDTO2Entity(SourceDTO dto, AppEntity parent) {
        AppSourceEntity entity = basicDTO2Entity(dto);
        entity.setApp(parent);
        
        return entity;
    }
    
    public static List<AppSourceEntity> childListDTO2Entity(List<SourceDTO> dtos, AppEntity parent) {
        List<AppSourceEntity> entities = new ArrayList<AppSourceEntity>();
        
        if (dtos != null) {
            for (SourceDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}