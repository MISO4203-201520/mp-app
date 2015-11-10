package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.ejbs.AppLogic;
import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.DeveloperConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.MediaDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.AppPersistence;
import static co.edu.uniandes.csw.appmarketplace.tests._TestUtil.*;
import java.util.ArrayList;
import java.util.Iterator;
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
public class AppLogicTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AppEntity.class.getPackage())
                .addPackage(AppDTO.class.getPackage())
                .addPackage(AppConverter.class.getPackage())
                .addPackage(AppLogic.class.getPackage())
                .addPackage(IAppLogic.class.getPackage())
                .addPackage(AppPersistence.class.getPackage())
                .addPackage(DeveloperEntity.class.getPackage())
                .addPackage(DeveloperDTO.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private IAppLogic appLogic;

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
        em.createQuery("delete from AppImageEntity").executeUpdate();
        em.createQuery("delete from AppVideoEntity").executeUpdate();
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
        PodamFactory factory = new PodamFactoryImpl();
        DeveloperEntity dev = DeveloperConverter.basicDTO2Entity(factory.manufacturePojo(DeveloperDTO.class));
        em.persist(dev);
        for (int i = 0; i < 3; i++) {
            AppEntity entity = new AppEntity();
        	entity.setName(generateRandom(String.class));
        	entity.setDescription(generateRandom(String.class));
        	entity.setVersion(generateRandom(String.class));
        	entity.setPicture(generateRandom(String.class));
        	entity.setPrice(generateRandom(Integer.class));
        	entity.setSize(generateRandom(Integer.class));
                entity.setCategory(generateRandom(String.class));
                entity.setIssueUrl(generateRandom(String.class));
                entity.setDeveloper(dev);
            em.persist(entity);
            data.add(entity);
        }
        
    }

    /**
     * @generated
     */
    @Test
    public void createAppTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AppDTO dto = factory.manufacturePojo(AppDTO.class);
        AppDTO result = appLogic.createApp(dto);
        Assert.assertNotNull(result);

        AppEntity entity = em.find(AppEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
        Assert.assertEquals(dto.getCategory(), entity.getCategory());
        Assert.assertEquals(dto.getDescription(), entity.getDescription());
        Assert.assertEquals(dto.getVersion(), entity.getVersion());
        Assert.assertEquals(dto.getPicture(), entity.getPicture());
        Assert.assertEquals(dto.getPrice(), entity.getPrice());
        Assert.assertEquals(dto.getSize(), entity.getSize());
        Assert.assertEquals(dto.getIssueUrl(), entity.getIssueUrl());
    }

    /**
     * @generated
     */
    @Test
    public void getAppsTest() {
        List<AppDTO> list = appLogic.getApps(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (AppDTO dto : list) {
            boolean found = false;
            for (AppEntity entity : data) {
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
    public void getAppTest() {
        AppEntity entity = data.get(0);
        AppDTO dto = appLogic.getApp(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getCategory(), dto.getCategory());
        Assert.assertEquals(entity.getDescription(), dto.getDescription());
        Assert.assertEquals(entity.getVersion(), dto.getVersion());
        Assert.assertEquals(entity.getPicture(), dto.getPicture());
        Assert.assertEquals(entity.getPrice(), dto.getPrice());
        Assert.assertEquals(entity.getSize(), dto.getSize());
        Assert.assertEquals(entity.getIssueUrl(), dto.getIssueUrl());
    }

    /**
     * @generated
     */
    @Test
    public void deleteAppTest() {
        AppEntity entity = data.get(0);
        appLogic.deleteApp(entity.getId());
        AppEntity deleted = em.find(AppEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateAppTest() {
        AppEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AppDTO dto = factory.manufacturePojo(AppDTO.class);
        dto.setId(entity.getId());

        appLogic.updateApp(dto);

        AppEntity resp = em.find(AppEntity.class, entity.getId());

        Assert.assertEquals(dto.getName(), resp.getName());
        Assert.assertEquals(dto.getCategory(), resp.getCategory());
        Assert.assertEquals(dto.getDescription(), resp.getDescription());
        Assert.assertEquals(dto.getVersion(), resp.getVersion());
        Assert.assertEquals(dto.getPicture(), resp.getPicture());
        Assert.assertEquals(dto.getPrice(), resp.getPrice());
        Assert.assertEquals(dto.getSize(), resp.getSize());
        Assert.assertEquals(dto.getIssueUrl(), resp.getIssueUrl());
    }

    /**
     * @generated
     */
    @Test
    public void getAppPaginationTest() {
        //Page 1
        List<AppDTO> dto1 = appLogic.getApps(1, 2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(2, dto1.size());
        //Page 2
        List<AppDTO> dto2 = appLogic.getApps(2, 2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(1, dto2.size());

        for (AppDTO dto : dto1) {
            boolean found = false;
            for (AppEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (AppDTO dto : dto2) {
            boolean found = false;
            for (AppEntity entity : data) {
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
        List<AppEntity> cache = new ArrayList<AppEntity>();
        List<AppDTO> list = appLogic.findByName(name);

        for (AppEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(cache.size(), list.size());
        for (AppDTO dto : list) {
            boolean found = false;
            for (AppEntity cacheEntity : cache) {
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
    public void getCheapest() {
        String developerName = data.get(0).getDeveloper().getName();
        AppEntity less = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            AppEntity current = data.get(i);
            if (current.getPrice() < less.getPrice() && current.getDeveloper().getName().equals(developerName)) {
                less = data.get(i);
            }
        }
        List<AppDTO> entity = appLogic.getCheapest(developerName);
        Iterator<AppDTO> iterator = entity.iterator();
        
        while(iterator.hasNext()){
            AppDTO current = iterator.next();
            Assert.assertEquals(current.getPrice(), less.getPrice());
        }
    }
    
    public void getAppsByCategory() {
        String category = data.get(0).getCategory();
        List<AppEntity> cachedApps = new ArrayList<AppEntity>();
        List<AppDTO> foundApps = appLogic.getAppsByCategory(category);

        for (AppEntity app : data) {
            if (app.getCategory().equals(category)) {
                cachedApps.add(app);
            }
        }
        Assert.assertEquals(cachedApps.size(), foundApps.size());

        for (AppDTO foundApp : foundApps) {
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

    @Test
    public void getAppsByKeyWords() {
        String keyword = data.get(0).getCategory();

        List<AppEntity> cachedApps = new ArrayList<AppEntity>();
        List<AppDTO> foundApps = appLogic.getAppsByKeyWords(keyword);

        for (AppEntity app : data) {
            if (app.getCategory().contains(keyword) || app.getName().contains(keyword) || app.getDescription().contains(keyword)) {
                cachedApps.add(app);
            }
        }
        Assert.assertEquals(cachedApps.size(), foundApps.size());

        for (AppDTO foundApp : foundApps) {
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

    @Test
    public void addImageToApp() {
        AppEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MediaDTO media = factory.manufacturePojo(MediaDTO.class);
        appLogic.addImage(entity.getId(), media.getUrl(), media.getMimetype());
        AppEntity app = em.find(AppEntity.class, entity.getId());
        Assert.assertTrue(app.getImages().size()> 0);
    }
    
    @Test
    public void addVideoToApp() {
        System.out.println(data.size());
        AppEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MediaDTO media = factory.manufacturePojo(MediaDTO.class);
        appLogic.addVideo(entity.getId(), media.getUrl(), media.getMimetype());
        AppEntity app = em.find(AppEntity.class, entity.getId());
        Assert.assertTrue(app.getVideos().size()> 0);
    }
}
