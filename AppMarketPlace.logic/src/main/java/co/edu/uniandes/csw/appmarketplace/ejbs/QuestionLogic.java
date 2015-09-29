/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IQuestionLogic;
import co.edu.uniandes.csw.appmarketplace.converters.QuestionConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.appmarketplace.persistence.QuestionPersistence;
import co.edu.uniandes.csw.appmarketplace.utils.Emailer;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ca.forero10
 */
@Stateless
public class QuestionLogic implements IQuestionLogic {

    @Inject
    private QuestionPersistence persistence;

    @Override
    public void doQuestion(QuestionDTO question, DeveloperDTO dev, AppDTO app, String devEmail) {
        try {
            //TODO email dev is missing
            persistence.createQuestion(QuestionConverter.basicDTO2Entity(question));
            Emailer.sendQuestionEmail(dev.getName(),
                    devEmail, question.getDescription(),
                    question.getDate(), question.getClient().getName(), question.getEmail(),
                    app.getName());
        } catch (ParseException ex) {
            Logger.getLogger(CommentLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
