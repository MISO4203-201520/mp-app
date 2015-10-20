/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.PurchasedRecordDTO;
import java.util.List;

/**
 *
 * @author ac.rojas13
 */
public interface IPurchasedRecordLogic {
    
    public List<PurchasedRecordDTO> getRecords(Integer id,Integer page, Integer maxRecords);

    public PurchasedRecordDTO getRecord(Long id);

    public PurchasedRecordDTO createRecord(PurchasedRecordDTO dto);

    public PurchasedRecordDTO updateRecord(PurchasedRecordDTO dto);

    public void deleteRecord(Long id);

}
