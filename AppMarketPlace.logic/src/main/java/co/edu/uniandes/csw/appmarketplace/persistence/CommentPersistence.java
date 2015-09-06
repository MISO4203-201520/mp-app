/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.Comment;

/**
 *
 * @author if.garcia11
 */
public class CommentPersistence extends CrudPersistence <Comment> {
    
    public CommentPersistence(){
    this.entityClass = Comment.class;
    
    
    }
    
    public void InsertComment(Comment comment){
        this.create(comment);
    }
}
