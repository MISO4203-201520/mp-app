/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IPaymentCardLogic;
import co.edu.uniandes.csw.appmarketplace.converters.PaymentCardConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentCardDTO;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentCardEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.PaymentCardPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author ac.rojas13
 */
public class PaymentCardLogic implements IPaymentCardLogic{

    @Inject
    private PaymentCardPersistence persistence;
    
    /**
     * @generated
     */
    @Override
    public List<PaymentCardDTO> getPaymentCards(Integer id,Integer page, Integer maxRecords) {
        return PaymentCardConverter.listEntity2DTO(persistence.findAll(id, page, maxRecords));
    }
    
    /**
     * @generated
     */
    @Override
    public PaymentCardDTO getPaymentCards(Long id) {
        return PaymentCardConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @return 
     * @generated
     */
    @Override
    @Transactional
    public PaymentCardDTO createPaymentCards(PaymentCardDTO dto) {
        PaymentCardEntity entity = PaymentCardConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return PaymentCardConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    @Transactional
    public PaymentCardDTO updatePaymentCards(PaymentCardDTO dto) {
        PaymentCardEntity entity = persistence.update(PaymentCardConverter.fullDTO2Entity(dto));
        return PaymentCardConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Transactional
    @Override
    public void deletePaymentCards(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<PaymentCardDTO> findByName(String name) {
        return PaymentCardConverter.listEntity2DTO(persistence.findByName(name));
    }
    
}
