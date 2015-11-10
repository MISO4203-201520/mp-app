/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.QuestionEntity;
import javax.ejb.Stateless;

/**
 *
 * @author ca.forero10
 */
@Stateless
public class QuestionPersistence extends CrudPersistence<QuestionEntity>{
    
    public QuestionPersistence(){
        this.entityClass = QuestionEntity.class;
    }
    
    public void createQuestion(QuestionEntity question){
        this.create(question);
    }
}
