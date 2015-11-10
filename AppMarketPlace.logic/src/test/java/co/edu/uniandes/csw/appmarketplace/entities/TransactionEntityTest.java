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
public class TransactionEntityTest {
    
    public TransactionEntityTest() {
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
     * Test of getId method, of class TransactionEntity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TransactionEntity instance = new TransactionEntity();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class TransactionEntity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        TransactionEntity instance = new TransactionEntity();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getTotal method, of class TransactionEntity.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        TransactionEntity instance = new TransactionEntity();
        int expResult = 0;
        int result = instance.getTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class TransactionEntity.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        int total = 0;
        TransactionEntity instance = new TransactionEntity();
        instance.setTotal(total);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getStatus method, of class TransactionEntity.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        TransactionEntity instance = new TransactionEntity();
        String expResult = null;
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class TransactionEntity.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        TransactionEntity instance = new TransactionEntity();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getPaymentCard method, of class TransactionEntity.
     */
    @Test
    public void testGetPaymentCard() {
        System.out.println("getPaymentCard");
        TransactionEntity instance = new TransactionEntity();
        PaymentCardEntity expResult = null;
        PaymentCardEntity result = instance.getPaymentCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentCard method, of class TransactionEntity.
     */
    @Test
    public void testSetPaymentCard() {
        System.out.println("setPaymentCard");
        PaymentCardEntity paymentCard = null;
        TransactionEntity instance = new TransactionEntity();
        instance.setPaymentCard(paymentCard);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of hashCode method, of class TransactionEntity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TransactionEntity instance = new TransactionEntity();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class TransactionEntity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        TransactionEntity instance = new TransactionEntity();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TransactionEntity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TransactionEntity instance = new TransactionEntity();
        String expResult = instance.toString();;
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPayer method, of class TransactionEntity.
     */
    @Test
    public void testGetPayer() {
        System.out.println("getPayer");
        TransactionEntity instance = new TransactionEntity();
        ClientEntity expResult = null;
        ClientEntity result = instance.getPayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPayer method, of class TransactionEntity.
     */
    @Test
    public void testSetPayer() {
        System.out.println("setPayer");
        ClientEntity payer = null;
        TransactionEntity instance = new TransactionEntity();
        instance.setPayer(payer);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getRecipient method, of class TransactionEntity.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        TransactionEntity instance = new TransactionEntity();
        AppEntity expResult = null;
        AppEntity result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setRecipient method, of class TransactionEntity.
     */
    @Test
    public void testSetRecipient() {
        System.out.println("setRecipient");
        AppEntity recipient = null;
        TransactionEntity instance = new TransactionEntity();
        instance.setRecipient(recipient);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getDate method, of class TransactionEntity.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        TransactionEntity instance = new TransactionEntity();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class TransactionEntity.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        TransactionEntity instance = new TransactionEntity();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getAppId method, of class TransactionEntity.
     */
    @Test
    public void testGetAppId() {
        System.out.println("getAppId");
        TransactionEntity instance = new TransactionEntity();
        AppEntity expResult = null;
        AppEntity result = instance.getAppId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAppId method, of class TransactionEntity.
     */
    @Test
    public void testSetAppId() {
        System.out.println("setAppId");
        AppEntity appId = null;
        TransactionEntity instance = new TransactionEntity();
        instance.setAppId(appId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }
    
}
