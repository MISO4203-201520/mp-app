/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.PaymentCardDTO;
import java.util.List;

/**
 *
 * @author ac.rojas13
 */
public interface IPaymentCardLogic {

    public List<PaymentCardDTO> getPaymentCards(Integer page, Integer maxRecords);

    public PaymentCardDTO getPaymentCards(Long id);

    public PaymentCardDTO createPaymentCards(PaymentCardDTO dto);

    public PaymentCardDTO updatePaymentCards(PaymentCardDTO dto);

    public void deletePaymentCards(Long id);

    public List<PaymentCardDTO> findByName(String name);
}
