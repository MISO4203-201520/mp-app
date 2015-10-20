/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ac.rojas13
 */
@Path("/paymentMethod")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentMethodService{
    
    @Inject private IPaymentMethodLogic paymentMethodLogic;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    
    @POST
    @StatusCreated
    public void createPayment(PaymentMethodDTO dto) {
        paymentMethodLogic.createPaymentMethod(dto);
    }
    
    @GET
    public List<PaymentMethodDTO> getPaymentMethods(){
        return paymentMethodLogic.getPaymentMethods(page, maxRecords);
    }
    
    @GET
    @Path("{id: \\d+}")
    public PaymentMethodDTO getPaymentMethod(@PathParam("id") Long id){
        return paymentMethodLogic.getPaymentMethod(id);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public PaymentMethodDTO updateApp(@PathParam("id") Long id, PaymentMethodDTO dto) {
        dto.setId(id);
        return paymentMethodLogic.updatePaymentMethod(dto);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteApp(@PathParam("id") Long id) {
        paymentMethodLogic.deletePaymentMethod(id);
    }
}
