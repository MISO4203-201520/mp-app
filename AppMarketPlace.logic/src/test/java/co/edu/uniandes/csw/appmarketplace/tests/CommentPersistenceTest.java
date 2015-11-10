package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.converters.AppConverter;
import co.edu.uniandes.csw.appmarketplace.converters.ClientConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.appmarketplace.entities.Comment;
import co.edu.uniandes.csw.appmarketplace.persistence.CommentPersistence;
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
 * @genecommentd
 */
@RunWith(Arquillian.class)
public class CommentPersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @genecommentd
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(Comment.class.getPackage())
                .addPackage(CommentPersistence.class.getPackage())
                .addPackage(ClientEntity.class.getPackage())
                .addPackage(ClientDTO.class.getPackage())
                .addPackage(AppEntity.class.getPackage())
                .addPackage(AppDTO.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @genecommentd
     */
    @Inject
    private CommentPersistence commentPersistence;

    /**
     * @genecommentd
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @genecommentd
     */
    @Inject
    UserTransaction utx;

    /**
     * @genecommentd
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
     * @genecommentd
     */
    private void clearData() {
        em.createQuery("delete from Comment").executeUpdate();
    }

    /**
     * @genecommentd
     */
    private List<Comment> data = new ArrayList<Comment>();

    /**
     * @genecommentd
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        ClientEntity client = ClientConverter.basicDTO2Entity(factory.manufacturePojo(ClientDTO.class));
        em.persist(client);
        AppEntity app = AppConverter.basicDTO2Entity(factory.manufacturePojo(AppDTO.class));
        em.persist(app);
        for (int i = 0; i < 3; i++) {
            Comment entity = new Comment();
            entity.setClient(client);
            entity.setApp(app);
            entity.setComment("Random");
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @genecommentd
     */
    @Test
    public void createCommentTest() {
        PodamFactory factory = new PodamFactoryImpl();
        Comment newEntity = new Comment();
        newEntity.setClient(data.get(0).getClient());
        newEntity.setApp(data.get(0).getApp());
        newEntity.setComment("Comentario de prueba");
        
        Comment result = commentPersistence.create(newEntity);

        Assert.assertNotNull(result);

        Comment entity = em.find(Comment.class, result.getId());

        Assert.assertEquals(newEntity.getClient().getName(), entity.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), entity.getApp().getName());
    }

    /**
     * @genecommentd
     */
    @Test
    public void getCommentsTest() {
        List<Comment> list = commentPersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (Comment ent : list) {
            boolean found = false;
            for (Comment entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @genecommentd
     */
    @Test
    public void getCommentTest() {
        Comment entity = data.get(0);
        Comment newEntity = commentPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getClient().getName(), entity.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), entity.getApp().getName());
    }

    /**
     * @genecommentd
     */
    @Test
    public void deleteCommentTest() {
        Comment entity = data.get(0);
        commentPersistence.delete(entity.getId());
        Comment deleted = em.find(Comment.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @genecommentd
     */
    @Test
    public void updateCommentTest() {
        Comment entity = data.get(0);

        PodamFactory factory = new PodamFactoryImpl();
        Comment newEntity = new Comment();
        newEntity.setComment("Another comment");
        newEntity.setClient(entity.getClient());
        newEntity.setApp(entity.getApp());
        newEntity.setId(entity.getId());

        commentPersistence.update(newEntity);

        Comment resp = em.find(Comment.class, entity.getId());

        Assert.assertEquals(newEntity.getClient().getName(), resp.getClient().getName());
        Assert.assertEquals(newEntity.getApp().getName(), resp.getApp().getName());
    }

    /**
     * @genecommentd
     */
    @Test
    public void getCommentPaginationTest() {
        //Page 1
        List<Comment> ent1 = commentPersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<Comment> ent2 = commentPersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (Comment ent : ent1) {
            boolean found = false;
            for (Comment entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (Comment ent : ent2) {
            boolean found = false;
            for (Comment entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}