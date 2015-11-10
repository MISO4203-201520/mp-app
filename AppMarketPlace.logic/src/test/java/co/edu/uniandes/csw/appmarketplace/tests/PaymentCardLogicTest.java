/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.api.IPaymentCardLogic;
import co.edu.uniandes.csw.appmarketplace.converters.PaymentCardConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentCardDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.appmarketplace.ejbs.PaymentCardLogic;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentCardEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.PaymentCardPersistence;
import static co.edu.uniandes.csw.appmarketplace.tests._TestUtil.*;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.patino10
 */
@RunWith(Arquillian.class)
public class PaymentCardLogicTest {
    public static final String DEPLOY = "Prueba";
    public PaymentCardLogicTest() {
    }
    
    
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(PaymentCardEntity.class.getPackage())
                .addPackage(PaymentCardDTO.class.getPackage())
                .addPackage(PaymentCardConverter.class.getPackage())
                .addPackage(PaymentCardLogic.class.getPackage())
                .addPackage(IPaymentCardLogic.class.getPackage())
                .addPackage(PaymentCardPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * @generated
     */
    @Inject
    private IPaymentCardLogic paymentCardLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from PaymentCardEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<PaymentCardEntity> data = new ArrayList<PaymentCardEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            PaymentCardEntity entity = factory.manufacturePojo(PaymentCardEntity.class);
            entity.setId(i+1l);
            em.persist(entity);
            data.add(entity);
        }
    }

    
    /**
     * Test of createPaymentCards method, of class PaymentCardLogic.
     */
    @Test
    public void testCreatePaymentCards() {
        PodamFactory factory = new PodamFactoryImpl();
        PaymentCardDTO dto = factory.manufacturePojo(PaymentCardDTO.class);
        PaymentCardDTO result = paymentCardLogic.createPaymentCards(dto);
        Assert.assertNotNull(result);

        PaymentCardEntity entity = em.find(PaymentCardEntity.class, result.getId());

        Assert.assertEquals(dto.getCardnumber(), entity.getCardnumber());
    }
    /**
     * Test of getPaymentCards method, of class PaymentCardLogic.
     */
    @Test
    public void testGetPaymentCards_3args() {
        List<PaymentCardDTO> result = paymentCardLogic.getPaymentCards(data.get(0).getId(), 1, 1);
        for (PaymentCardDTO dto : result) {
            boolean found = false;
            for (PaymentCardEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getPaymentCards method, of class PaymentCardLogic.
     */
    @Test
    public void testGetPaymentCards_Long() {
        PaymentCardDTO result = paymentCardLogic.getPaymentCards(data.get(0).getId());
        Assert.assertEquals(data.get(0).getCardnumber(), result.getCardnumber());
    }

    

    /**
     * Test of updatePaymentCards method, of class PaymentCardLogic.
     */
    @Test
    public void testUpdatePaymentCards() {
        PaymentCardEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaymentCardDTO dto = factory.manufacturePojo(PaymentCardDTO.class);
        dto.setId(entity.getId());
        paymentCardLogic.updatePaymentCards(dto);

        PaymentCardEntity res = em.find(PaymentCardEntity.class, entity.getId());

        Assert.assertEquals(dto.getCardnumber(), res.getCardnumber());
    }

    /**
     * Test of deletePaymentCards method, of class PaymentCardLogic.
     */
    @Test
    public void testDeletePaymentCards() {
        PaymentCardEntity entity = data.get(0);
        paymentCardLogic.deletePaymentCards(entity.getId());
        PaymentCardEntity deleted = em.find(PaymentCardEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

 
    
}
