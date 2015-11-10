package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "Admin.getByUserId", query = "select u from AdminEntity u WHERE u.userId = :user_id")
})
@XmlRootElement
public class AdminEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Admin")
    private Long id;

    private String name;

    private String userId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
