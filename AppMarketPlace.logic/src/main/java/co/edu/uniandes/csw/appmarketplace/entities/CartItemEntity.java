package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class CartItemEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "CartItem")
    private Long id;

    private String name;

    private Integer quantity;

    @ManyToOne
    private AppEntity app;
    @ManyToOne
    private ClientEntity client;
    /**
     * @generated
     */
    public Long getId(){
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName(){
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @generated
     */
    public Integer getQuantity(){
        return quantity;
    }

    /**
     * @generated
     */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    /**
     * @generated
     */
    public AppEntity getApp() {
        return app;
    }

    /**
     * @generated
     */
    public void setApp(AppEntity app) {
        this.app = app;
    }

    /**
     * @generated
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * @generated
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

}
