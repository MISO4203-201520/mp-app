/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.api.ICartItemLogic;
import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.api.IPaymentCardLogic;
import co.edu.uniandes.csw.appmarketplace.api.ITransactionLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.CartItemDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.TransactionDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author ac.rojas13
 */
@Path("/transaction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionService {

    @Inject
    private ITransactionLogic TransactionLogic;
    @Inject
    private IClientLogic clientLogic;
    @Inject
    private IAppLogic appLogic;
    @Inject
    private IPaymentCardLogic paymentCardLogic;
    @Inject
    private ICartItemLogic cartItemLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    private final ClientDTO client = (ClientDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");

    @POST
    @StatusCreated
    public void createPayment(TransactionDTO dto) {
        dto.setPayer(client);
        dto.setPaymentCard(paymentCardLogic.getPaymentCards(dto.getId()));
        dto.setId(null);
        dto.setStatus("Aprobado");
        for (CartItemDTO cartItem : clientLogic.getClient(client.getId()).getCartItems()) {
            dto.setRecipient(cartItem.getApp());
            dto.setTotal((int) ((appLogic.getApp(cartItem.getApp().getId()).getPrice() - appLogic.getApp(cartItem.getApp().getId()).getDiscount()) * Long.parseLong(cartItem.getQuantity().toString())));
            TransactionLogic.createTransaction(dto);
            cartItemLogic.deleteCartItemByClient(client.getId(), cartItem.getId());
        }

    }

    @GET
    public List<TransactionDTO> getPaymentMethods() {
        return TransactionLogic.getTransactions(page, maxRecords);
    }

    @GET
    @Path("{id: \\d+}")
    public TransactionDTO getPaymentMethod(@PathParam("id") Long id) {
        return TransactionLogic.getTransaction(id);
    }

    @PUT
    @Path("{id: \\d+}")
    public TransactionDTO updateApp(@PathParam("id") Long id, TransactionDTO dto) {
        dto.setId(id);
        return TransactionLogic.updateTransaction(dto);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteApp(@PathParam("id") Long id) {
        TransactionLogic.deleteTransaction(id);
    }
}
