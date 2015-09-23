/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;

/**
 *
 * @author ac.rojas13
 */
public class TransactionPersistence extends CrudPersistence<TransactionEntity>{

    public TransactionPersistence() {
        this.entityClass = TransactionEntity.class;
    }
    
    
}
