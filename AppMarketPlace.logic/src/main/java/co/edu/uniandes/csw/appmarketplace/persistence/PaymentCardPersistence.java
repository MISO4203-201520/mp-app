/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentCardEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ac.rojas13
 */
public class PaymentCardPersistence extends CrudPersistence<PaymentCardEntity> {

    public PaymentCardPersistence() {
        this.entityClass = PaymentCardEntity.class;
    }

    public List<PaymentCardEntity> findAll(Integer id, Integer page, Integer maxRecords) {
        Query q = em.createQuery("select u from " + entityClass.getSimpleName() + " u WHERE u.ownerId.id = " + id);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
