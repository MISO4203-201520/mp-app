package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import java.util.Map;
import javax.persistence.NoResultException;

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

    public List<AppEntity> getCheapestApp(String name) {
        return executeListNamedQuery("AppEntity.getCheapest");
    }

    public List<AppEntity> getAppsByCategory(String category) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("category", category);
            return executeListNamedQuery("AppEntity.getAppsByCategory", params);
        } catch (NoResultException e) {
            return null;
        }
        
    }
}
