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
public class ForgotPasswordDTOTest {
    
    public ForgotPasswordDTOTest() {
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
     * Test of getNewPassword method, of class ForgotPasswordDTO.
     */
    @Test
    public void testGetNewPassword() {
        System.out.println("getNewPassword");
        ForgotPasswordDTO instance = new ForgotPasswordDTO();
        String expResult = null;
        String result = instance.getNewPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNewPassword method, of class ForgotPasswordDTO.
     */
    @Test
    public void testSetNewPassword() {
        System.out.println("setNewPassword");
        String newPassword = "";
        ForgotPasswordDTO instance = new ForgotPasswordDTO();
        instance.setNewPassword(newPassword);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getToken method, of class ForgotPasswordDTO.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
        ForgotPasswordDTO instance = new ForgotPasswordDTO();
        String expResult = null;
        String result = instance.getToken();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setToken method, of class ForgotPasswordDTO.
     */
    @Test
    public void testSetToken() {
        System.out.println("setToken");
        String token = "";
        ForgotPasswordDTO instance = new ForgotPasswordDTO();
        instance.setToken(token);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
