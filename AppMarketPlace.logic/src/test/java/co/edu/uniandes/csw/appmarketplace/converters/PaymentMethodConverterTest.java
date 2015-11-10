/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentMethodEntity;
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
public class PaymentMethodConverterTest {
    
    public PaymentMethodConverterTest() {
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
     * Test of refEntity2DTO method, of class PaymentMethodConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        PaymentMethodEntity entity = new PaymentMethodEntity();
        PaymentMethodDTO expResult = null;
        PaymentMethodDTO result = PaymentMethodConverter.refEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class PaymentMethodConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        PaymentMethodDTO dto = new PaymentMethodDTO();
        PaymentMethodEntity expResult = null;
        PaymentMethodEntity result = PaymentMethodConverter.refDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullEntity2DTO method, of class PaymentMethodConverter.
     */
    @Test
    public void testFullEntity2DTO() {
        System.out.println("fullEntity2DTO");
        PaymentMethodEntity entity = new PaymentMethodEntity();
        PaymentMethodDTO expResult = null;
        PaymentMethodDTO result = PaymentMethodConverter.fullEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullDTO2Entity method, of class PaymentMethodConverter.
     */
    @Test
    public void testFullDTO2Entity() {
        System.out.println("fullDTO2Entity");
        PaymentMethodDTO dto = new PaymentMethodDTO();
        PaymentMethodEntity expResult = null;
        PaymentMethodEntity result = PaymentMethodConverter.fullDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class PaymentMethodConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<PaymentMethodEntity> entities = new ArrayList();;
        List<PaymentMethodDTO> expResult = new ArrayList();
        List<PaymentMethodDTO> result = PaymentMethodConverter.listEntity2DTO(entities);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listDTO2Entity method, of class PaymentMethodConverter.
     */
    @Test
    public void testListDTO2Entity() {
        System.out.println("listDTO2Entity");
        List<PaymentMethodDTO> dtos = new ArrayList();;
        List<PaymentMethodEntity> expResult = new ArrayList();
        List<PaymentMethodEntity> result = PaymentMethodConverter.listDTO2Entity(dtos);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
