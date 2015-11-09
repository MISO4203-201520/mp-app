/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.api.IPaymentCardLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentCardDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
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
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author ac.rojas13
 */
@Path("/paymentCard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentCardService {

    @Inject
    private IPaymentCardLogic paymentCardLogic;
    @Inject
    private IClientLogic clientLogic;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    private UserDTO loggedUser = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");
    
    @POST
    @StatusCreated
    public void createPayment(PaymentCardDTO dto) {
        paymentCardLogic.createPaymentCards(dto);
    }

    @GET
    public List<PaymentCardDTO> getPaymentMethods() {
        ClientDTO client = clientLogic.getClientByUsername(loggedUser.getUserName());
        return paymentCardLogic.getPaymentCards(Long.valueOf(client.getId().toString()),page, maxRecords);
    }

    @GET
    @Path("{id: \\d+}")
    public PaymentCardDTO getPaymentMethod(@PathParam("id") Long id) {
        return paymentCardLogic.getPaymentCards(id);
    }

    @PUT
    @Path("{id: \\d+}")
    public PaymentCardDTO updateApp(@PathParam("id") Long id, PaymentCardDTO dto) {
        dto.setId(id);
        return paymentCardLogic.updatePaymentCards(dto);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteApp(@PathParam("id") Long id) {
        paymentCardLogic.deletePaymentCards(id);
    }
}
