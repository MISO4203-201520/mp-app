/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ac.rojas13
 */
public class TransactionPersistence extends CrudPersistence<TransactionEntity> {

    public TransactionPersistence() {
        this.entityClass = TransactionEntity.class;
    }

    public List<TransactionEntity> findByPayer(Long clientId, Long appId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("payer", clientId);
        params.put("app", appId);
        return this.executeListNamedQuery("TransactionEntity.findByClient", params);
    }

}
