/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author af.decastro879
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "IssueEntity.getIssuesByAppId", query = "SELECT u FROM IssueEntity u WHERE u.app.id = :appId")
})
public class IssueEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "Issue")
    private Long id;
    
    private String comment;
    
    private String type;
    
    private boolean resolved;
    
    private Integer votes;
    
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public AppEntity getApp() {
        return app;
    }

    public void setApp(AppEntity app) {
        this.app = app;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.appmarketplace.entities.IssueEntity[ id=" + id + " ]";
    }
    
}
