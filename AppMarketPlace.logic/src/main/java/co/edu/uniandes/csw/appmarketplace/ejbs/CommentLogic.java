/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.ICommentLogic;
import co.edu.uniandes.csw.appmarketplace.converters.CommentConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.persistence.CommentPersistence;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author if.garcia11
 */
@Stateless
public class CommentLogic implements ICommentLogic {

    @Inject
    private CommentPersistence persistence;

    @Override
    public int countComments() {
        return persistence.count();
    }
    
    @Override
    public void InsertComment(CommentDTO dto) {
        try {
            persistence.InsertComment(CommentConverter.basicDTO2Entity(dto));
        } catch (ParseException ex) {
            Logger.getLogger(CommentLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
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
