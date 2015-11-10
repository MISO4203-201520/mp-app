package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.ClientConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.RateDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.RateEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.RatePersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class RatePersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(RateEntity.class.getPackage())
                .addPackage(RatePersistence.class.getPackage())
                .addPackage(ClientEntity.class.getPackage())
                .addPackage(ClientDTO.class.getPackage())
                .addPackage(AppEntity.class.getPackage())
                .addPackage(AppDTO.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private RatePersistence ratePersistence;

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
        em.createQuery("delete from RateEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<RateEntity> data = new ArrayList<RateEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        ClientEntity client = ClientConverter.basicDTO2Entity(factory.manufacturePojo(ClientDTO.class));
        em.persist(client);
        AppEntity app = AppConverter.basicDTO2Entity(factory.manufacturePojo(AppDTO.class));
        em.persist(app);
        for (int i = 0; i < 3; i++) {
            RateEntity entity = new RateEntity();
            entity.setClient(client);
            entity.setApp(app);
            entity.setRate(0L);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createRateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RateEntity newEntity = new RateEntity();
        newEntity.setClient(data.get(0).getClient());
        newEntity.setApp(data.get(0).getApp());
        newEntity.setRate(3L);
        
        RateEntity result = ratePersistence.create(newEntity);

        Assert.assertNotNull(result);

        RateEntity entity = em.find(RateEntity.class, result.getId());

        Assert.assertEquals(newEntity.getClient().getName(), entity.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), entity.getApp().getName());
    }

    /**
     * @generated
     */
    @Test
    public void getRatesTest() {
        List<RateEntity> list = ratePersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (RateEntity ent : list) {
            boolean found = false;
            for (RateEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
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
    public void getRateTest() {
        RateEntity entity = data.get(0);
        RateEntity newEntity = ratePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getClient().getName(), entity.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), entity.getApp().getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteRateTest() {
        RateEntity entity = data.get(0);
        ratePersistence.delete(entity.getId());
        RateEntity deleted = em.find(RateEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateRateTest() {
        RateEntity entity = data.get(0);

        PodamFactory factory = new PodamFactoryImpl();
        RateEntity newEntity = new RateEntity();
        newEntity.setRate(4L);
        newEntity.setClient(entity.getClient());
        newEntity.setApp(entity.getApp());
        newEntity.setId(entity.getId());

        ratePersistence.update(newEntity);

        RateEntity resp = em.find(RateEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getClient().getName(), resp.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), resp.getApp().getName());
    }

    /**
     * @generated
     */
    @Test
    public void getRatePaginationTest() {
        //Page 1
        List<RateEntity> ent1 = ratePersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<RateEntity> ent2 = ratePersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (RateEntity ent : ent1) {
            boolean found = false;
            for (RateEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (RateEntity ent : ent2) {
            boolean found = false;
            for (RateEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}