package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.api.ITransactionLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.RateDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated
 */
@Path("/apps")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AppService {

    static final Logger logger = LoggerFactory
            .getLogger(AppService.class);

    @Inject
    private IAppLogic appLogic;
    @Inject
    ITransactionLogic transactionLogic;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest req;
    @Inject
    private IDeveloperLogic developerLogic;
    @Inject
    private IClientLogic clientLogic;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @QueryParam("category")
    private String category;
    @QueryParam("keyword")
    private String keyword;
    @QueryParam("q")
    private String appName;

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public AppDTO createApp(AppDTO dto) {
        UserDTO loggedUser = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("Developer");

        if (loggedUser != null) {
            DeveloperDTO developer = developerLogic.getDeveloperByUsername(loggedUser.getUserName());
            if (developer != null) {
                // Creating the app with its developer
                logger.info("Creating the app {} with its developer {}", dto.getName(), developer.getFullName());
                dto.setDeveloper(developer);
                return appLogic.createApp(dto);
            } else {
                logger.warn("App cannot be created because there's no developer associated.");
                return null;
            }
        } else {
            logger.warn("App cannot be created because there's no developer associated (no session initialized).");
            return null;
        }
    }

    /**
     * @generated
     */
    @GET
    public List<AppDTO> getApps() {

        if (appName != null) {
            return appLogic.findByName(appName);
        }
        if (category != null && !category.isEmpty()) {
            return appLogic.getAppsByCategory(category);
        }
        if (keyword != null && !keyword.isEmpty()) {
            return appLogic.getAppsByKeyWords(keyword);
        }
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", appLogic.countApps());
        }
        return appLogic.getApps(page, maxRecords);
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public AppDTO getApp(@PathParam("id") Long id) {
        AppDTO app = appLogic.getApp(id);
        if (app == null) {
            throw new WebApplicationException(404);
        }
        return app;
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
    @Path("/keywords/{keyword}")
    public List<AppDTO> getAppsByKeyWords(@PathParam("keyword") String keyword) {
        return appLogic.getAppsByCategory(keyword);
    }

    @POST
    @Path("{id: \\d+}/rate")
    public void rateApp(@PathParam("id") Long id, RateDTO dto) {
        UserDTO loggedUser = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");

        if (loggedUser != null) {
            ClientDTO client = clientLogic.getClientByUsername(loggedUser.getUserName());
            if (client != null && dto.getRate() != null) {
                // Rating the app
                logger.info("Rating app with {} points by {}", dto.getRate(), client.getFullName());
                appLogic.rateApp(id, client.getId(), dto.getRate());
                
            } else {
                logger.warn("App cannot be rated because there's no client associated.");
                throw new WebApplicationException(401);
            }
        } else {
            logger.warn("App cannot be created because there's no developer associated (no session initialized).");
            throw new WebApplicationException(401);
        }
    }

    @POST
    @Path("{id: \\d+}/media")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void addMedia(
            @PathParam("id") Long id,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("file") FormDataBodyPart bodyPart) {

        String location = req.getServletContext().getRealPath("/media/" + id);
        String fileName = fileDetail.getFileName();
        // save it
        try {
            String mimetype = bodyPart.getMediaType().toString();
            if (mimetype.contains("image")) {
                writeToFile(fileInputStream, fileName, location);
                appLogic.addImage(id, "media/" + id + "/" + fileName, mimetype);
            }
            if (mimetype.contains("video")) {
                writeToFile(fileInputStream, fileName, location);
                appLogic.addVideo(id, "media/" + id + "/" + fileName, mimetype);
            }
        } catch (IOException e) {
            logger.error("Error saving file", e);
            throw new WebApplicationException(e, 500);
        }
    }

    // save uploaded file to new location
    private void writeToFile(
            InputStream uploadedInputStream,
            String fileName,
            String parentFolder) throws IOException {

        File file = new File(parentFolder, fileName);
        file.getParentFile().mkdirs();
        OutputStream out = new FileOutputStream(file);
        int read = 0;
        byte[] bytes = new byte[1024];

        out = new FileOutputStream(file);
        while ((read = uploadedInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }
}
