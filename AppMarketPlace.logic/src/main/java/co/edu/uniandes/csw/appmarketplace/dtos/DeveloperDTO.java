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
    private String fullName;
    private String email;
    private String status;
    private List<AppDTO> apps;

    private String firstName;
    private String lastName;
    private String photo;
    private String bannerProfile;
    private String commentProfile;
    

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

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return new StringBuilder()
                .append(this.getFirstName())
                .append(" ")
                .append(this.getLastName())
                .toString();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBannerProfile() {
        return bannerProfile;
    }

    public void setBannerProfile(String bannerProfile) {
        this.bannerProfile = bannerProfile;
    }

    public String getCommentProfile() {
        return commentProfile;
    }

    public void setCommentProfile(String commentProfile) {
        this.commentProfile = commentProfile;
    }
}
