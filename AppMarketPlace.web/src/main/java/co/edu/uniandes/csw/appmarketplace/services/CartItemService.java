package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.ICartItemLogic;
import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.CartItemDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.SecurityUtils;

/**
 * @generated
 */
@Path("/cartItems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartItemService {

    @Inject
    private ICartItemLogic cartItemLogic;
    @Inject
    private IClientLogic clientLogic;
    private ClientDTO client;

    public ClientDTO getClient() {
        if (client == null) {
            UserDTO loggedUser = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");

            if (loggedUser != null) {
                client = clientLogic.getClientByUsername(loggedUser.getUserName());
            } else {
                client = null;
            }
        }
        return client;
    }

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public CartItemDTO createCartItem(CartItemDTO dto) {
        // Getting object ClientDTO to avoid NullPointerException
        if (getClient() == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        return cartItemLogic.createCartItemByClient(dto, client.getId());
    }

    /**
     * @generated
     */
    @GET
    public List<CartItemDTO> getCartItems() {
        if (getClient() == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        return clientLogic.getClient(client.getId()).getCartItems();
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CartItemDTO getCartItem(@PathParam("id") Long id) {
        if (getClient() == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        return cartItemLogic.getCartItemsByClientById(id, client.getId());
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CartItemDTO updateCartItem(@PathParam("id") Long id, CartItemDTO dto) {
        if (getClient() == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        dto.setId(id);
        return cartItemLogic.updateCartItemByClient(client.getId(), dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCartItem(@PathParam("id") Long id) {
        if (getClient() == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        cartItemLogic.deleteCartItemByClient(client.getId(), id);
    }
}
