package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.CartItemEntity;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class CartItemPersistence extends CrudPersistence<CartItemEntity> {

    /**
     * @generated
     */
    public CartItemPersistence() {
        this.entityClass = CartItemEntity.class;
    }
}
