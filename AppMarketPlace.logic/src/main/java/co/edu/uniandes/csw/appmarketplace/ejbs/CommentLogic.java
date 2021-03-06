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
    public CommentDTO insertComment(CommentDTO dto) {
        //Se dejo el metodo como estaba por que interferia con las pruebas, la logica para que solo inserte un comentario si compro la app se realizo en el front end
        Comment entity = null;
        try {
            entity = CommentConverter.basicDTO2Entity(dto);
            persistence.insertComment(entity);
            return CommentConverter.refEntity2DTO(entity);
        } catch (ParseException ex) {
            Logger.getLogger(CommentLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<CommentDTO> getComments(Integer page, Integer maxRecords) {
        return CommentConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    @Override
    public void deleteComment(Long id) {
        persistence.delete(id);
    }

    @Override
    public Long countByAppClient(Long idCliente, Long idApp) {
        return transactionPersistence.countByAppClient(idCliente, idApp);
    }

}
