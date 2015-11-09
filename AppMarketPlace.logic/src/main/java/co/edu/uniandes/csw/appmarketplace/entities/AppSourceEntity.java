package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author djimenez
 */
@Entity
public class AppSourceEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "AppSource")
    private Long id;
    private String url;
    private String version;
    @ManyToOne
    private AppEntity app;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AppEntity getApp() {
        return app;
    }

    public void setApp(AppEntity app) {
        this.app = app;
    }
}