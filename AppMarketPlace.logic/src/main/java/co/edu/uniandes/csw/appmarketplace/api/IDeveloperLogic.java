package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import java.util.List;

public interface IDeveloperLogic {
    public int countDevelopers();
    public List<DeveloperDTO> getDevelopers(Integer page, Integer maxRecords);
    public DeveloperDTO getDeveloper(Long id);
    public DeveloperDTO createDeveloper(DeveloperDTO dto);
    public DeveloperDTO updateDeveloper(DeveloperDTO dto);
    public void deleteDeveloper(Long id);
    public List<DeveloperDTO> findByName(String name);
    public DeveloperDTO getDeveloperByUserId(String userId);
    public DeveloperDTO getDeveloperByUsername(String username);
}
