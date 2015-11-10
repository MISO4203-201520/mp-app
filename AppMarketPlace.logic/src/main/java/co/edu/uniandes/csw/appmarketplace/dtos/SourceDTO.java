package co.edu.uniandes.csw.appmarketplace.dtos;

/**
 *
 * @author djimenez
 */
public class SourceDTO {
    private Long id;
    private String url;
    private String version;
    private AppDTO app;

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

    public AppDTO getApp() {
        return app;
    }

    public void setApp(AppDTO app) {
        this.app = app;
    }
}