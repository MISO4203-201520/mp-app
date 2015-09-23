/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.TransactionDTO;
import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ac.rojas13
 */
public class TransactionConverter {

    /**
     * @param entity
     * @return
     * @generated
     */
    public static TransactionDTO refEntity2DTO(TransactionEntity entity) {
        if (entity != null) {
            TransactionDTO dto = new TransactionDTO();
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
    public static TransactionEntity refDTO2Entity(TransactionDTO dto) {
        if (dto != null) {
            TransactionEntity entity = new TransactionEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static TransactionDTO basicEntity2DTO(TransactionEntity entity) {
        if (entity != null) {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(entity.getId());
            dto.setPayer(ClientConverter.refEntity2DTO(entity.getPayer()));
            dto.setRecipient(AppConverter.refEntity2DTO(entity.getRecipient()));
            dto.setTotal(entity.getTotal());
            dto.setStatus(entity.getStatus());
            dto.setPaymentCard(PaymentCardConverter.refEntity2DTO(entity.getPaymentCard()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static TransactionEntity basicDTO2Entity(TransactionDTO dto) {
        if (dto != null) {
            TransactionEntity entity = new TransactionEntity();
            entity.setId(dto.getId());
            entity.setPayer(ClientConverter.refDTO2Entity(dto.getPayer()));
            entity.setRecipient(AppConverter.refDTO2Entity(dto.getRecipient()));
            entity.setTotal(dto.getTotal());
            entity.setStatus(dto.getStatus());
            entity.setPaymentCard(PaymentCardConverter.refDTO2Entity(dto.getPaymentCard()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static TransactionDTO fullEntity2DTO(TransactionEntity entity) {
        if (entity != null) {
            TransactionDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static TransactionEntity fullDTO2Entity(TransactionDTO dto) {
        if (dto != null) {
            TransactionEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static List<TransactionDTO> listEntity2DTO(List<TransactionEntity> entities) {
        List<TransactionDTO> dtos = new ArrayList<TransactionDTO>();
        if (entities != null) {
            for (TransactionEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<TransactionEntity> listDTO2Entity(List<TransactionDTO> dtos) {
        List<TransactionEntity> entities = new ArrayList<TransactionEntity>();
        if (dtos != null) {
            for (TransactionDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
