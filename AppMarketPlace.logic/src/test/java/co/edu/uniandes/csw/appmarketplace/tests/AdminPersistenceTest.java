package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.converters.AdminConverter;
import co.edu.uniandes.csw.appmarketplace.converters.ClientConverter;
import co.edu.uniandes.csw.appmarketplace.converters.DeveloperConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AdminPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.ClientPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.DeveloperPersistence;
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
 * @author d.jmenez13
 */
@RunWith(Arquillian.class)
public class AdminPersistenceTest {

    public static final String DEPLOY = "Prueba";

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AdminEntity.class.getPackage())
                .addPackage(DeveloperEntity.class.getPackage())
                .addPackage(ClientEntity.class.getPackage())
                .addPackage(AdminPersistence.class.getPackage())
                .addPackage(DeveloperPersistence.class.getPackage())
                .addPackage(ClientPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private AdminPersistence adminPersistence;

    @Inject
    private DeveloperPersistence developerPersistence;

    @Inject
    private ClientPersistence clientPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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

    private void clearData() {
        em.createQuery("delete from AdminEntity").executeUpdate();
        em.createQuery("delete from DeveloperEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
    }

    private List<AdminEntity> data = new ArrayList<AdminEntity>();
    private List<DeveloperEntity> developersData = new ArrayList<DeveloperEntity>();
    private List<ClientEntity> clientsData = new ArrayList<ClientEntity>();

    private void insertData() {
        // Inserting records for admin test
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            AdminEntity entity = AdminConverter.basicDTO2Entity(
                    factory.manufacturePojo(AdminDTO.class));
            em.persist(entity);
            data.add(entity);
        }
        // Inserting records for developers test via admin
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            DeveloperEntity entity = DeveloperConverter.basicDTO2Entity(
                    factory.manufacturePojo(DeveloperDTO.class));
            em.persist(entity);
            developersData.add(entity);
        }
        // Inserting records for developers test via admin
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            ClientEntity entity = ClientConverter.basicDTO2Entity(
                    factory.manufacturePojo(ClientDTO.class));
            em.persist(entity);
            clientsData.add(entity);
        }
    }

    @Test
    public void t00CreateAdmin() {
        PodamFactory factory = new PodamFactoryImpl();
        AdminEntity newEntity = AdminConverter.basicDTO2Entity(
                factory.manufacturePojo(AdminDTO.class));
        AdminEntity result = adminPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AdminEntity entity = em.find(AdminEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getUserId(), entity.getUserId());
    }

    @Test
    public void t02GetAdmins() {
        List<AdminEntity> list = adminPersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (AdminEntity ent : list) {
            boolean found = false;
            for (AdminEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void t04GetAdmin() {
        AdminEntity entity = data.get(0);
        AdminEntity newEntity = adminPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getUserId(), newEntity.getUserId());
    }

    @Test
    public void t06DeleteAdmin() {
        AdminEntity entity = data.get(0);
        adminPersistence.delete(entity.getId());
        AdminEntity deleted = em.find(AdminEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void t08UpdateAdmin() {
        AdminEntity entity = data.get(0);

        PodamFactory factory = new PodamFactoryImpl();
        AdminEntity newEntity = AdminConverter.basicDTO2Entity(
                factory.manufacturePojo(AdminDTO.class));
        newEntity.setId(entity.getId());

        adminPersistence.update(newEntity);

        AdminEntity resp = em.find(AdminEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getUserId(), resp.getUserId());
    }

    @Test
    public void t10GetAdminPagination() {
        //Page 1
        List<AdminEntity> ent1 = adminPersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<AdminEntity> ent2 = adminPersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (AdminEntity ent : ent1) {
            boolean found = false;
            for (AdminEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (AdminEntity ent : ent2) {
            boolean found = false;
            for (AdminEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void t12FindByName() {
        String name = data.get(0).getName();
        List<AdminEntity> cache = new ArrayList<AdminEntity>();
        List<AdminEntity> list = adminPersistence.findByName(name);

        for (AdminEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(list.size(), cache.size());
        for (AdminEntity ent : list) {
            boolean found = false;
            for (AdminEntity cacheEntity : cache) {
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
    public void t14GetAdminByUserId() {
        AdminEntity adminToSearch = data.get(0);

        AdminEntity adminFound = adminPersistence
                .getAdminByUserId(adminToSearch.getUserId());

        Assert.assertNotNull(adminFound);
        Assert.assertEquals(adminFound.getName(), adminToSearch.getName());
        Assert.assertEquals(adminFound.getUserId(), adminToSearch.getUserId());
    }

    @Test
    public void t16GetDevelopersViaAdmin() {
        List<DeveloperEntity> list = developerPersistence.findAll(null, null);
        Assert.assertEquals(developersData.size(), list.size());
        for (DeveloperEntity ent : list) {
            boolean found = false;
            for (DeveloperEntity entity : developersData) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void t18GetClientsViaAdmin() {
        List<ClientEntity> list = clientPersistence.findAll(null, null);
        Assert.assertEquals(clientsData.size(), list.size());
        for (ClientEntity ent : list) {
            boolean found = false;
            for (ClientEntity entity : clientsData) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
