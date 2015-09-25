package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.RateEntity;
import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.RatePersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.TransactionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Stateless
public class AppLogic implements IAppLogic {

    @Inject
    private AppPersistence persistence;

    @Inject
    private RatePersistence ratePersistence;

    @Inject
    private TransactionPersistence transactionPersistence;

    /**
     * @generated
     */
    public int countApps() {
        return persistence.count();
    }

    /**
     * @generated
     */
    public List<AppDTO> getApps(Integer page, Integer maxRecords) {
        return AppConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    public AppDTO getApp(Long id) {
        return AppConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    public AppDTO createApp(AppDTO dto) {
        AppEntity entity = AppConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return AppConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public AppDTO updateApp(AppDTO dto) {
        AppEntity entity = persistence.update(AppConverter.fullDTO2Entity(dto));
        return AppConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public void deleteApp(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    public List<AppDTO> findByName(String name) {
        return AppConverter.listEntity2DTO(persistence.findByName(name));
    }

    public List<AppDTO> getCheapest(String developerName) {
        return AppConverter.listEntity2DTO(persistence.getCheapestApp(developerName));
    }

    public List<AppDTO> getAppsByCategory(String category) {
        return AppConverter.listEntity2DTO(persistence.getAppsByCategory(category));
    }

    public void rateApp(Long appId, Long clientId, Long rateValue) {
        List<TransactionEntity> transactions = transactionPersistence.findByPayer(clientId, appId);
        if (transactions.size() > 0) {
            RateEntity rate = ratePersistence.findByAppClient(clientId, appId);
            if (rate == null) {
                rate = new RateEntity();
                ClientEntity client = new ClientEntity();
                client.setId(clientId);
                rate.setClient(client);
                AppEntity app = new AppEntity();
                app.setId(appId);
                rate.setApp(app);
            }
            rate.setRate(rateValue);
            ratePersistence.update(rate);
        }else{
            throw new WebApplicationException(403);
        }
    }
}
