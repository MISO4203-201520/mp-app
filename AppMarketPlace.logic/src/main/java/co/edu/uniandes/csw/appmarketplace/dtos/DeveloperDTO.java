package co.edu.uniandes.csw.appmarketplace.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement 
public class DeveloperDTO {

    private Long id;
    private String name;
    private String userId;
    private List<AppDTO> apps;
    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @generated
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }

    /**
     * @generated
     */
    public List<AppDTO> getApps() {
        return apps;
    }

    /**
     * @generated
     */
    public void setApps(List<AppDTO> apps) {
        this.apps = apps;
    }

}
