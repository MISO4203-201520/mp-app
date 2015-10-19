package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;

/**
 * @generated
 */
public abstract class AppConverter {

    /**
     * @generated
     */
    private AppConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static AppDTO refEntity2DTO(AppEntity entity) {
        if (entity != null) {
            AppDTO dto = new AppDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setVersion(entity.getVersion());
            dto.setPicture(entity.getPicture());
            dto.setPrice(entity.getPrice());
            dto.setSize(entity.getSize());
            dto.setPlatform(entity.getPlatform());
            dto.setDiscount(entity.getDiscount());
            dto.setCategory(entity.getCategory());
            dto.setStartDiscountDate(entity.getStartDiscountDate());
            dto.setFinishDiscountDate(entity.getFinishDiscountDate());
            dto.setEnabled(entity.isEnabled());

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
    public static AppEntity refDTO2Entity(AppDTO dto) {
        if (dto != null) {
            AppEntity entity = new AppEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static AppDTO basicEntity2DTO(AppEntity entity) {
        if (entity != null) {
            AppDTO dto = new AppDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setVersion(entity.getVersion());
            dto.setPicture(entity.getPicture());
            dto.setPrice(entity.getPrice());
            dto.setSize(entity.getSize());
            dto.setDeveloper(DeveloperConverter.refEntity2DTO(entity.getDeveloper()));
            dto.setPlatform(entity.getPlatform());
            dto.setDiscount(entity.getDiscount());
            dto.setCategory(entity.getCategory());
            dto.setStartDiscountDate(entity.getStartDiscountDate());
            dto.setFinishDiscountDate(entity.getFinishDiscountDate());
            dto.setEnabled(entity.isEnabled());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AppEntity basicDTO2Entity(AppDTO dto) {
        if (dto != null) {
            AppEntity entity = new AppEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setVersion(dto.getVersion());
            entity.setPicture(dto.getPicture());
            entity.setPrice(dto.getPrice());
            entity.setSize(dto.getSize());
            entity.setDeveloper(DeveloperConverter.refDTO2Entity(dto.getDeveloper()));
            entity.setPlatform(dto.getPlatform());
            entity.setDiscount(dto.getDiscount());
            entity.setCategory(dto.getCategory());
            entity.setStartDiscountDate(dto.getStartDiscountDate());
            entity.setFinishDiscountDate(dto.getFinishDiscountDate());
            dto.setEnabled(entity.isEnabled());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AppDTO fullEntity2DTO(AppEntity entity) {
        if (entity != null) {
            AppDTO dto = basicEntity2DTO(entity);
            dto.setComments(CommentConverter.listEntity2DTO(entity.getComments()));
            dto.setImages(AppImageConverter.listEntity2DTO(entity.getImages()));
            dto.setVideos(AppVideoConverter.listEntity2DTO(entity.getVideos()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AppEntity fullDTO2Entity(AppDTO dto) {
        if (dto != null) {
            AppEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<AppDTO> listEntity2DTO(List<AppEntity> entities) {
        List<AppDTO> dtos = new ArrayList<AppDTO>();
        if (entities != null) {
            for (AppEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<AppEntity> listDTO2Entity(List<AppDTO> dtos) {
        List<AppEntity> entities = new ArrayList<AppEntity>();
        if (dtos != null) {
            for (AppDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * @generated
     */
    public static AppEntity childDTO2Entity(AppDTO dto, DeveloperEntity parent) {
        AppEntity entity = basicDTO2Entity(dto);
        entity.setDeveloper(parent);
        return entity;
    }

    /**
     * @generated
     */
    public static List<AppEntity> childListDTO2Entity(List<AppDTO> dtos, DeveloperEntity parent) {
        List<AppEntity> entities = new ArrayList<AppEntity>();
        if (dtos != null) {
            for (AppDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }

}
