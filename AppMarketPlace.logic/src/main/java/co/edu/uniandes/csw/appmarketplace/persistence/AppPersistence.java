package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class AppPersistence extends CrudPersistence<AppEntity> {

    /**
     * @generated
     */
    public AppPersistence() {
        this.entityClass = AppEntity.class;
    }
}
