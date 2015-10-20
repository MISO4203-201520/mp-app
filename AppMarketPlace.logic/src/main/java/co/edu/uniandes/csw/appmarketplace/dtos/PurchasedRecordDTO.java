/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import java.util.Date;

/**
 *
 * @author ac.rojas13
 */
public class PurchasedRecordDTO {
    
    private Long id;
    private int quantity;
    private Date date;    
    private ClientEntity clientId;
    private AppEntity appId;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the clientId
     */
    public ClientEntity getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(ClientEntity clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the appId
     */
    public AppEntity getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(AppEntity appId) {
        this.appId = appId;
    }
}
