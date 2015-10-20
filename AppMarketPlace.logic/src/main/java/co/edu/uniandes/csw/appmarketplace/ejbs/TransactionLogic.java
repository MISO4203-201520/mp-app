/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.ITransactionLogic;
import co.edu.uniandes.csw.appmarketplace.converters.TransactionConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.TransactionDTO;
import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.TransactionPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author ac.rojas13
 */
public class TransactionLogic implements ITransactionLogic {

    @Inject
    private TransactionPersistence persistence;

    /**
     * @generated
     */
    @Override
    public List<TransactionDTO> getTransactions(Integer page, Integer maxRecords) {
        return TransactionConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    @Override
    public TransactionDTO getTransaction(Long id) {
        return TransactionConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    @Transactional
    @Override
    public TransactionDTO createTransaction(TransactionDTO dto) {
        TransactionEntity entity = TransactionConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return TransactionConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Transactional
    @Override
    public TransactionDTO updateTransaction(TransactionDTO dto) {
        TransactionEntity entity = persistence.update(TransactionConverter.fullDTO2Entity(dto));
        return TransactionConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Transactional
    @Override
    public void deleteTransaction(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<TransactionDTO> findByName(String name) {
        return TransactionConverter.listEntity2DTO(persistence.findByName(name));
    }

    @Override
    public List<TransactionDTO> findByClientId(Long id) {
        return TransactionConverter.listEntity2DTO(persistence.getAllTransactionsByClientId(id));
    }
}
