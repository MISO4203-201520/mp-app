package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import java.util.List;

public interface IAppLogic {

    public int countApps();

    public List<AppDTO> getApps(Integer page, Integer maxRecords);

    public AppDTO getApp(Long id);

    public AppDTO createApp(AppDTO dto);

    public AppDTO updateApp(AppDTO dto);

    public void deleteApp(Long id);

    public List<AppDTO> findByName(String name);

    public List<AppDTO> getCheapest(String developerName);

    public List<AppDTO> getAppsByCategory(String category);

    public List<AppDTO> getAppsByKeyWords(String keyword);

    public void rateApp(Long appId, Long clientId, Long rateValue);

    public void addImage(Long appId, String url, String mimetype);

    public void addVideo(Long appId, String url, String mimetype);
}
