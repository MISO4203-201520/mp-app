/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import java.util.List;

/**
 *
 * @author ac.rojas13
 */
public interface IPaymentMethodLogic {

    public List<PaymentMethodDTO> getPaymentMethods(Integer page, Integer maxRecords);

    public PaymentMethodDTO getPaymentMethod(Long id);

    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO dto);

    public PaymentMethodDTO updatePaymentMethod(PaymentMethodDTO dto);

    public void deletePaymentMethod(Long id);
    
    public List<PaymentMethodDTO> findByName(String name);

}
