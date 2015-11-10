/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.Comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author if.garcia11
 * @modified by d.jmenez13 Implementing logger. Shortening technical debt.
 */
public class CommentPersistence extends CrudPersistence<Comment> {

    static final Logger logger = LoggerFactory
            .getLogger(CommentPersistence.class);

    public CommentPersistence() {
        this.entityClass = Comment.class;
    }

    public void insertComment(Comment comment) {
        this.create(comment);
    }

    public List<Comment> getCommentsByApp(Long appId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("app_id", appId);
        return this.executeListNamedQuery("Comment.getCommentsByApp", params);
    }
}
