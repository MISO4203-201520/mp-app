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

/**
 *
 * @author if.garcia11
 */
public class CommentPersistence extends CrudPersistence<Comment> {

    public CommentPersistence() {
        this.entityClass = Comment.class;
    }

    public void InsertComment(Comment comment) {
        this.create(comment);
    }
    
    public List<Comment> getCommentsByApp(Long appId){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("app_id", appId);
        return this.executeListNamedQuery("Comment.getCommentsByApp", params);
    }
}
