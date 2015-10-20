package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
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

/**
 * @generated
 */
@Path("/developers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeveloperService {

    @Inject private IDeveloperLogic developerLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public DeveloperDTO createDeveloper(DeveloperDTO dto) {
        return developerLogic.createDeveloper(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<DeveloperDTO> getDevelopers() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", developerLogic.countDevelopers());
        }
        return developerLogic.getDevelopers(page, maxRecords);
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public DeveloperDTO getDeveloper(@PathParam("id") Long id) {
        return developerLogic.getDeveloper(id);
    }
    
    @GET
    @Path("{username}")
    public DeveloperDTO getDeveloperByUsername(@PathParam("username") String username) {
        return developerLogic.getDeveloperByUsername(username);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public DeveloperDTO updateDeveloper(@PathParam("id") Long id, DeveloperDTO dto) {
        dto.setId(id);
        return developerLogic.updateDeveloper(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDeveloper(@PathParam("id") Long id) {
        developerLogic.deleteDeveloper(id);
    }
}
