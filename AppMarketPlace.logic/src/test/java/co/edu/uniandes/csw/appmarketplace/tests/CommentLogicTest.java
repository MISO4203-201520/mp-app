/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.api.IAdminLogic;
import co.edu.uniandes.csw.appmarketplace.api.ICommentLogic;
import co.edu.uniandes.csw.appmarketplace.converters.AdminConverter;
import co.edu.uniandes.csw.appmarketplace.converters.CommentConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.ejbs.CommentLogic;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
import co.edu.uniandes.csw.appmarketplace.entities.Comment;
import co.edu.uniandes.csw.appmarketplace.persistence.AdminPersistence;
import co.edu.uniandes.csw.appmarketplace.persistence.CommentPersistence;
import static co.edu.uniandes.csw.appmarketplace.tests.AdminLogicTest.DEPLOY;
import java.text.ParseException;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.patino10
 */
@RunWith(Arquillian.class)
public class CommentLogicTest {
    public static final String DEPLOY = "Prueba";
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(Comment.class.getPackage())
                .addPackage(CommentDTO.class.getPackage())
                .addPackage(CommentConverter.class.getPackage())
                .addPackage(CommentLogic.class.getPackage())
                .addPackage(ICommentLogic.class.getPackage())
                .addPackage(CommentPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private ICommentLogic commentLogic;
    
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
        em.createQuery("delete from Comment").executeUpdate();
    }
    private List<Comment> data = new ArrayList<Comment>();
    
    private void insertData() throws ParseException {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            Comment entity = CommentConverter.basicDTO2Entity(factory.manufacturePojo(CommentDTO.class));
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Test of InsertComment method, of class CommentLogic.
     */
    @Test
    public void testInsertComment() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        CommentDTO dto = factory.manufacturePojo(CommentDTO.class);
        CommentDTO result = commentLogic.InsertComment(dto);
        Assert.assertNotNull(result);
        Comment entity = em.find(Comment.class, result.getId());

        Assert.assertEquals(dto.getComment(), entity.getComment());
        Assert.assertEquals(dto.getId(), entity.getId());
    }
    
}
