package co.edu.uniandes.csw.appmarketplace.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement 
public class AppDTO {

    private Long id;
    private String name;
    private String description;
    private String version;
    private String picture;
    private Integer price;
    private Integer size;
    private DeveloperDTO developer;
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
    public String getDescription() {
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * @generated
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @generated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @generated
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @generated
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @generated
     */
    public DeveloperDTO getDeveloper() {
        return developer;
    }

    /**
     * @generated
     */
    public void setDeveloper(DeveloperDTO developer) {
        this.developer = developer;
    }

}
