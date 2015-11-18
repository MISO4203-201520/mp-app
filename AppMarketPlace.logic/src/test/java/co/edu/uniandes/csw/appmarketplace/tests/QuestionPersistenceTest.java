package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.ClientConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.QuestionEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.QuestionPersistence;
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
public class QuestionPersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(QuestionEntity.class.getPackage())
                .addPackage(QuestionPersistence.class.getPackage())
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
    private QuestionPersistence questionPersistence;

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
        em.createQuery("delete from QuestionEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<QuestionEntity> data = new ArrayList<QuestionEntity>();

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
            QuestionEntity entity = new QuestionEntity();
            entity.setClient(client);
            entity.setApp(app);
            entity.setDescription("Random description");
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createQuestionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        QuestionEntity newEntity = new QuestionEntity();
        newEntity.setClient(data.get(0).getClient());
        newEntity.setApp(data.get(0).getApp());
        newEntity.setDescription("Another description");
        
        QuestionEntity result = questionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        QuestionEntity entity = em.find(QuestionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getClient().getName(), entity.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), entity.getApp().getName());
    }

    /**
     * @generated
     */
    @Test
    public void getQuestionsTest() {
        List<QuestionEntity> list = questionPersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (QuestionEntity ent : list) {
            boolean found = false;
            for (QuestionEntity entity : data) {
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
    public void getQuestionTest() {
        QuestionEntity entity = data.get(0);
        QuestionEntity newEntity = questionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getClient().getName(), entity.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), entity.getApp().getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteQuestionTest() {
        QuestionEntity entity = data.get(0);
        questionPersistence.delete(entity.getId());
        QuestionEntity deleted = em.find(QuestionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateQuestionTest() {
        QuestionEntity entity = data.get(0);

        PodamFactory factory = new PodamFactoryImpl();
        QuestionEntity newEntity = new QuestionEntity();
        newEntity.setDescription("Another description");
        newEntity.setClient(entity.getClient());
        newEntity.setApp(entity.getApp());
        newEntity.setId(entity.getId());

        questionPersistence.update(newEntity);

        QuestionEntity resp = em.find(QuestionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getClient().getName(), resp.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), resp.getApp().getName());
    }

    /**
     * @generated
     */
    @Test
    public void getQuestionPaginationTest() {
        //Page 1
        List<QuestionEntity> ent1 = questionPersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<QuestionEntity> ent2 = questionPersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (QuestionEntity ent : ent1) {
            boolean found = false;
            for (QuestionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (QuestionEntity ent : ent2) {
            boolean found = false;
            for (QuestionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}