package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author d.jmenez13
 */
@RunWith(Arquillian.class)
public class AppPersistenceTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AppEntity.class.getPackage())
                .addPackage(AppPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private AppPersistence appPersistence;

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
        em.createQuery("delete from AppEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<AppEntity> data = new ArrayList<AppEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AppEntity entity = new AppEntity();
            entity.setName(generateRandom(String.class));
            entity.setCategory(generateRandom(String.class));
            entity.setDescription(generateRandom(String.class));
            entity.setVersion(generateRandom(String.class));
            entity.setPicture(generateRandom(String.class));
            entity.setPrice(generateRandom(Integer.class));
            entity.setSize(generateRandom(Integer.class));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createAppTest() {
        AppEntity newEntity = new AppEntity();
        newEntity.setName(generateRandom(String.class));
        newEntity.setCategory(generateRandom(String.class));
        newEntity.setDescription(generateRandom(String.class));
        newEntity.setVersion(generateRandom(String.class));
        newEntity.setPicture(generateRandom(String.class));
        newEntity.setPrice(generateRandom(Integer.class));
        newEntity.setSize(generateRandom(Integer.class));

        AppEntity result = appPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AppEntity entity = em.find(AppEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCategory(), entity.getCategory());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getVersion(), entity.getVersion());
        Assert.assertEquals(newEntity.getPicture(), entity.getPicture());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
        Assert.assertEquals(newEntity.getSize(), entity.getSize());
    }

    /**
     * @generated
     */
    @Test
    public void getAppsTest() {
        List<AppEntity> list = appPersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (AppEntity ent : list) {
            boolean found = false;
            for (AppEntity entity : data) {
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
    public void getAppTest() {
        AppEntity entity = data.get(0);
        AppEntity newEntity = appPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCategory(), newEntity.getCategory());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getVersion(), newEntity.getVersion());
        Assert.assertEquals(entity.getPicture(), newEntity.getPicture());
        Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
        Assert.assertEquals(entity.getSize(), newEntity.getSize());
    }

    /**
     * @generated
     */
    @Test
    public void deleteAppTest() {
        AppEntity entity = data.get(0);
        appPersistence.delete(entity.getId());
        AppEntity deleted = em.find(AppEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateAppTest() {
        AppEntity entity = data.get(0);

        AppEntity newEntity = new AppEntity();

        newEntity.setId(entity.getId());
        newEntity.setName(generateRandom(String.class));
        newEntity.setCategory(generateRandom(String.class));
        newEntity.setDescription(generateRandom(String.class));
        newEntity.setVersion(generateRandom(String.class));
        newEntity.setPicture(generateRandom(String.class));
        newEntity.setPrice(generateRandom(Integer.class));
        newEntity.setSize(generateRandom(Integer.class));

        appPersistence.update(newEntity);

        AppEntity resp = em.find(AppEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCategory(), resp.getCategory());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(newEntity.getVersion(), resp.getVersion());
        Assert.assertEquals(newEntity.getPicture(), resp.getPicture());
        Assert.assertEquals(newEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(newEntity.getSize(), resp.getSize());
    }

    /**
     * @generated
     */
    @Test
    public void getAppPaginationTest() {
        //Page 1
        List<AppEntity> ent1 = appPersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<AppEntity> ent2 = appPersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (AppEntity ent : ent1) {
            boolean found = false;
            for (AppEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (AppEntity ent : ent2) {
            boolean found = false;
            for (AppEntity entity : data) {
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
    public void findByName() {
        String name = data.get(0).getName();
        List<AppEntity> cache = new ArrayList<AppEntity>();
        List<AppEntity> list = appPersistence.findByName(name);

        for (AppEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(list.size(), cache.size());
        for (AppEntity ent : list) {
            boolean found = false;
            for (AppEntity cacheEntity : cache) {
                if (cacheEntity.getName().equals(ent.getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail();
            }
        }
    }
    
    @Test
    public void getAppsByCategory() {
        String category = data.get(0).getCategory();
        List<AppEntity> cachedApps = new ArrayList<AppEntity>();
        List<AppEntity> foundApps = appPersistence.getAppsByCategory(category);
        
        for (AppEntity app : data) {
            if (app.getCategory().equals(category)) {
                cachedApps.add(app);
            }
        }
        Assert.assertEquals(cachedApps.size(), foundApps.size());
        
        for (AppEntity foundApp : foundApps) {
            boolean found = false;
            for (AppEntity cachedApp : cachedApps) {
                if (cachedApp.getName().equals(foundApp.getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail();
            }
        }
    }
}
