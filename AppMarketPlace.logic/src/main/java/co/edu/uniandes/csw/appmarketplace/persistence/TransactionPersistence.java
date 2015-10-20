/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ac.rojas13
 * @modified by d.jmenez13  Implementing logger. Shortening technical debt.
 */
public class TransactionPersistence extends CrudPersistence<TransactionEntity> {
    static final Logger logger = LoggerFactory
			.getLogger(TransactionPersistence.class);

    public TransactionPersistence() {
        this.entityClass = TransactionEntity.class;
    }

    public Long countByAppClient(Long clientId, Long appId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("payer_id", clientId);
            params.put("app_id", appId);
            return this.executeSingleNamedQuery("TransactionEntity.countByClientApp", params);
        } catch (NoResultException e) {
            logger.warn("Transaction cannot be found by clientId  {} and appId {}", clientId, appId, e);
        }
        return 0L;
    }
    public Long countByApp(Long appId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("app_id", appId);
            return this.executeSingleNamedQuery("TransactionEntity.countByApp", params);
        } catch (NoResultException e) {
            logger.warn("Transaction cannot be found by appId {}", appId, e);
        }
        return 0L;
    }
    
    public List<TransactionEntity> findByPayer(Long id){
        Query q = em.createQuery("select u from " + TransactionEntity.class.getSimpleName() + " u where u.payer = :payer");        
        ClientEntity clientEntity = em.find(ClientEntity.class, id);        
        q.setParameter("payer", clientEntity);
        List<TransactionEntity> list = q.getResultList();
        return q.getResultList();
    }
}
