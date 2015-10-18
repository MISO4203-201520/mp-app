package co.edu.uniandes.csw.appmarketplace.dtos;

public class MediaDTO {

    private Long id;

    private String url;
    
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

    public AppDTO getApp() {
        return app;
    }

    public void setApp(AppDTO app) {
        this.app = app;
    }
}
