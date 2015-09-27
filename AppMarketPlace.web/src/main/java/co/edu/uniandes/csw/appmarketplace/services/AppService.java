package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.api.ITransactionLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.RateDTO;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;

/**
 * @generated
 */
@Path("/apps")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AppService {

    @Inject
    private IAppLogic appLogic;
    @Inject
    ITransactionLogic transactionLogic;
    @Context
    private HttpServletResponse response;
    @Inject
    private IDeveloperLogic developerLogic;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @QueryParam("q")
    private String appName;
    private DeveloperDTO developer = (DeveloperDTO) SecurityUtils.getSubject().getSession().getAttribute("Developer");
    private ClientDTO client = (ClientDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public AppDTO createApp(AppDTO dto) {
        if (developer == null) {
            return null;
        }
        dto.setDeveloper(developer);
        return appLogic.createApp(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<AppDTO> getApps() {
        if (developer != null) {
            return developerLogic.getDeveloper(developer.getId()).getApps();
        } else {
            if (appName != null) {
                return appLogic.findByName(appName);
            }
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", appLogic.countApps());
            }
            return appLogic.getApps(page, maxRecords);
        }
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public AppDTO getApp(@PathParam("id") Long id) {
        return appLogic.getApp(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public AppDTO updateApp(@PathParam("id") Long id, AppDTO dto) {
        dto.setId(id);
        return appLogic.updateApp(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteApp(@PathParam("id") Long id) {
        appLogic.deleteApp(id);
    }

    @GET
    @Path("/cheapest/{developerName}")
    public List<AppDTO> getCheapest(@PathParam("developerName") String name) {
        return appLogic.getCheapest(name);
    }

    /**
     * @generated
     */
    @GET
    @Path("/categories/{category}")
    public List<AppDTO> getAppsByCategory(@PathParam("category") String category) {
        return appLogic.getAppsByCategory(category);
    }

    @GET
    @Path("{id: \\d+}/purchases")
    public List<TransactionDTO> isBought(@PathParam("id") Long id) {
        if (client != null) {
            return transactionLogic.findByClient(client.getId(), id);
        }
        return new ArrayList();
    }

    @POST
    @Path("{id: \\d+}/rate")
    public void rateApp(@PathParam("id") Long id, RateDTO dto) {
        if (client != null && dto.getRate() != null) {
            appLogic.rateApp(id, client.getId(), dto.getRate());
        }else{
            throw new WebApplicationException(401);
        }
    }
}
