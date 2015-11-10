/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

import co.edu.uniandes.csw.appmarketplace.entities.AppEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
import java.util.Date;
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
public class PurchasedRecordDTOTest {
    
    public PurchasedRecordDTOTest() {
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
     * Test of getId method, of class PurchasedRecordDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PurchasedRecordDTO.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class PurchasedRecordDTO.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantity method, of class PurchasedRecordDTO.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 0;
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        instance.setQuantity(quantity);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getDate method, of class PurchasedRecordDTO.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class PurchasedRecordDTO.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getClientId method, of class PurchasedRecordDTO.
     */
    @Test
    public void testGetClientId() {
        System.out.println("getClientId");
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        ClientEntity expResult = null;
        ClientEntity result = instance.getClientId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setClientId method, of class PurchasedRecordDTO.
     */
    @Test
    public void testSetClientId() {
        System.out.println("setClientId");
        ClientEntity clientId = null;
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        instance.setClientId(clientId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getAppId method, of class PurchasedRecordDTO.
     */
    @Test
    public void testGetAppId() {
        System.out.println("getAppId");
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        AppEntity expResult = null;
        AppEntity result = instance.getAppId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAppId method, of class PurchasedRecordDTO.
     */
    @Test
    public void testSetAppId() {
        System.out.println("setAppId");
        AppEntity appId = null;
        PurchasedRecordDTO instance = new PurchasedRecordDTO();
        instance.setAppId(appId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
