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
public class AppSourceEntityTest {
    
    public AppSourceEntityTest() {
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
     * Test of getId method, of class AppSourceEntity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AppSourceEntity instance = new AppSourceEntity();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class AppSourceEntity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        AppSourceEntity instance = new AppSourceEntity();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getUrl method, of class AppSourceEntity.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        AppSourceEntity instance = new AppSourceEntity();
        String expResult = null;
        String result = instance.getUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUrl method, of class AppSourceEntity.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        String url = "";
        AppSourceEntity instance = new AppSourceEntity();
        instance.setUrl(url);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getVersion method, of class AppSourceEntity.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        AppSourceEntity instance = new AppSourceEntity();
        String expResult = null;
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setVersion method, of class AppSourceEntity.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String version = "";
        AppSourceEntity instance = new AppSourceEntity();
        instance.setVersion(version);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getApp method, of class AppSourceEntity.
     */
    @Test
    public void testGetApp() {
        System.out.println("getApp");
        AppSourceEntity instance = new AppSourceEntity();
        AppEntity expResult = null;
        AppEntity result = instance.getApp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setApp method, of class AppSourceEntity.
     */
    @Test
    public void testSetApp() {
        System.out.println("setApp");
        AppEntity app = null;
        AppSourceEntity instance = new AppSourceEntity();
        instance.setApp(app);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
