/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentMethodEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ac.rojas13
 */
public abstract class PaymentMethodConverter {

    private PaymentMethodConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static PaymentMethodDTO refEntity2DTO(PaymentMethodEntity entity) {
        if (entity != null) {
            PaymentMethodDTO dto = new PaymentMethodDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
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
    public static PaymentMethodEntity refDTO2Entity(PaymentMethodDTO dto) {
        if (dto != null) {
            PaymentMethodEntity entity = new PaymentMethodEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static PaymentMethodDTO basicEntity2DTO(PaymentMethodEntity entity) {
        if (entity != null) {
            PaymentMethodDTO dto = new PaymentMethodDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static PaymentMethodEntity basicDTO2Entity(PaymentMethodDTO dto) {
        if (dto != null) {
            PaymentMethodEntity entity = new PaymentMethodEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static PaymentMethodDTO fullEntity2DTO(PaymentMethodEntity entity) {
        if (entity != null) {
            PaymentMethodDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static PaymentMethodEntity fullDTO2Entity(PaymentMethodDTO dto) {
        if (dto != null) {
            PaymentMethodEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<PaymentMethodDTO> listEntity2DTO(List<PaymentMethodEntity> entities) {
        List<PaymentMethodDTO> dtos = new ArrayList<PaymentMethodDTO>();
        if (entities != null) {
            for (PaymentMethodEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<PaymentMethodEntity> listDTO2Entity(List<PaymentMethodDTO> dtos) {
        List<PaymentMethodEntity> entities = new ArrayList<PaymentMethodEntity>();
        if (dtos != null) {
            for (PaymentMethodDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
