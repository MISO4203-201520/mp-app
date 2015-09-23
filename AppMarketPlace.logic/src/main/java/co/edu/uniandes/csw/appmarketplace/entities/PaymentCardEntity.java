/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ac.rojas13
 */
@Entity
@Table(name = "paymentcardentity")
@NamedQueries({
    @NamedQuery(name = "PaymentCardEntity.findAll", query = "SELECT p FROM PaymentCardEntity p"),
    @NamedQuery(name = "PaymentCardEntity.findById", query = "SELECT p FROM PaymentCardEntity p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentCardEntity.findByFullname", query = "SELECT p FROM PaymentCardEntity p WHERE p.fullname = :fullname"),
    @NamedQuery(name = "PaymentCardEntity.findByCardnumber", query = "SELECT p FROM PaymentCardEntity p WHERE p.cardnumber = :cardnumber")    
    })
public class PaymentCardEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(generator = "PaymentCard")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cardnumber")
    private int cardnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "security_code")
    private short securityCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @JoinColumn(name = "payment_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PaymentMethodEntity paymentType;

    public PaymentCardEntity() {
    }

    public PaymentCardEntity(Long id) {
        this.id = id;
    }

    public PaymentCardEntity(Long id, String fullname, int cardnumber, short securityCode, Date dueDate) {
        this.id = id;
        this.fullname = fullname;
        this.cardnumber = cardnumber;
        this.securityCode = securityCode;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public short getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(short securityCode) {
        this.securityCode = securityCode;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }    

    public PaymentMethodEntity getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethodEntity paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentCardEntity)) {
            return false;
        }
        PaymentCardEntity other = (PaymentCardEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.appmarketplace.entities.PaymentCardEntity[ id=" + id + " ]";
    }
    
}
