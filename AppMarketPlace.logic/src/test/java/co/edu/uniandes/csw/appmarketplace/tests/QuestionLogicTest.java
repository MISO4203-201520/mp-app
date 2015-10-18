/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.tests;

import co.edu.uniandes.csw.appmarketplace.api.IQuestionLogic;
import co.edu.uniandes.csw.appmarketplace.converters.QuestionConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.appmarketplace.ejbs.QuestionLogic;
import co.edu.uniandes.csw.appmarketplace.entities.QuestionEntity;
import co.edu.uniandes.csw.appmarketplace.persistence.QuestionPersistence;
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
public class QuestionLogicTest {
    public static final String DEPLOY = "Prueba";
    public QuestionLogicTest() {
    }
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(QuestionEntity.class.getPackage())
                .addPackage(QuestionDTO.class.getPackage())
                .addPackage(QuestionConverter.class.getPackage())
                .addPackage(QuestionLogic.class.getPackage())
                .addPackage(IQuestionLogic.class.getPackage())
                .addPackage(QuestionPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private IQuestionLogic questionLogic;
    
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
        em.createQuery("delete from QuestionEntity").executeUpdate();
    }
    private List<QuestionEntity> data = new ArrayList<QuestionEntity>();
    
    private void insertData() throws ParseException {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            QuestionEntity  entity = QuestionConverter.basicDTO2Entity(factory.manufacturePojo(QuestionDTO.class));
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Test of doQuestion method, of class QuestionLogic.
     */
    @Test
    public void testDoQuestion() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        QuestionDTO dto = factory.manufacturePojo(QuestionDTO.class);        
        DeveloperDTO dto2 = factory.manufacturePojo(DeveloperDTO.class);
        AppDTO dto3 = factory.manufacturePojo(AppDTO.class);
        QuestionDTO result = questionLogic.doQuestion(dto,dto2,dto3,"jd.patino10@uniandes.edu.co");
        Assert.assertNotNull(result);
        QuestionEntity entity = em.find(QuestionEntity.class, result.getId());

        Assert.assertEquals(dto.getDescription(), entity.getDescription());
        Assert.assertEquals(dto.getEmail(), entity.getEmail());
        Assert.assertEquals(dto.getId(), entity.getId());
    }
    
}
