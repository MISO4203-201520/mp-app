package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.ClientConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.AppSourceEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppSourcePersistence;
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
public class AppSourcePersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AppSourceEntity.class.getPackage())
                .addPackage(AppSourcePersistence.class.getPackage())
                .addPackage(AppEntity.class.getPackage())
                .addPackage(AppDTO.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private AppSourcePersistence sourcePersistence;

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
        em.createQuery("delete from AppSourceEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<AppSourceEntity> data = new ArrayList<AppSourceEntity>();

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
            AppSourceEntity entity = new AppSourceEntity();
            entity.setApp(app);
            entity.setUrl("Random_url" + i);
            entity.setVersion("Random_version" + i);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createAppSourceTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AppSourceEntity newEntity = new AppSourceEntity();
        newEntity.setApp(data.get(0).getApp());
        newEntity.setUrl("Random_url");
        newEntity.setVersion("Random_version");

        AppSourceEntity result = sourcePersistence.create(newEntity);

        Assert.assertNotNull(result);

        AppSourceEntity entity = em.find(AppSourceEntity.class, result.getId());
        Assert.assertEquals(newEntity.getUrl(), entity.getUrl());
        Assert.assertEquals(newEntity.getVersion(), entity.getVersion());
    }

    /**
     * @generated
     */
    @Test
    public void getAppSourcesTest() {
        List<AppSourceEntity> list = sourcePersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (AppSourceEntity ent : list) {
            boolean found = false;
            for (AppSourceEntity entity : data) {
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
    public void getAppSourceTest() {
        AppSourceEntity entity = data.get(0);
        AppSourceEntity newEntity = sourcePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(newEntity.getUrl(), entity.getUrl());
        Assert.assertEquals(newEntity.getVersion(), entity.getVersion());
    }

    /**
     * @generated
     */
    @Test
    public void deleteAppSourceTest() {
        AppSourceEntity entity = data.get(0);
        sourcePersistence.delete(entity.getId());
        AppSourceEntity deleted = em.find(AppSourceEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateAppSourceTest() {
        AppSourceEntity entity = data.get(0);

        PodamFactory factory = new PodamFactoryImpl();
        AppSourceEntity newEntity = new AppSourceEntity();
        newEntity.setUrl("another_Random_url");
        newEntity.setVersion("another_Random_version");
        newEntity.setApp(entity.getApp());
        newEntity.setId(entity.getId());

        sourcePersistence.update(newEntity);

        AppSourceEntity resp = em.find(AppSourceEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getUrl(), resp.getUrl());
        Assert.assertEquals(newEntity.getVersion(), resp.getVersion());
    }

    /**
     * @generated
     */
    @Test
    public void getAppSourcePaginationTest() {
        //Page 1
        List<AppSourceEntity> ent1 = sourcePersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<AppSourceEntity> ent2 = sourcePersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (AppSourceEntity ent : ent1) {
            boolean found = false;
            for (AppSourceEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (AppSourceEntity ent : ent2) {
            boolean found = false;
            for (AppSourceEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
