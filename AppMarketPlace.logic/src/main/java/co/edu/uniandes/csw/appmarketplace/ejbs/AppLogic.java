package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.TransactionConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.TransactionDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.AppImageEntity;
import co.edu.uniandes.csw.appmarketplace.entities.AppVideoEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.RateEntity;
import co.edu.uniandes.csw.appmarketplace.entities.TransactionEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppImagePersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.AppPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.AppVideoPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.RatePersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.TransactionPersistence;
import java.util.ArrayList;
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

    @Inject
    private AppImagePersistence imgPersistence;

    @Inject
    private AppVideoPersistence vidPersistence;

    String excludeWords[] = {"a", "e", "i", "o", "u", "el", "la", "las", "los", "al", "un", "en", "es", "del", "lo"};

    /**
     * @generated
     */
    @Override
    public int countApps() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<AppDTO> getApps(Integer page, Integer maxRecords) {

        List<AppDTO> apps = AppConverter.listEntity2DTO(persistence.findAll(page, maxRecords));

        // Antes de retornar el listado, le coloco el rate a cada producto
        for (AppDTO dto : apps) {
            dto.setRate(ratePersistence.getAverageByApp(dto.getId()));
        }

        return apps;
    }

    /**
     * @generated
     */
    @Override
    public AppDTO getApp(Long id) {
        AppDTO dto = AppConverter.fullEntity2DTO(persistence.find(id));
        if (dto != null) {
            dto.setDownloads(transactionPersistence.countByApp(id));
            dto.setRate(ratePersistence.getAverageByApp(id));
        }
        return dto;
    }

    /**
     * @generated
     */
    @Override
    public AppDTO createApp(AppDTO dto) {
        AppEntity entity = AppConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return AppConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public AppDTO updateApp(AppDTO dto) {
        AppEntity entity = persistence.update(AppConverter.fullDTO2Entity(dto));
        return AppConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteApp(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<AppDTO> findByName(String name) {
        return AppConverter.listEntity2DTO(persistence.findByName(name));
    }

    @Override
    public List<AppDTO> getCheapest(String developerName) {
        return AppConverter.listEntity2DTO(persistence.getCheapestApp(developerName));
    }

    @Override
    public List<AppDTO> getAppsByCategory(String category) {
        return AppConverter.listEntity2DTO(persistence.getAppsByCategory(category));
    }

    @Override
    public List<AppDTO> getAppsByKeyWords(String keyword) {
        List<AppEntity> lista = new ArrayList<AppEntity>();
        if (keyword == null) {
            return AppConverter.listEntity2DTO(lista);
        }

        lista.addAll(persistence.getAppsByKeyWords(keyword));
        String words[] = keyword.split(" ");
        if (words.length > 1) {
            for (String word : words) {
                if (verifyWord(word) == false) {
                    for (AppEntity newApp : persistence.getAppsByKeyWords(word)) {
                        if (verifyExistingApp(lista, newApp) == false) {
                            lista.add(newApp);
                        }
                    }
                }
            }
        }
        return AppConverter.listEntity2DTO(lista);
    }

    public Boolean verifyExistingApp(List<AppEntity> list, AppEntity newApp) {
        for (AppEntity app : list) {
            if (app.getId() == newApp.getId()) {
                return true;
            }

        }
        return false;
    }

    public Boolean verifyWord(String word) {

        for (String excludeWord : excludeWords) {
            if (word.equals(excludeWord)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void rateApp(Long appId, Long clientId, Long rateValue) {
        Long transactions = transactionPersistence.countByAppClient(clientId, appId);
        if (transactions > 0) {
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
        } else {
            throw new WebApplicationException(403);
        }
    }

    @Override
    public void addImage(Long appId, String url, String mimetype) {
        AppEntity app = persistence.find(appId);
        AppImageEntity img = new AppImageEntity();
        img.setApp(app);
        img.setUrl(url);
        img.setMimetype(mimetype);
        imgPersistence.create(img);
    }

    @Override
    public void addVideo(Long appId, String url, String mimetype) {
        AppEntity app = persistence.find(appId);
        AppVideoEntity vid = new AppVideoEntity();
        vid.setApp(app);
        vid.setUrl(url);
        vid.setMimetype(mimetype);
        vidPersistence.create(vid);
    }

    @Override
    public void disableApp(Long appId) {
        AppEntity entity = persistence.find(appId);
        entity.setEnabled(!entity.isEnabled());
        persistence.update(entity);
    }
    
    @Override
    public List<TransactionDTO> findByApp(Long appId) {
        return TransactionConverter.listEntity2DTO(transactionPersistence.findByApp(appId));
    }
}
