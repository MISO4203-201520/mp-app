package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Developer.getByUserId", query = "select u from DeveloperEntity u WHERE u.userId = :user_id")
})
@XmlRootElement
public class DeveloperEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Developer")
    private Long id;

    private String name;

    private String userId;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppEntity> apps;
    /**
     * @generated
     */
    public Long getId(){
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName(){
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @generated
     */
    public String getUserId(){
        return userId;
    }

    /**
     * @generated
     */
    public void setUserId(String userId){
        this.userId = userId;
    }

    /**
     * @generated
     */
    @XmlTransient
    public List<AppEntity> getApps() {
        return apps;
    }

    /**
     * @generated
     */
    public void setApps(List<AppEntity> apps) {
        this.apps = apps;
    }

}
