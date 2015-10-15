package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated
 * @modified by d.jmenez13  Implementing logger. Shortening technical debt.
 */
@Stateless
public class DeveloperPersistence extends CrudPersistence<DeveloperEntity> {
    static final Logger logger = LoggerFactory
			.getLogger(DeveloperPersistence.class);

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
            logger.warn("Developer cannot be found by userId  {} ", userId);
        }
        return null;
    }
    
    public DeveloperEntity getDeveloperByUsername (String username) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("username", username);
            return this.executeSingleNamedQuery("Developer.getByUsername", params);
        } catch (NoResultException e) {
            logger.warn("Developer cannot be found by username  {} ", username);
        }
        return null;
    }
}
