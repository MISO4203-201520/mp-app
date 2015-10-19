package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.ejbs.AdminLogic;
import co.edu.uniandes.csw.appmarketplace.api.IAdminLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AdminConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AdminPersistence;
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
public class AdminLogicTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AdminEntity.class.getPackage())
                .addPackage(AdminDTO.class.getPackage())
                .addPackage(AdminConverter.class.getPackage())
                .addPackage(AdminLogic.class.getPackage())
                .addPackage(IAdminLogic.class.getPackage())
                .addPackage(AdminPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private IAdminLogic adminLogic;

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
        em.createQuery("delete from AdminEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<AdminEntity> data = new ArrayList<AdminEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            AdminEntity entity = AdminConverter.basicDTO2Entity(factory.manufacturePojo(AdminDTO.class));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createAdminTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AdminDTO dto = factory.manufacturePojo(AdminDTO.class);
        AdminDTO result = adminLogic.createAdmin(dto);
        Assert.assertNotNull(result);

        AdminEntity entity = em.find(AdminEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
        Assert.assertEquals(dto.getUserId(), entity.getUserId());
    }

    /**
     * @generated
     */
    @Test
    public void getAdminsTest() {
        List<AdminDTO> list = adminLogic.getAdmins(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (AdminDTO dto : list) {
            boolean found = false;
            for (AdminEntity entity : data) {
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
    public void getAdminTest() {
        AdminEntity entity = data.get(0);
        AdminDTO dto = adminLogic.getAdmin(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getUserId(), dto.getUserId());
    }

    /**
     * @generated
     */
    @Test
    public void deleteAdminTest() {
        AdminEntity entity = data.get(0);
        adminLogic.deleteAdmin(entity.getId());
        AdminEntity deleted = em.find(AdminEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateAdminTest() {
        AdminEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdminDTO dto = factory.manufacturePojo(AdminDTO.class);
        dto.setId(entity.getId());
        
        adminLogic.updateAdmin(dto);

        AdminEntity resp = em.find(AdminEntity.class, entity.getId());

        Assert.assertEquals(dto.getName(), resp.getName());
        Assert.assertEquals(dto.getUserId(), resp.getUserId());
    }

    /**
     * @generated
     */
    @Test
    public void getAdminPaginationTest() {
        //Page 1
        List<AdminDTO> dto1 = adminLogic.getAdmins(1, 2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(2, dto1.size());
        //Page 2
        List<AdminDTO> dto2 = adminLogic.getAdmins(2, 2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(1, dto2.size());

        for (AdminDTO dto : dto1) {
            boolean found = false;
            for (AdminEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (AdminDTO dto : dto2) {
            boolean found = false;
            for (AdminEntity entity : data) {
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
        List<AdminEntity> cache = new ArrayList<AdminEntity>();
        List<AdminDTO> list = adminLogic.findByName(name);

        for (AdminEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(cache.size(), list.size());
        for (AdminDTO dto : list) {
            boolean found = false;
            for (AdminEntity cacheEntity : cache) {
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

    @Test
    public void getAdminByUserId() {
        AdminEntity searchedAdmin = data.get(0);

        AdminDTO foundAdmin = adminLogic
                .getAdminByUserId(searchedAdmin.getUserId());

        Assert.assertNotNull(foundAdmin);
        Assert.assertEquals(foundAdmin.getName(), searchedAdmin.getName());
        Assert.assertEquals(foundAdmin.getUserId(), searchedAdmin.getUserId());
    }
}