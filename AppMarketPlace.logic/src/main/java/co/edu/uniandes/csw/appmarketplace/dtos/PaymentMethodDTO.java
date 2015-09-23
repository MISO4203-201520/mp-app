/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

/**
 *
 * @author ac.rojas13
 */
public class PaymentMethodDTO {
    
    private Long id;
    private String name;

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
     * @return the description
     */
    public String getName() {
        return name;
    }

    /**
     * @param description the description to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
}
