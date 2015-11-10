/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.appmarketplace.entities.QuestionEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jd.patino10
 */
public class QuestionConverterTest {
    
    public QuestionConverterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of basicDTO2Entity method, of class QuestionConverter.
     */
    @Test
    public void testBasicDTO2Entity() throws Exception {
        System.out.println("basicDTO2Entity");
        QuestionDTO dto = new QuestionDTO();
        QuestionEntity expResult = null;
        QuestionEntity result = QuestionConverter.basicDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is aassertNull(null); prototype.");
    }

    /**
     * Test of basicEntity2DTO method, of class QuestionConverter.
     */
    @Test
    public void testBasicEntity2DTO() throws Exception {
        System.out.println("basicEntity2DTO");
        QuestionEntity entity = new QuestionEntity();
        QuestionDTO expResult = null;
        QuestionDTO result = QuestionConverter.basicEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
