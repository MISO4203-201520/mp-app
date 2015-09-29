package co.edu.uniandes.csw.appmarketplace.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement 
public class ClientDTO {

    private Long id;
    private String name;
    private String userId;
    private String fullName;
    private String email;
    private String status;
    private List<CartItemDTO> cartItems;
    private List<PaymentCardDTO> cards;
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
    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    /**
     * @generated
     */
    public void setCartItems(List<CartItemDTO> cartitems) {
        this.cartItems = cartitems;
    }

    /**
     * @return the cards
     */
    public List<PaymentCardDTO> getCards() {
        return cards;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(List<PaymentCardDTO> cards) {
        this.cards = cards;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
