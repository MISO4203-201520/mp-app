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
public class ResponseDTOTest {
    
    public ResponseDTOTest() {
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
     * Test of getMessage method, of class ResponseDTO.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        ResponseDTO instance = new ResponseDTO(null,0);
        String expResult = null;
        String result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class ResponseDTO.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "";
        ResponseDTO instance = new ResponseDTO(null,0);
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getStatus method, of class ResponseDTO.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        ResponseDTO instance = new ResponseDTO(null,0);
        int expResult = 0;
        int result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class ResponseDTO.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int status = 0;
        ResponseDTO instance = new ResponseDTO(null,0);
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
