/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

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
public class RateEntityTest {
    
    public RateEntityTest() {
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
     * Test of getId method, of class RateEntity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        RateEntity instance = new RateEntity();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class RateEntity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        RateEntity instance = new RateEntity();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getRate method, of class RateEntity.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        RateEntity instance = new RateEntity();
        Long expResult = null;
        Long result = instance.getRate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setRate method, of class RateEntity.
     */
    @Test
    public void testSetRate() {
        System.out.println("setRate");
        Long rate = null;
        RateEntity instance = new RateEntity();
        instance.setRate(rate);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getClient method, of class RateEntity.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        RateEntity instance = new RateEntity();
        ClientEntity expResult = null;
        ClientEntity result = instance.getClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setClient method, of class RateEntity.
     */
    @Test
    public void testSetClient() {
        System.out.println("setClient");
        ClientEntity client = null;
        RateEntity instance = new RateEntity();
        instance.setClient(client);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getApp method, of class RateEntity.
     */
    @Test
    public void testGetApp() {
        System.out.println("getApp");
        RateEntity instance = new RateEntity();
        AppEntity expResult = null;
        AppEntity result = instance.getApp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setApp method, of class RateEntity.
     */
    @Test
    public void testSetApp() {
        System.out.println("setApp");
        AppEntity app = null;
        RateEntity instance = new RateEntity();
        instance.setApp(app);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
