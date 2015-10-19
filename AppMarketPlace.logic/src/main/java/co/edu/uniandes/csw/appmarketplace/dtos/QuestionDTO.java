/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

import java.util.Date;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ca.forero10
 */
public class QuestionDTO {
    
    private Long id;
    
    private String description;
    
    private Date date;
    
    private String email;
    
    @PodamExclude
    private AppDTO app;
    
    @PodamExclude
    private ClientDTO client;

    public AppDTO getApp() {
        return app;
    }

    public ClientDTO getClient() {
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

    public void setApp(AppDTO app) {
        this.app = app;
    }

    public void setClient(ClientDTO client) {
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
