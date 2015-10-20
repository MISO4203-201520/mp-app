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
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import co.edu.uniandes.csw.appmarketplace.utils.Emailer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    private UserDTO loggedUser = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");

    @POST
    @StatusCreated
    public void createPayment(TransactionDTO dto) throws ParseException {
        Subject currentUser = SecurityUtils.getSubject();
        if (loggedUser == null) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, new Exception("User is not a registered client"));
            return;
        }
        Map<String, String> userAttributes = (Map<String, String>) currentUser.getPrincipals().oneByType(java.util.Map.class);
        ClientDTO client = clientLogic.getClientByUsername(loggedUser.getUserName());
        dto.setPayer(client);
        dto.setPaymentCard(paymentCardLogic.getPaymentCards(dto.getId()));
        dto.setId(null);
        dto.setStatus("Aprobado");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dto.setDate(dateFormat.parse(dateFormat.format(date)));
        int total = 0;
        int number = 0;
        for (CartItemDTO cartItem : clientLogic.getClient(client.getId()).getCartItems()) {
            dto.setRecipient(cartItem.getApp());
            Date actualDate =new Date();
            // Verifica si esta en el rango de fechas para aplicar el descuento.
            if (actualDate.compareTo(cartItem.getApp().getStartDiscountDate())>=0 && actualDate.compareTo(cartItem.getApp().getFinishDiscountDate())<=0){
                dto.setTotal((int) ((appLogic.getApp(cartItem.getApp().getId()).getPrice() - appLogic.getApp(cartItem.getApp().getId()).getDiscount()) * Long.parseLong(cartItem.getQuantity().toString())));
            }else{
                dto.setTotal((int) ((appLogic.getApp(cartItem.getApp().getId()).getPrice()) * Long.parseLong(cartItem.getQuantity().toString())));
            }
            dto.setAppId(appLogic.getApp(cartItem.getApp().getId()));
            TransactionLogic.createTransaction(dto);
            cartItemLogic.deleteCartItemByClient(client.getId(), cartItem.getId());
            number++;
            // Verifica si esta en el rango de fechas para aplicar el descuento.
            if (actualDate.compareTo(cartItem.getApp().getStartDiscountDate())>=0 && actualDate.compareTo(cartItem.getApp().getFinishDiscountDate())<=0){
                total += (int) ((appLogic.getApp(cartItem.getApp().getId()).getPrice() - appLogic.getApp(cartItem.getApp().getId()).getDiscount()) * Long.parseLong(cartItem.getQuantity().toString()));
            }else
                total += (int) ((appLogic.getApp(cartItem.getApp().getId()).getPrice()) * Long.parseLong(cartItem.getQuantity().toString()));
            
        }
        Emailer.sendPaymentEmail(client.getName(), userAttributes.get("email"), Integer.toString(total), new Date(), Integer.toString(number));
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
    
    @GET
    @Path("user/{userid}")
    public List<TransactionDTO> getTxByUserId(@PathParam("userid") Long userid) {
        return TransactionLogic.getTransactionByPayer(userid);
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
