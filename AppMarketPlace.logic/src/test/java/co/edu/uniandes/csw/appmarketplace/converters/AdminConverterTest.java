/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.entities.AdminEntity;
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
public class AdminConverterTest {
    
    public AdminConverterTest() {
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
     * Test of refEntity2DTO method, of class AdminConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        AdminEntity entity = new AdminEntity();
        AdminDTO expResult = null;
        AdminDTO result = AdminConverter.refEntity2DTO(entity);
        //assertEquals(expResult, result);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class AdminConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        AdminDTO dto = new AdminDTO();
        AdminEntity expResult = null;
        AdminEntity result = AdminConverter.refDTO2Entity(dto);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertNull(null);
    }

    /**
     * Test of basicEntity2DTO method, of class AdminConverter.
     */
    @Test
    public void testBasicEntity2DTO() {
        System.out.println("basicEntity2DTO");
        AdminEntity entity = new AdminEntity();
        AdminDTO expResult = null;
        AdminDTO result = AdminConverter.basicEntity2DTO(entity);
        //assertEquals(expResult, result);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of basicDTO2Entity method, of class AdminConverter.
     */
    @Test
    public void testBasicDTO2Entity() {
        System.out.println("basicDTO2Entity");
        AdminDTO dto = new AdminDTO();
        AdminEntity expResult = null;
        AdminEntity result = AdminConverter.basicDTO2Entity(dto);
        //assertEquals(expResult, result);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullEntity2DTO method, of class AdminConverter.
     */
    @Test
    public void testFullEntity2DTO() {
        System.out.println("fullEntity2DTO");
        AdminEntity entity = new AdminEntity();
        AdminDTO expResult = null;
        AdminDTO result = AdminConverter.fullEntity2DTO(entity);
        //assertEquals(expResult, result);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullDTO2Entity method, of class AdminConverter.
     */
    @Test
    public void testFullDTO2Entity() {
        System.out.println("fullDTO2Entity");
        AdminDTO dto = new AdminDTO();
        AdminEntity expResult = null;
        AdminEntity result = AdminConverter.fullDTO2Entity(dto);
        //assertEquals(expResult, result);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class AdminConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<AdminEntity> entities = null;
        List<AdminDTO> expResult = new ArrayList();
        List<AdminDTO> result = AdminConverter.listEntity2DTO(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listDTO2Entity method, of class AdminConverter.
     */
    @Test
    public void testListDTO2Entity() {
        System.out.println("listDTO2Entity");
        List<AdminDTO> dtos = null;
        List<AdminEntity> expResult = new ArrayList();
        List<AdminEntity> result = AdminConverter.listDTO2Entity(dtos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
