package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountStatus;
import com.stormpath.sdk.client.Client;
import com.stormpath.shiro.realm.ApplicationRealm;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {

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
        return developerLogic.getDevelopers(page, maxRecords);
    }

    @GET
    @Path("/clients")
    public List<ClientDTO> getClients() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", clientLogic.countClients());
        }
        return clientLogic.getClients(page, maxRecords);
    }

    @POST
    @Path("/clients/{username}/disable")
    public void disableClient(@PathParam("username") String username) {
        ClientDTO cliente = clientLogic.getClientByUsername(username);
        if (cliente != null) {
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client client = realm.getClient();
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
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client client = realm.getClient();
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