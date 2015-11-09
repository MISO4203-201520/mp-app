/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca.forero10
 */
public abstract class AdminConverter {

    private AdminConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static AdminDTO refEntity2DTO(AdminEntity entity) {
        if (entity != null) {
            AdminDTO dto = new AdminDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());

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
    public static AdminEntity refDTO2Entity(AdminDTO dto) {
        if (dto != null) {
            AdminEntity entity = new AdminEntity();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AdminDTO basicEntity2DTO(AdminEntity entity) {
        if (entity != null) {
            AdminDTO dto = new AdminDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AdminEntity basicDTO2Entity(AdminDTO dto) {
        if (dto != null) {
            AdminEntity entity = new AdminEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setUserId(dto.getUserId());
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AdminDTO fullEntity2DTO(AdminEntity entity) {
        if (entity != null) {
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static AdminEntity fullDTO2Entity(AdminDTO dto) {
        if (dto != null) {
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<AdminDTO> listEntity2DTO(List<AdminEntity> entities) {
        List<AdminDTO> dtos = new ArrayList<AdminDTO>();
        if (entities != null) {
            for (AdminEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<AdminEntity> listDTO2Entity(List<AdminDTO> dtos) {
        List<AdminEntity> entities = new ArrayList<AdminEntity>();
        if (dtos != null) {
            for (AdminDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
