/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ac.rojas13
 */
@Entity
@Table(name = "paymentmethodentity")
@NamedQueries({
    @NamedQuery(name = "PaymentMethodEntity.findAll", query = "SELECT p FROM PaymentMethodEntity p"),
    @NamedQuery(name = "PaymentMethodEntity.findById", query = "SELECT p FROM PaymentMethodEntity p WHERE p.id = :id")})
public class PaymentMethodEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(generator = "PaymentMethod")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentType")
    private Collection<PaymentCardEntity> paymentCardEntityCollection;

    public PaymentMethodEntity() {
    }

    public PaymentMethodEntity(Long id) {
        this.id = id;
    }

    public PaymentMethodEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PaymentCardEntity> getPaymentCardEntityCollection() {
        return paymentCardEntityCollection;
    }

    public void setPaymentCardEntityCollection(Collection<PaymentCardEntity> paymentCardEntityCollection) {
        this.paymentCardEntityCollection = paymentCardEntityCollection;
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
        if (!(object instanceof PaymentMethodEntity)) {
            return false;
        }
        PaymentMethodEntity other = (PaymentMethodEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.appmarketplace.entities.PaymentMethodEntity[ id=" + id + " ]";
    }
    
}
