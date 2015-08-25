package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.ejbs.DeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.converters.DeveloperConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.DeveloperPersistence;
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
 * @generated
 */
@RunWith(Arquillian.class)
public class DeveloperLogicTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(DeveloperEntity.class.getPackage())
                .addPackage(DeveloperDTO.class.getPackage())
                .addPackage(DeveloperConverter.class.getPackage())
                .addPackage(DeveloperLogic.class.getPackage())
                .addPackage(IDeveloperLogic.class.getPackage())
                .addPackage(DeveloperPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private IDeveloperLogic developerLogic;

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
        em.createQuery("delete from DeveloperEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<DeveloperEntity> data = new ArrayList<DeveloperEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            DeveloperEntity entity = new DeveloperEntity();
        	entity.setName(generateRandom(String.class));
        	entity.setUserId(generateRandom(String.class));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createDeveloperTest() {
        DeveloperDTO dto = new DeveloperDTO();
        dto.setName(generateRandom(String.class));
        dto.setUserId(generateRandom(String.class));

        DeveloperDTO result = developerLogic.createDeveloper(dto);

        Assert.assertNotNull(result);

        DeveloperEntity entity = em.find(DeveloperEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
        Assert.assertEquals(dto.getUserId(), entity.getUserId());
    }

    /**
     * @generated
     */
    @Test
    public void getDevelopersTest() {
        List<DeveloperDTO> list = developerLogic.getDevelopers(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (DeveloperDTO dto : list) {
            boolean found = false;
            for (DeveloperEntity entity : data) {
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
    public void getDeveloperTest() {
        DeveloperEntity entity = data.get(0);
        DeveloperDTO dto = developerLogic.getDeveloper(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getUserId(), dto.getUserId());
    }

    /**
     * @generated
     */
    @Test
    public void deleteDeveloperTest() {
        DeveloperEntity entity = data.get(0);
        developerLogic.deleteDeveloper(entity.getId());
        DeveloperEntity deleted = em.find(DeveloperEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateDeveloperTest() {
        DeveloperEntity entity = data.get(0);

        DeveloperDTO dto = new DeveloperDTO();

        dto.setId(entity.getId());
        dto.setName(generateRandom(String.class));
        dto.setUserId(generateRandom(String.class));

        developerLogic.updateDeveloper(dto);

        DeveloperEntity resp = em.find(DeveloperEntity.class, entity.getId());

        Assert.assertEquals(dto.getName(), resp.getName());
        Assert.assertEquals(dto.getUserId(), resp.getUserId());
    }

    /**
     * @generated
     */
    @Test
    public void getDeveloperPaginationTest() {
        //Page 1
        List<DeveloperDTO> dto1 = developerLogic.getDevelopers(1, 2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(2, dto1.size());
        //Page 2
        List<DeveloperDTO> dto2 = developerLogic.getDevelopers(2, 2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(1, dto2.size());

        for (DeveloperDTO dto : dto1) {
            boolean found = false;
            for (DeveloperEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (DeveloperDTO dto : dto2) {
            boolean found = false;
            for (DeveloperEntity entity : data) {
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
    public void findByName() {
        String name = data.get(0).getName();
        List<DeveloperEntity> cache = new ArrayList<DeveloperEntity>();
        List<DeveloperDTO> list = developerLogic.findByName(name);

        for (DeveloperEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(cache.size(), list.size());
        for (DeveloperDTO dto : list) {
            boolean found = false;
            for (DeveloperEntity cacheEntity : cache) {
                if (cacheEntity.getName().equals(dto.getName())) {
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
