/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.RateEntity;
import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author af.esguerra10
 * @modified by d.jmenez13  Implementing logger. Shortening technical debt.
 */
@Stateless
public class RatePersistence extends CrudPersistence<RateEntity> {
    static final Logger logger = LoggerFactory
			.getLogger(RatePersistence.class);

    public RatePersistence() {
        this.entityClass = RateEntity.class;
    }

    public RateEntity findByAppClient(Long clientId, Long appId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("client_id", clientId);
            params.put("app_id", appId);
            return this.executeSingleNamedQuery("RateEntity.findByAppClient", params);
        } catch (NoResultException e) {
            logger.warn("Rate cannot be found by clientId  {} and appId {}", clientId, appId, e);
        }
        return null;
    }
    
    public Double getAverageByApp(Long appId){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("app_id", appId);
            return this.executeSingleNamedQuery("RateEntity.avgByApp", params);
        } catch (NoResultException e) {
            logger.warn("Rate cannot be found by appId {}", appId, e);
        }
        return 0.0;
    }
}
