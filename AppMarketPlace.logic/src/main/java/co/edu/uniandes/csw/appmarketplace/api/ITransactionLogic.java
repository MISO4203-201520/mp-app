/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.TransactionDTO;
import java.util.List;

/**
 *
 * @author ac.rojas13
 */
public interface ITransactionLogic {

    public List<TransactionDTO> getTransactions(Integer page, Integer maxRecords);

    public TransactionDTO getTransaction(Long id);

    public TransactionDTO createTransaction(TransactionDTO dto);

    public TransactionDTO updateTransaction(TransactionDTO dto);

    public void deleteTransaction(Long id);

    public List<TransactionDTO> findByName(String name);
}
