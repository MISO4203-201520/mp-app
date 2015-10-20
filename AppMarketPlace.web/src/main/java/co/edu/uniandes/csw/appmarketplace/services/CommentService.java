/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.api.ICommentLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author if.garcia11
 */
@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentService {

    private  ClientDTO client;
    @Inject
    private IClientLogic clientLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;

    @Inject
    private ICommentLogic commentLogic;
static final Logger logger = LoggerFactory
            .getLogger(CommentService.class);
    @POST
    @StatusCreated
    @Consumes("application/json")
    public CommentDTO insertComment(CommentDTO dto) {
        UserDTO loggedUser = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("Client");

            if (loggedUser != null) {                
                client = clientLogic.getClientByUsername(loggedUser.getUserName());
                dto.setClient(client);
        
              return  commentLogic.InsertComment(dto);
            } else {
                client = null;
            }
            return null;
        }
            
    

    @GET
    public List<CommentDTO> getComments() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", commentLogic.countComments());
        }
        List<CommentDTO> comments = commentLogic.getComments(page, maxRecords);
        return comments;
    }

    @DELETE
    @Path("/{id: \\d+}")
    public void deleteComment(@PathParam("id") Long id) {
        commentLogic.deleteComment(id);
    }
}
