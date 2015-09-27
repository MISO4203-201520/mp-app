/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author af.esguerra10
 */
@Table(
    uniqueConstraints=
        @UniqueConstraint(columnNames={"client", "app"})
)
@Entity
@NamedQueries({
    @NamedQuery(name = "RateEntity.findByAppClient", query = "SELECT t FROM RateEntity t where t.app.id = :app_id and t.client.id = :client_id"),
    @NamedQuery(name = "RateEntity.avgByApp", query = "SELECT AVG(t.rate) FROM RateEntity t where t.app.id = :app_id")
})
public class RateEntity {

    @Id
    @GeneratedValue(generator = "Rate")
    private Long id;
    private Long rate;

    @ManyToOne
    private ClientEntity client;
    @ManyToOne
    private AppEntity app;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
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
