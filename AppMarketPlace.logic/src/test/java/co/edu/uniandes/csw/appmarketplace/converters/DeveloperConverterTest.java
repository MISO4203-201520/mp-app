/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.entities.DeveloperEntity;
import java.util.ArrayList;
import java.util.List;
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
public class DeveloperConverterTest {
    
    public DeveloperConverterTest() {
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
     * Test of refEntity2DTO method, of class DeveloperConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        DeveloperEntity entity = null;
        DeveloperDTO expResult = null;
        DeveloperDTO result = DeveloperConverter.refEntity2DTO(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class DeveloperConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        DeveloperDTO dto = null;
        DeveloperEntity expResult = null;
        DeveloperEntity result = DeveloperConverter.refDTO2Entity(dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of basicEntity2DTO method, of class DeveloperConverter.
     */
    @Test
    public void testBasicEntity2DTO() {
        System.out.println("basicEntity2DTO");
        DeveloperEntity entity = null;
        DeveloperDTO expResult = null;
        DeveloperDTO result = DeveloperConverter.basicEntity2DTO(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of basicDTO2Entity method, of class DeveloperConverter.
     */
    @Test
    public void testBasicDTO2Entity() {
        System.out.println("basicDTO2Entity");
        DeveloperDTO dto = null;
        DeveloperEntity expResult = null;
        DeveloperEntity result = DeveloperConverter.basicDTO2Entity(dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullEntity2DTO method, of class DeveloperConverter.
     */
    @Test
    public void testFullEntity2DTO() {
        System.out.println("fullEntity2DTO");
        DeveloperEntity entity = null;
        DeveloperDTO expResult = null;
        DeveloperDTO result = DeveloperConverter.fullEntity2DTO(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullDTO2Entity method, of class DeveloperConverter.
     */
    @Test
    public void testFullDTO2Entity() {
        System.out.println("fullDTO2Entity");
        DeveloperDTO dto = null;
        DeveloperEntity expResult = null;
        DeveloperEntity result = DeveloperConverter.fullDTO2Entity(dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class DeveloperConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<DeveloperEntity> entities = null;
        List<DeveloperDTO> expResult = new ArrayList();
        List<DeveloperDTO> result = DeveloperConverter.listEntity2DTO(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listDTO2Entity method, of class DeveloperConverter.
     */
    @Test
    public void testListDTO2Entity() {
        System.out.println("listDTO2Entity");
        List<DeveloperDTO> dtos = null;
        List<DeveloperEntity> expResult = new ArrayList();
        List<DeveloperEntity> result = DeveloperConverter.listDTO2Entity(dtos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
