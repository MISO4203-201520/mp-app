/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.PaymentMethodEntity;

/**
 *
 * @author ac.rojas13
 */
public class PaymentMethodPersistence extends CrudPersistence<PaymentMethodEntity> {

    public PaymentMethodPersistence() {
        this.entityClass = PaymentMethodEntity.class;

    }

}
