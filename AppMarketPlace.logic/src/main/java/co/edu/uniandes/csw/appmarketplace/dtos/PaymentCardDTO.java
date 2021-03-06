/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ac.rojas13
 */
public class PaymentCardDTO {

    private Long id;
    private String fullname;
    private int cardnumber;
    private short securityCode;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dueDate;
    @PodamExclude
    private PaymentMethodDTO paymentType;
    @PodamExclude
    private ClientDTO ownerId;
    
    
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
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the paymentType
     */
    public PaymentMethodDTO getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(PaymentMethodDTO paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the cardnumber
     */
    public int getCardnumber() {
        return cardnumber;
    }

    /**
     * @param cardnumber the cardnumber to set
     */
    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    /**
     * @return the securityCode
     */
    public short getSecurityCode() {
        return securityCode;
    }

    /**
     * @param securityCode the securityCode to set
     */
    public void setSecurityCode(short securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the ownerId
     */
    public ClientDTO getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(ClientDTO ownerId) {
        this.ownerId = ownerId;
    }
    
}
