/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

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
public class PaymentCardEntityTest {
    
    public PaymentCardEntityTest() {
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
     * Test of getId method, of class PaymentCardEntity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PaymentCardEntity instance = new PaymentCardEntity();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PaymentCardEntity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getFullname method, of class PaymentCardEntity.
     */
    @Test
    public void testGetFullname() {
        System.out.println("getFullname");
        PaymentCardEntity instance = new PaymentCardEntity();
        String expResult = null;
        String result = instance.getFullname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setFullname method, of class PaymentCardEntity.
     */
    @Test
    public void testSetFullname() {
        System.out.println("setFullname");
        String fullname = "";
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setFullname(fullname);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getCardnumber method, of class PaymentCardEntity.
     */
    @Test
    public void testGetCardnumber() {
        System.out.println("getCardnumber");
        PaymentCardEntity instance = new PaymentCardEntity();
        int expResult = 0;
        int result = instance.getCardnumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCardnumber method, of class PaymentCardEntity.
     */
    @Test
    public void testSetCardnumber() {
        System.out.println("setCardnumber");
        int cardnumber = 0;
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setCardnumber(cardnumber);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getSecurityCode method, of class PaymentCardEntity.
     */
    @Test
    public void testGetSecurityCode() {
        System.out.println("getSecurityCode");
        PaymentCardEntity instance = new PaymentCardEntity();
        short expResult = 0;
        short result = instance.getSecurityCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setSecurityCode method, of class PaymentCardEntity.
     */
    @Test
    public void testSetSecurityCode() {
        System.out.println("setSecurityCode");
        short securityCode = 0;
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setSecurityCode(securityCode);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getDueDate method, of class PaymentCardEntity.
     */
    @Test
    public void testGetDueDate() {
        System.out.println("getDueDate");
        PaymentCardEntity instance = new PaymentCardEntity();
        Date expResult = null;
        Date result = instance.getDueDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDueDate method, of class PaymentCardEntity.
     */
    @Test
    public void testSetDueDate() {
        System.out.println("setDueDate");
        Date dueDate = null;
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setDueDate(dueDate);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getPaymentType method, of class PaymentCardEntity.
     */
    @Test
    public void testGetPaymentType() {
        System.out.println("getPaymentType");
        PaymentCardEntity instance = new PaymentCardEntity();
        PaymentMethodEntity expResult = null;
        PaymentMethodEntity result = instance.getPaymentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentType method, of class PaymentCardEntity.
     */
    @Test
    public void testSetPaymentType() {
        System.out.println("setPaymentType");
        PaymentMethodEntity paymentType = null;
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setPaymentType(paymentType);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of hashCode method, of class PaymentCardEntity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        PaymentCardEntity instance = new PaymentCardEntity();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class PaymentCardEntity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        PaymentCardEntity instance = new PaymentCardEntity();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class PaymentCardEntity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PaymentCardEntity instance = new PaymentCardEntity();
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getOwnerId method, of class PaymentCardEntity.
     */
    @Test
    public void testGetOwnerId() {
        System.out.println("getOwnerId");
        PaymentCardEntity instance = new PaymentCardEntity();
        ClientEntity expResult = null;
        ClientEntity result = instance.getOwnerId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setOwnerId method, of class PaymentCardEntity.
     */
    @Test
    public void testSetOwnerId() {
        System.out.println("setOwnerId");
        ClientEntity ownerId = null;
        PaymentCardEntity instance = new PaymentCardEntity();
        instance.setOwnerId(ownerId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
