package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.MediaDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppImageEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class AppImageConverter {

    private AppImageConverter() {
    }

    public static MediaDTO refEntity2DTO(AppImageEntity entity) {
        if (entity != null) {
            MediaDTO dto = new MediaDTO();
            dto.setId(entity.getId());
            dto.setUrl(entity.getUrl());
            dto.setMimetype(entity.getMimetype());
            dto.setApp(AppConverter.refEntity2DTO(entity.getApp()));
            return dto;
        }
        return null;
    }

    public static List<MediaDTO> listEntity2DTO(List<AppImageEntity> entities) {
        List<MediaDTO> dtos = new ArrayList<MediaDTO>();
        if (entities != null) {
            for (AppImageEntity entity : entities) {
                dtos.add(refEntity2DTO(entity));
            }
        }
        return dtos;
    }
}
