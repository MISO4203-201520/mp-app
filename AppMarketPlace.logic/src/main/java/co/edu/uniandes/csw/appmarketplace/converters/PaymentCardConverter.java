/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.PaymentCardDTO;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentCardEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ac.rojas13
 */
public abstract class PaymentCardConverter {

    private static DateFormat format =new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * @param entity
     * @return
     * @generated
     */
    public static PaymentCardDTO refEntity2DTO(PaymentCardEntity entity) {
        if (entity != null) {
            PaymentCardDTO dto = new PaymentCardDTO();
            dto.setId(entity.getId());            
            
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
    public static PaymentCardEntity refDTO2Entity(PaymentCardDTO dto) {
        if (dto != null) {
            PaymentCardEntity entity = new PaymentCardEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static PaymentCardDTO basicEntity2DTO(PaymentCardEntity entity) {
        if (entity != null) {
            PaymentCardDTO dto = new PaymentCardDTO();
            dto.setId(entity.getId());
            dto.setCardnumber(entity.getCardnumber());
            dto.setDueDate(entity.getDueDate().toString());
            dto.setFullname(entity.getFullname());
            dto.setPaymentType(PaymentMethodConverter.refEntity2DTO(entity.getPaymentType()));            
            dto.setSecurityCode(entity.getSecurityCode());
            dto.setOwnerId(ClientConverter.refEntity2DTO(entity.getOwnerId()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static PaymentCardEntity basicDTO2Entity(PaymentCardDTO dto) {
        if (dto != null) {
            PaymentCardEntity entity = new PaymentCardEntity();
            entity.setId(dto.getId());
            entity.setCardnumber(dto.getCardnumber());
            try {
                entity.setDueDate(format.parse(dto.getDueDate()));
            } catch (ParseException ex) {
                Logger.getLogger(PaymentCardConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            entity.setFullname(dto.getFullname());
            entity.setId(dto.getId());
            entity.setPaymentType(PaymentMethodConverter.refDTO2Entity(dto.getPaymentType()));
            entity.setSecurityCode(dto.getSecurityCode());
            entity.setOwnerId(ClientConverter.refDTO2Entity(dto.getOwnerId()));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static PaymentCardDTO fullEntity2DTO(PaymentCardEntity entity) {
        if (entity != null) {
            PaymentCardDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static PaymentCardEntity fullDTO2Entity(PaymentCardDTO dto) {
        if (dto != null) {
            PaymentCardEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<PaymentCardDTO> listEntity2DTO(List<PaymentCardEntity> entities) {
        List<PaymentCardDTO> dtos = new ArrayList<PaymentCardDTO>();
        if (entities != null) {
            for (PaymentCardEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<PaymentCardEntity> listDTO2Entity(List<PaymentCardDTO> dtos) {
        List<PaymentCardEntity> entities = new ArrayList<PaymentCardEntity>();
        if (dtos != null) {
            for (PaymentCardDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
