/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ca.forero10
 */
@Entity
@XmlRootElement
public class QuestionEntity implements Serializable{
    
    @Id
    @GeneratedValue(generator = "question")
    private Long id;
    
    @ManyToOne
    private AppEntity app;
    
    @OneToOne
    private ClientEntity client;
    
    private String description;
    
    private String email;
    
    @Temporal(DATE)
    private Date date;

    public AppEntity getApp() {
        return app;
    }

    public ClientEntity getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setApp(AppEntity app) {
        this.app = app;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
