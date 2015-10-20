/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.PaymentMethodConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.appmarketplace.ejbs.AppLogic;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentMethodEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppPersistence;
import static co.edu.uniandes.csw.appmarketplace.tests.AdminLogicTest.DEPLOY;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author af.esguerra10
 */
@RunWith(Arquillian.class)
public class PaymentMethodLogicTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AppEntity.class.getPackage())
                .addPackage(PaymentMethodDTO.class.getPackage())
                .addPackage(AppConverter.class.getPackage())
                .addPackage(AppLogic.class.getPackage())
                .addPackage(IAppLogic.class.getPackage())
                .addPackage(AppPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private IPaymentMethodLogic pmLogic;

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
        em.createQuery("delete from PaymentMethodEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<PaymentMethodEntity> data = new ArrayList<PaymentMethodEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PaymentMethodDTO pm = new PodamFactoryImpl().manufacturePojo(PaymentMethodDTO.class);
            PaymentMethodEntity entity = PaymentMethodConverter.fullDTO2Entity(pm);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createPMTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PaymentMethodDTO dto = factory.manufacturePojo(PaymentMethodDTO.class);
        PaymentMethodDTO result = pmLogic.createPaymentMethod(dto);
        Assert.assertNotNull(result);

        PaymentMethodEntity entity = em.find(PaymentMethodEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
    }

    @Test
    public void getPaymentMethodsTest() {
        List<PaymentMethodDTO> list = pmLogic.getPaymentMethods(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (PaymentMethodDTO dto : list) {
            boolean found = false;
            for (PaymentMethodEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getPaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        PaymentMethodDTO dto = pmLogic.getPaymentMethod(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deletePaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        pmLogic.deletePaymentMethod(entity.getId());
        PaymentMethodEntity deleted = em.find(PaymentMethodEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updatePaymentMethodTest() {
        PaymentMethodEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaymentMethodDTO dto = factory.manufacturePojo(PaymentMethodDTO.class);
        dto.setId(entity.getId());

        pmLogic.updatePaymentMethod(dto);

        PaymentMethodEntity resp = em.find(PaymentMethodEntity.class, entity.getId());

        Assert.assertEquals(dto.getName(), resp.getName());
    }

}
