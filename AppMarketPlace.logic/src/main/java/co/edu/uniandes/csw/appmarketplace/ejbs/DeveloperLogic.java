package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.converters.DeveloperConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.DeveloperPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class DeveloperLogic implements IDeveloperLogic {

    @Inject private DeveloperPersistence persistence;

    /**
     * @generated
     */
    public int countDevelopers() {
        return persistence.count();
    }

    /**
     * @generated
     */
    public List<DeveloperDTO> getDevelopers(Integer page, Integer maxRecords) {
        return DeveloperConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    public DeveloperDTO getDeveloper(Long id) {
        return DeveloperConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    public DeveloperDTO createDeveloper(DeveloperDTO dto) {
        DeveloperEntity entity = DeveloperConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return DeveloperConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public DeveloperDTO updateDeveloper(DeveloperDTO dto) {
        DeveloperEntity entity = persistence.update(DeveloperConverter.fullDTO2Entity(dto));
        return DeveloperConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public void deleteDeveloper(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    public List<DeveloperDTO> findByName(String name) {
        return DeveloperConverter.listEntity2DTO(persistence.findByName(name));
    }
    
    public DeveloperDTO getDeveloperByUserId(String userId) {
        return DeveloperConverter.refEntity2DTO(persistence.getDeveloperByUserId(userId));
    }
}
