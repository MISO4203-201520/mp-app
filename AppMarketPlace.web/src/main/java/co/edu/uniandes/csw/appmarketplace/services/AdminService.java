package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import static co.edu.uniandes.csw.appmarketplace.shiro.ShiroUtils.getClient;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountStatus;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.resource.ResourceException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @modified by d.jimenez13 Find developers and clients by username instead id
 * to avoid test errors Switch account status for developers and clients to
 * catch ResourceException and avoid test errors
 */
@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {

    static final Logger logger = LoggerFactory
            .getLogger(AdminService.class);

    @Inject
    private IClientLogic clientLogic;
    @Inject
    private IDeveloperLogic developerLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;

    @GET
    @Path("/developers")
    public List<DeveloperDTO> getDevelopers() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", developerLogic.countDevelopers());
        }
        List<DeveloperDTO> developers = developerLogic.getDevelopers(page, maxRecords);
        Client clientWS = getClient();
        for (DeveloperDTO developer : developers) {
            try {
                Account account = clientWS.getResource(developer.getUserId(), Account.class);
                developer.setStatus(account.getStatus().name());
            } catch (ResourceException e) {
                logger.warn("Account resource for developer {} was not found!",
                        developer.getUserId(), e);
            }
        }
        return developers;
    }

    @GET
    @Path("/clients")
    public List<ClientDTO> getClients() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", clientLogic.countClients());
        }
        List<ClientDTO> clients = clientLogic.getClients(page, maxRecords);
        Client clientWS = getClient();
        for (ClientDTO client : clients) {
            try {
                Account account = clientWS.getResource(client.getUserId(), Account.class);
                client.setStatus(account.getStatus().name());
            } catch (ResourceException e) {
                logger.warn("Account resource for client {} was not found!",
                        client.getUserId(), e);
            }
        }
        return clients;
    }

    @POST
    @Path("/clients/{username}/disable")
    public void disableClient(@PathParam("username") String username) {
        ClientDTO cliente = clientLogic.getClientByUsername(username);
        if (cliente != null) {
            Client client = getClient();
            Account account = client.getResource(cliente.getUserId(), Account.class);
            if (account.getStatus() == AccountStatus.DISABLED) {
                account.setStatus(AccountStatus.ENABLED);
            } else if (account.getStatus() == AccountStatus.ENABLED) {
                account.setStatus(AccountStatus.DISABLED);
            }
            account.save();
        }

    }

    @POST
    @Path("/developers/{username}/disable")
    public void disableDeveloper(@PathParam("username") String username) {
        DeveloperDTO developer = developerLogic.getDeveloperByUsername(username);

        if (developer != null) {
            Client client = getClient();
            Account account = client.getResource(developer.getUserId(), Account.class);
            if (account.getStatus() == AccountStatus.DISABLED) {
                account.setStatus(AccountStatus.ENABLED);
            } else if (account.getStatus() == AccountStatus.ENABLED) {
                account.setStatus(AccountStatus.DISABLED);
            }
            account.save();
        }
    }
}
