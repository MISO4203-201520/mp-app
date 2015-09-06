package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class AppLogic implements IAppLogic {

    @Inject private AppPersistence persistence;

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
    
    public List<AppDTO> getCheapest(){
        return AppConverter.listEntity2DTO(persistence.getCheapestApp());
    }
}
