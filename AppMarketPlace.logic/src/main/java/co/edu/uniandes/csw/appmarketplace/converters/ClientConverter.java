package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class ClientConverter {

    /**
     * @generated
     */
    private ClientConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static ClientDTO refEntity2DTO(ClientEntity entity) {
        if (entity != null) {
            ClientDTO dto = new ClientDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setEmail(entity.getEmail());
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @param dto
     * @return
     * @generated
     */
    public static ClientEntity refDTO2Entity(ClientDTO dto) {
        if (dto != null) {
            ClientEntity entity = new ClientEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static ClientDTO basicEntity2DTO(ClientEntity entity) {
        if (entity != null) {
            ClientDTO dto = new ClientDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());
            dto.setCards(PaymentCardConverter.listEntity2DTO(entity.getCards()));
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setEmail(entity.getEmail());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static ClientEntity basicDTO2Entity(ClientDTO dto) {
        if (dto != null) {
            ClientEntity entity = new ClientEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setUserId(dto.getUserId());
            entity.setCards(PaymentCardConverter.listDTO2Entity(dto.getCards()));
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setEmail(dto.getEmail());            
                    
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static ClientDTO fullEntity2DTO(ClientEntity entity) {
        if (entity != null) {
            ClientDTO dto = basicEntity2DTO(entity);
            dto.setCartItems(CartItemConverter.listEntity2DTO(entity.getCartItems()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static ClientEntity fullDTO2Entity(ClientDTO dto) {
        if (dto != null) {
            ClientEntity entity = basicDTO2Entity(dto);
            entity.setCartItems(CartItemConverter.childListDTO2Entity(dto.getCartItems(), entity));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */

    public static List<ClientDTO> listEntity2DTO(List<ClientEntity> entities) {
        List<ClientDTO> dtos = new ArrayList<ClientDTO>();
        if (entities != null) {
            for (ClientEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<ClientEntity> listDTO2Entity(List<ClientDTO> dtos) {
        List<ClientEntity> entities = new ArrayList<ClientEntity>();
        if (dtos != null) {
            for (ClientDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
