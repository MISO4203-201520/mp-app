/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.ICommentLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author if.garcia11
 */
@Path("/comments")

public class CommentService {
    @Inject private ICommentLogic commentLogic;
    
    @POST
    @StatusCreated
    @Consumes("application/json")
    public void insertComment(CommentDTO dto) {
    commentLogic.InsertComment(dto);
    }
}
