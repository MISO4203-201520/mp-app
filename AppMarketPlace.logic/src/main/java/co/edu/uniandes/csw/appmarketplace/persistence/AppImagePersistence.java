/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.AppImageEntity;

/**
 *
 * @author af.esguerra10
 */
public class AppImagePersistence extends CrudPersistence<AppImageEntity> {

    public AppImagePersistence() {
        this.entityClass = AppImageEntity.class;
    }
}
