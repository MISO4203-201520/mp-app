/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

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
public class RateDTOTest {
    
    public RateDTOTest() {
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
     * Test of getId method, of class RateDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        RateDTO instance = new RateDTO();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class RateDTO.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        RateDTO instance = new RateDTO();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getRate method, of class RateDTO.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        RateDTO instance = new RateDTO();
        Long expResult = null;
        Long result = instance.getRate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setRate method, of class RateDTO.
     */
    @Test
    public void testSetRate() {
        System.out.println("setRate");
        Long rate = null;
        RateDTO instance = new RateDTO();
        instance.setRate(rate);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getApp method, of class RateDTO.
     */
    @Test
    public void testGetApp() {
        System.out.println("getApp");
        RateDTO instance = new RateDTO();
        AppDTO expResult = null;
        AppDTO result = instance.getApp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setApp method, of class RateDTO.
     */
    @Test
    public void testSetApp() {
        System.out.println("setApp");
        AppDTO app = null;
        RateDTO instance = new RateDTO();
        instance.setApp(app);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
