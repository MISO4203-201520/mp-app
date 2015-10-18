package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Client.getByUsername", query = "select c from ClientEntity c WHERE u.name = :username")
})
@XmlRootElement
public class ClientEntity implements Serializable {

    @Id    
    @GeneratedValue(generator = "Client")    
    private Long id;

    private String name;

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

}
