/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author ac.rojas13
 */
public class TransactionDTO {

    private Long id;
    private ClientDTO payer;
    private AppDTO recipient;
    private int total;
    private String status;
    private PaymentCardDTO paymentCard;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;
    private AppDTO appId;

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
     * @return the payer
     */
    public ClientDTO getPayer() {
        return payer;
    }

    /**
     * @param payer the payer to set
     */
    public void setPayer(ClientDTO payer) {
        this.payer = payer;
    }

    /**
     * @return the recipient
     */
    public AppDTO getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(AppDTO recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the paymentCard
     */
    public PaymentCardDTO getPaymentCard() {
        return paymentCard;
    }

    /**
     * @param paymentCard the paymentCard to set
     */
    public void setPaymentCard(PaymentCardDTO paymentCard) {
        this.paymentCard = paymentCard;
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
     * @return the appId
     */
    public AppDTO getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(AppDTO appId) {
        this.appId = appId;
    }

}
