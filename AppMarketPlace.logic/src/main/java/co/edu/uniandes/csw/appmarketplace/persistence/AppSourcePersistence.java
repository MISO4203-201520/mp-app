package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppSourceEntity;

/**
 *
 * @author djimenez
 */
public class AppSourcePersistence extends CrudPersistence<AppSourceEntity> {
    
    public AppSourcePersistence() {
        this.entityClass = AppSourceEntity.class;
    }
}