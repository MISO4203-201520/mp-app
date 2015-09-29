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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author if.garcia11
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Comment.getCommentsByApp", query = "select  u from Comment u WHERE u.app.id = :app_id")
})
public class Comment implements Serializable{
    
    @Id
    @GeneratedValue(generator = "comment")
    
    private Long id;
    
    private String comment;

    @Temporal(DATE)
    private Date date ;

    @OneToOne
    private ClientEntity client;
    
    @ManyToOne
    private AppEntity app;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public AppEntity getApp() {
        return app;
    }

    public void setApp(AppEntity app) {
        this.app = app;
    }

}