/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import java.util.List;

/**
 *
 * @author ca.forero10
 */
public interface IAdminLogic {
    public int countClients();
    public List<AdminDTO> getClients(Integer page, Integer maxRecords);
    public AdminDTO getClient(Long id);
    public AdminDTO createClient(AdminDTO dto);
    public AdminDTO updateClient(AdminDTO dto);
    public void deleteClient(Long id);
    public List<AdminDTO> findByName(String name);
    public AdminDTO getAdminByUserId(String userId);
}
