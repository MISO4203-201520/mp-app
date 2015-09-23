package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public List<AppEntity> getCheapestApp(String developerName){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("developerName",  "%" + developerName + "%");
        return executeListNamedQuery("AppEntity.getCheapest", params);
    }
}
