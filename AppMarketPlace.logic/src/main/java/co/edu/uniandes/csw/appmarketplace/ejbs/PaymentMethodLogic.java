/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.appmarketplace.converters.PaymentMethodConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentMethodEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.PaymentMethodPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ac.rojas13
 */
@Stateless
public class PaymentMethodLogic implements IPaymentMethodLogic{

    @Inject
    private PaymentMethodPersistence persistence;
    
    /**
     * @generated
     */
    @Override
    public List<PaymentMethodDTO> getPaymentMethods(Integer page, Integer maxRecords) {
        return PaymentMethodConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }
    
    /**
     * @generated
     */
    @Override
    public PaymentMethodDTO getPaymentMethod(Long id) {
        return PaymentMethodConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO dto) {
        PaymentMethodEntity entity = PaymentMethodConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return PaymentMethodConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public PaymentMethodDTO updatePaymentMethod(PaymentMethodDTO dto) {
        PaymentMethodEntity entity = persistence.update(PaymentMethodConverter.fullDTO2Entity(dto));
        return PaymentMethodConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public void deletePaymentMethod(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<PaymentMethodDTO> findByName(String name) {
        return PaymentMethodConverter.listEntity2DTO(persistence.findByName(name));
    }
}
