/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.appmarketplace.entities.QuestionEntity;
import java.text.ParseException;

/**
 *
 * @author ca.forero10
 */
public abstract class QuestionConverter {

    private QuestionConverter() {
    }

    public static QuestionEntity basicDTO2Entity(QuestionDTO dto) throws ParseException {
        if (dto != null) {
            QuestionEntity entity = new QuestionEntity();
            entity.setId(dto.getId());
            entity.setEmail(dto.getEmail());
            entity.setDescription(dto.getDescription());
            entity.setDate(dto.getDate());
            entity.setClient(ClientConverter.refDTO2Entity(dto.getClient()));
            entity.setApp(AppConverter.refDTO2Entity(dto.getApp()));
            return entity;
        } else {
            return null;
        }
    }
}
