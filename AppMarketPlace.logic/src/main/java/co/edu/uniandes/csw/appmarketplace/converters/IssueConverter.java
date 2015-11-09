/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.IssueDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
import co.edu.uniandes.csw.appmarketplace.entities.IssueEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca.forero10
 */
public abstract class IssueConverter {

    private IssueConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static IssueDTO refEntity2DTO(IssueEntity entity) {
        if (entity != null) {
            IssueDTO dto = new IssueDTO();
            dto.setId(entity.getId());
            dto.setComment(entity.getComment());
            dto.setResolved(entity.isResolved());
            dto.setType(entity.getType());
            dto.setVotes(entity.getVotes());

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
    public static IssueEntity refDTO2Entity(IssueDTO dto) {
        if (dto != null) {
            IssueEntity entity = new IssueEntity();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static IssueDTO basicEntity2DTO(IssueEntity entity) {
        if (entity != null) {
            IssueDTO dto = refEntity2DTO(entity);
            dto.setApp(AppConverter.refEntity2DTO(entity.getApp()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static IssueEntity basicDTO2Entity(IssueDTO dto) {
        if (dto != null) {
            IssueEntity entity = refDTO2Entity(dto);
            entity.setApp(AppConverter.refDTO2Entity(dto.getApp()));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static IssueDTO fullEntity2DTO(IssueEntity entity) {
        if (entity != null) {
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static IssueEntity fullDTO2Entity(IssueDTO dto) {
        if (dto != null) {
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<IssueDTO> listEntity2DTO(List<IssueEntity> entities) {
        List<IssueDTO> dtos = new ArrayList<IssueDTO>();
        if (entities != null) {
            for (IssueEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<IssueEntity> listDTO2Entity(List<IssueDTO> dtos) {
        List<IssueEntity> entities = new ArrayList<IssueEntity>();
        if (dtos != null) {
            for (IssueDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
