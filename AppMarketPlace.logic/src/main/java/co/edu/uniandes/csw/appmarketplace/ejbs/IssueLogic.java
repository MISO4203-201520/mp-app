package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IIssueLogic;
import co.edu.uniandes.csw.appmarketplace.converters.IssueConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.IssueDTO;
import co.edu.uniandes.csw.appmarketplace.entities.IssueEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.IssuePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ca.forero10
 * @modified by d.jimenez13 Change method name according to AdminDTO
 */
public class IssueLogic implements IIssueLogic {

    @Inject
    private IssuePersistence persistence;

    @Override
    public int countIssues() {
        return persistence.count();
    }

    @Override
    public List<IssueDTO> getIssues(Integer page, Integer maxRecords) {
        return IssueConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    @Override
    public IssueDTO getIssue(Long id) {
        return IssueConverter.fullEntity2DTO(persistence.find(id));
    }

    @Override
    public IssueDTO createIssue(IssueDTO dto) {
        IssueEntity entity = IssueConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return IssueConverter.fullEntity2DTO(entity);
    }

    @Override
    public IssueDTO updateIssue(IssueDTO dto) {
        IssueEntity entity = persistence.update(IssueConverter.fullDTO2Entity(dto));
        return IssueConverter.fullEntity2DTO(entity);
    }

    @Override
    public void deleteIssue(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<IssueDTO> getIssuesByAppId(String appId) {
        return IssueConverter.listEntity2DTO(persistence.getIssuesByAppId(appId));
    }
    
    @Override
    public void resolveIssue(Long id){
        IssueEntity entity = persistence.find(id);
        entity.setResolved(true);
        persistence.update(entity);
    }
}