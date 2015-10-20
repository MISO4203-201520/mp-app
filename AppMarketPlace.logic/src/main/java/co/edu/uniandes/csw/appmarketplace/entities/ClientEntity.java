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
    @NamedQuery(name = "Client.getByUserId", query = "select u from ClientEntity u WHERE u.userId = :user_id"),
    @NamedQuery(name = "Client.getByUsername", query = "select c from ClientEntity c WHERE c.name = :username")
})
@XmlRootElement
public class ClientEntity implements Serializable {

    @Id    
    @GeneratedValue(generator = "Client")    
    private Long id;

    private String name;
    
    private String firstName;
    
    private String lastName;
    
    private String email;

    private String userId;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PaymentCardEntity> cards;

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
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @generated
     */
    @XmlTransient
    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    /**
     * @generated
     */
    public void setCartItems(List<CartItemEntity> cartitems) {
        this.cartItems = cartitems;
    }

    /**
     * @return the cards
     */
    public List<PaymentCardEntity> getCards() {
        return cards;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(List<PaymentCardEntity> cards) {
        this.cards = cards;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
