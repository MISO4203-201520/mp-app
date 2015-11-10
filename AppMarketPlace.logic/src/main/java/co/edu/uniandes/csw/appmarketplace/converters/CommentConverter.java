package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.entities.Comment;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class CommentConverter {

    private CommentConverter() {
    }

    public static CommentDTO refEntity2DTO(Comment entity) {
        if (entity != null) {
            CommentDTO dto = new CommentDTO();
            dto.setId(entity.getId());
            dto.setComment(entity.getComment());
            dto.setClient(ClientConverter.fullEntity2DTO(entity.getClient()));
            dto.setApp(AppConverter.fullEntity2DTO(entity.getApp()));
            dto.setDate(entity.getDate());
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
    public static Comment refDTO2Entity(CommentDTO dto) {
        if (dto != null) {
            Comment entity = new Comment();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }

    public static List<CommentDTO> childListDTO2Entity(List<Comment> entities) {
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        if (entities != null) {
            for (Comment entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }

        return dtos;
    }

    public static List<Comment> childListEntity2DTO(List<CommentDTO> dtos) throws ParseException {
        List<Comment> entities = new ArrayList<Comment>();

        if (dtos != null) {
            for (CommentDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));

            }
        }
        return entities;

    }

    private static CommentDTO basicEntity2DTO(Comment entity) {

        if (entity != null) {
            CommentDTO dto = new CommentDTO();
            dto.setId(entity.getId());
            dto.setComment(entity.getComment());
            dto.setClient(ClientConverter.refEntity2DTO(entity.getClient()));
            dto.setApp(AppConverter.refEntity2DTO(entity.getApp()));
            dto.setDate(entity.getDate());

            return dto;
        } else {
            return null;
        }
    }

    public static Comment basicDTO2Entity(CommentDTO dto) throws ParseException {
        if (dto != null) {
            Comment entity = new Comment();
            entity.setId(dto.getId());
            entity.setComment(dto.getComment());
            entity.setDate(dto.getDate());
            entity.setClient(ClientConverter.refDTO2Entity(dto.getClient()));
            entity.setApp(AppConverter.refDTO2Entity(dto.getApp()));
            return entity;
        } else {
            return null;
        }
    }

    public static List<CommentDTO> listEntity2DTO(List<Comment> entities) {
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        if (entities != null) {
            for (Comment entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
}
