package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class DeveloperConverter {

    /**
     * @generated
     */
    private DeveloperConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static DeveloperDTO refEntity2DTO(DeveloperEntity entity) {
        if (entity != null) {
            DeveloperDTO dto = new DeveloperDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());
            
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setEmail(entity.getEmail());
            dto.setPhoto(entity.getPhoto());
            dto.setBannerProfile(entity.getBannerProfile());
            dto.setCommentProfile(entity.getCommentProfile());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @param dto
     * @return
     * @generated
     */
    public static DeveloperEntity refDTO2Entity(DeveloperDTO dto) {
        if (dto != null) {
            DeveloperEntity entity = new DeveloperEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static DeveloperDTO basicEntity2DTO(DeveloperEntity entity) {
        if (entity != null) {
            DeveloperDTO dto = refEntity2DTO(entity);

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static DeveloperEntity basicDTO2Entity(DeveloperDTO dto) {
        if (dto != null) {
            DeveloperEntity entity = new DeveloperEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setUserId(dto.getUserId());
            
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setEmail(dto.getEmail());
            entity.setPhoto(dto.getPhoto());
            entity.setBannerProfile(dto.getBannerProfile());
            entity.setCommentProfile(dto.getCommentProfile());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static DeveloperDTO fullEntity2DTO(DeveloperEntity entity) {
        if (entity != null) {
            DeveloperDTO dto = basicEntity2DTO(entity);
            dto.setApps(AppConverter.listEntity2DTO(entity.getApps()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static DeveloperEntity fullDTO2Entity(DeveloperDTO dto) {
        if (dto != null) {
            DeveloperEntity entity = basicDTO2Entity(dto);
            entity.setApps(AppConverter.childListDTO2Entity(dto.getApps(), entity));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<DeveloperDTO> listEntity2DTO(List<DeveloperEntity> entities) {
        List<DeveloperDTO> dtos = new ArrayList<DeveloperDTO>();
        if (entities != null) {
            for (DeveloperEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<DeveloperEntity> listDTO2Entity(List<DeveloperDTO> dtos) {
        List<DeveloperEntity> entities = new ArrayList<DeveloperEntity>();
        if (dtos != null) {
            for (DeveloperDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
