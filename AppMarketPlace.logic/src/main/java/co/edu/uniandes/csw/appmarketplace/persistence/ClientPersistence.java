package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated @modified by d.jmenez13 Implementing logger. Shortening technical
 * debt.
 */
@Stateless
public class ClientPersistence extends CrudPersistence<ClientEntity> {

    private static final Logger logger = LoggerFactory
            .getLogger(ClientPersistence.class);

    /**
     * @generated
     */
    public ClientPersistence() {
        this.entityClass = ClientEntity.class;
    }

    public ClientEntity getClientByUserId(String userId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", userId);
            return this.executeSingleNamedQuery("Client.getByUserId", params);
        } catch (NoResultException e) {
            logger.warn("Client cannot be found by userId  {} ", userId, e);
        }
        return null;
    }

    public ClientEntity getClientByUsername(String username) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("username", username);
            return this.executeSingleNamedQuery("Client.getByUsername", params);
        } catch (NoResultException e) {
            logger.warn("Client cannot be found by username  {} ", username, e);
        }
        return null;
    }
}