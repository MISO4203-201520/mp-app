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
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
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
    @Inject private IAdminLogic adminLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    
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
    @Path("/clients/{id: \\d+}/disable")
    public void disableClient(@PathParam("id") Long id) {
        clientLogic.disableClient(id);
    }
    
    @POST
    @Path("/developers/{id: \\d+}/disable")
    public void disableDeveloper(@PathParam("id") Long id) {
        developerLogic.disableDeveloper(id);
    }
}
