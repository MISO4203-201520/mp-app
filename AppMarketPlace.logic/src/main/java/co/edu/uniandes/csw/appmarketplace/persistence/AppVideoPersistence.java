package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppVideoEntity;

public class AppVideoPersistence extends CrudPersistence<AppVideoEntity> {

    public AppVideoPersistence() {
        this.entityClass = AppVideoEntity.class;
    }
}
