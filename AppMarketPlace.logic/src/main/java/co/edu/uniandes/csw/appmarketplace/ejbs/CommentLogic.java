/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.ICommentLogic;
import co.edu.uniandes.csw.appmarketplace.converters.CommentConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.entities.Comment;
import co.edu.uniandes.csw.appmarketplace.persistence.CommentPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.TransactionPersistence;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author if.garcia11
 */
@Stateless
public class CommentLogic implements ICommentLogic {

    @Inject
    private CommentPersistence persistence;
    
    @Inject
    private TransactionPersistence transactionPersistence;

    @Override
    public int countComments() {
        return persistence.count();
    }
    
    @Override
    public CommentDTO InsertComment(CommentDTO dto) {
        Comment entity=null;
        Long transactions = transactionPersistence.countByAppClient(dto.getClient().getId(), dto.getApp().getId());
        if (transactions > 0) {
            try {
            entity=CommentConverter.basicDTO2Entity(dto);
            persistence.InsertComment(entity);
            return CommentConverter.refEntity2DTO(entity);
            } catch (ParseException ex) {
                Logger.getLogger(CommentLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new WebApplicationException(403);
        }
        return null;
    }
    
    @Override
    public List<CommentDTO> getComments(Integer page, Integer maxRecords) {
        
        List<CommentDTO> comments = CommentConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
        return comments;
    }
    
    @Override
    public void deleteComment(Long id) {
        persistence.delete(id);
    }

}
