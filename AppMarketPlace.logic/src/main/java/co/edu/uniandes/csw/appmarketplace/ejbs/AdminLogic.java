/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.ejbs;

import co.edu.uniandes.csw.appmarketplace.api.IAdminLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AdminConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AdminPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ca.forero10
 */
public class AdminLogic implements IAdminLogic {

    @Inject
    private AdminPersistence persistence;

    @Override
    public int countClients() {
        return persistence.count();
    }

    @Override
    public List<AdminDTO> getClients(Integer page, Integer maxRecords) {
        return AdminConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    @Override
    public AdminDTO getClient(Long id) {
        return AdminConverter.fullEntity2DTO(persistence.find(id));
    }

    @Override
    public AdminDTO createClient(AdminDTO dto) {
        AdminEntity entity = AdminConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return AdminConverter.fullEntity2DTO(entity);
    }

    @Override
    public AdminDTO updateClient(AdminDTO dto) {
        AdminEntity entity = persistence.update(AdminConverter.fullDTO2Entity(dto));
        return AdminConverter.fullEntity2DTO(entity);
    }

    @Override
    public void deleteClient(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<AdminDTO> findByName(String name) {
        return AdminConverter.listEntity2DTO(persistence.findByName(name));
    }

    @Override
    public AdminDTO getAdminByUserId(String userId) {
        return AdminConverter.refEntity2DTO(persistence.getAdminByUserId(userId));
    }
}
