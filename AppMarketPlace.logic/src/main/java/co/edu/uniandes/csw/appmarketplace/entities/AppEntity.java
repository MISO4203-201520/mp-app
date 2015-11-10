package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @generated
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppEntity.getCheapest", query = "SELECT u FROM AppEntity u WHERE u.price = (SELECT MIN(v.price) FROM AppEntity v WHERE v.developer.name LIKE :developerName) AND u.developer.name LIKE :developerName AND u.enabled = TRUE"),
    @NamedQuery(name = "AppEntity.getAppsByCategory", query = "SELECT u FROM AppEntity u WHERE u.category = :category AND u.enabled = TRUE"),
    @NamedQuery(name = "AppEntity.getAppsByKeyWords", query = "SELECT u FROM AppEntity u WHERE u.category LIKE :keyword OR u.name LIKE :keyword OR u.description LIKE :keyword AND u.enabled = TRUE")
})
public class AppEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "App")
    private Long id;

    private String name;

    private String description;

    private String version;

    private String picture;

    private Integer price;

    private Integer size;

    private String platform;

    private Integer discount;

    private String category;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false)
    private boolean enabled = true;

    @Column(name = "startDiscountDate")
    @Temporal(TemporalType.DATE)
    private Date startDiscountDate;

    @Column(name = "finishDiscountDate")
    @Temporal(TemporalType.DATE)
    private Date finishDiscountDate;

    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppImageEntity> images;

    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppVideoEntity> videos;

    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppSourceEntity> sources;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "app")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "app")
    private List<QuestionEntity> questions;
    
    private String issueUrl;

    @ManyToOne
    private DeveloperEntity developer;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getStartDiscountDate() {
        return startDiscountDate;
    }

    public void setStartDiscountDate(Date startDiscountDate) {
        this.startDiscountDate = startDiscountDate;
    }

    public Date getFinishDiscountDate() {
        return finishDiscountDate;
    }

    public void setFinishDiscountDate(Date finishDiscountDate) {
        this.finishDiscountDate = finishDiscountDate;
    }

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
    public DeveloperEntity getDeveloper() {
        return developer;
    }

    /**
     * @generated
     */
    public void setDeveloper(DeveloperEntity developer) {
        this.developer = developer;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @XmlTransient
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @XmlTransient
    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public List<AppImageEntity> getImages() {
        return images;
    }

    public void setImages(List<AppImageEntity> images) {
        this.images = images;
    }

    public List<AppVideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<AppVideoEntity> videos) {
        this.videos = videos;
    }

    public List<AppSourceEntity> getSources() {
        return sources;
    }

    public void setSources(List<AppSourceEntity> sources) {
        this.sources = sources;
    
    }
    
    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }
}
