package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class DeveloperPersistence extends CrudPersistence<DeveloperEntity> {

    /**
     * @generated
     */
    public DeveloperPersistence() {
        this.entityClass = DeveloperEntity.class;
    }
    
    public DeveloperEntity getDeveloperByUserId(String userId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", userId);
            return this.executeSingleNamedQuery("Developer.getByUserId", params);
        } catch (NoResultException e) {
            return null;
        }
    }
}
