/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IAdminLogic;
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

/**
 *
 * @author ca.forero10
 */
@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {
    
    @Inject private IClientLogic clientLogic;
    @Inject private IDeveloperLogic developerLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    
    @GET
    @Path("/developers")
    public List<DeveloperDTO> getDevelopers() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", developerLogic.countDevelopers());
        }
        List<DeveloperDTO> developers = developerLogic.getDevelopers(page, maxRecords);
        for(DeveloperDTO developer: developers){
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client cl = realm.getClient();
            Account account = cl.getResource(developer.getUserId(), Account.class);
            developer.setFullName(account.getFullName());
            developer.setEmail(account.getEmail());
            developer.setStatus(account.getStatus().name());
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
        for(ClientDTO client: clients){
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client cl = realm.getClient();
            Account account = cl.getResource(client.getUserId(), Account.class);
            client.setFullName(account.getFullName());
            client.setEmail(account.getEmail());
            client.setStatus(account.getStatus().name());
        }
        return clients;
    }
    
    @POST
    @Path("/clients/{id: \\d+}/disable")
    public void disableClient(@PathParam("id") Long id) {
        ClientDTO cl = clientLogic.getClient(id);
        if(cl!=null){
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client client = realm.getClient();
            Account account = client.getResource(cl.getUserId(), Account.class);
            if(account.getStatus()==AccountStatus.DISABLED)
               account.setStatus(AccountStatus.ENABLED);
            else if(account.getStatus()==AccountStatus.ENABLED)
                account.setStatus(AccountStatus.DISABLED);
            account.save();
        }
        
    }
    
    @POST
    @Path("/developers/{id: \\d+}/disable")
    public void disableDeveloper(@PathParam("id") Long id) {
        DeveloperDTO dev = developerLogic.getDeveloper(id);
        if(dev!=null){
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client client = realm.getClient();
            Account account = client.getResource(dev.getUserId(), Account.class);
            if(account.getStatus()==AccountStatus.DISABLED)
               account.setStatus(AccountStatus.ENABLED);
            else if(account.getStatus()==AccountStatus.ENABLED)
                account.setStatus(AccountStatus.DISABLED);
            account.save();
        }
    }
}
