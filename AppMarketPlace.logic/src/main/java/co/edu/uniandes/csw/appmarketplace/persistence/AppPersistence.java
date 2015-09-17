package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import java.util.List;
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
    
    public List<AppEntity> getCheapestApp(String name){
        return executeListNamedQuery("AppEntity.getCheapest");
    }
}
