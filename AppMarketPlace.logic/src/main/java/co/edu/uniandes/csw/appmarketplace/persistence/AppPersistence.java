package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.AppImageEntity;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import java.util.Map;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated @modified by d.jmenez13 Implementing logger. Shortening technical
 * debt.
 */
@Stateless
public class AppPersistence extends CrudPersistence<AppEntity> {

    static final Logger logger = LoggerFactory
            .getLogger(AppPersistence.class);

    /**
     * @generated
     */
    public AppPersistence() {
        this.entityClass = AppEntity.class;
    }

    public List<AppEntity> getCheapestApp(String developerName) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("developerName", "%" + developerName + "%");
            return executeListNamedQuery("AppEntity.getCheapest", params);
        } catch (NoResultException e) {
            logger.warn("Application cannot be found by developerName  {} ", developerName, e);
        }
        return null;
    }

    public List<AppEntity> getAppsByCategory(String category) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("category", category);
            return executeListNamedQuery("AppEntity.getAppsByCategory", params);
        } catch (NoResultException e) {
            logger.warn("Application cannot be found by category  {} ", category, e);
        }
        return null;
    }

    public List<AppEntity> getAppsByKeyWords(String keyword) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", "%" + keyword + "%");
            return executeListNamedQuery("AppEntity.getAppsByKeyWords", params);
        } catch (NoResultException e) {
            logger.warn("Application cannot be found by appId  {} ", keyword, e);
        }
        return null;
    }
}
