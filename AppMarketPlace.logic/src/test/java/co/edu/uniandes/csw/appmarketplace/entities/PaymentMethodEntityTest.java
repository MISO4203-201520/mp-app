/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.entities;

import java.util.ArrayList;
import java.util.Collection;
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
public class PaymentMethodEntityTest {
    
    public PaymentMethodEntityTest() {
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
     * Test of getId method, of class PaymentMethodEntity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PaymentMethodEntity instance = new PaymentMethodEntity();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PaymentMethodEntity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        PaymentMethodEntity instance = new PaymentMethodEntity();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getName method, of class PaymentMethodEntity.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        PaymentMethodEntity instance = new PaymentMethodEntity();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class PaymentMethodEntity.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        PaymentMethodEntity instance = new PaymentMethodEntity();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of getPaymentCardEntityCollection method, of class PaymentMethodEntity.
     */
    @Test
    public void testGetPaymentCardEntityCollection() {
        System.out.println("getPaymentCardEntityCollection");
        PaymentMethodEntity instance = new PaymentMethodEntity();
        Collection<PaymentCardEntity> expResult = null;
        Collection<PaymentCardEntity> result = instance.getPaymentCardEntityCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentCardEntityCollection method, of class PaymentMethodEntity.
     */
    @Test
    public void testSetPaymentCardEntityCollection() {
        System.out.println("setPaymentCardEntityCollection");
        Collection<PaymentCardEntity> paymentCardEntityCollection = null;
        PaymentMethodEntity instance = new PaymentMethodEntity();
        instance.setPaymentCardEntityCollection(paymentCardEntityCollection);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertTrue(true);
    }

    /**
     * Test of hashCode method, of class PaymentMethodEntity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        PaymentMethodEntity instance = new PaymentMethodEntity();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class PaymentMethodEntity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        PaymentMethodEntity instance = new PaymentMethodEntity();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class PaymentMethodEntity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PaymentMethodEntity instance = new PaymentMethodEntity();
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
