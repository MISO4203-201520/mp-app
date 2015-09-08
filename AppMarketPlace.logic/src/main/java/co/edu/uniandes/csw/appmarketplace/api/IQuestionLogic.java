/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;

/**
 *
 * @author ca.forero10
 */
public interface IQuestionLogic {    
   
    public void doQuestion(QuestionDTO dto, DeveloperDTO devDto, AppDTO app, String devEmail);
    
}
