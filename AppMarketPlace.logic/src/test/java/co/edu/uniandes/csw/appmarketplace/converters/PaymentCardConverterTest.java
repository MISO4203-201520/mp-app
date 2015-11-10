/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.PaymentCardDTO;
import co.edu.uniandes.csw.appmarketplace.entities.PaymentCardEntity;
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
public class PaymentCardConverterTest {
    
    public PaymentCardConverterTest() {
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
     * Test of refEntity2DTO method, of class PaymentCardConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        PaymentCardEntity entity = new PaymentCardEntity();
        PaymentCardDTO expResult = null;
        PaymentCardDTO result = PaymentCardConverter.refEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class PaymentCardConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        PaymentCardDTO dto = new PaymentCardDTO();
        PaymentCardEntity expResult = null;
        PaymentCardEntity result = PaymentCardConverter.refDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullEntity2DTO method, of class PaymentCardConverter.
     */
    @Test
    public void testFullEntity2DTO() {
        System.out.println("fullEntity2DTO");
        PaymentCardEntity entity = new PaymentCardEntity();
        PaymentCardDTO expResult = null;
        PaymentCardDTO result = PaymentCardConverter.fullEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullDTO2Entity method, of class PaymentCardConverter.
     */
    @Test
    public void testFullDTO2Entity() {
        System.out.println("fullDTO2Entity");
        PaymentCardDTO dto = new PaymentCardDTO();
        PaymentCardEntity expResult = null;
        PaymentCardEntity result = PaymentCardConverter.fullDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class PaymentCardConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<PaymentCardEntity> entities = new ArrayList();;
        List<PaymentCardDTO> expResult = new ArrayList();
        List<PaymentCardDTO> result = PaymentCardConverter.listEntity2DTO(entities);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listDTO2Entity method, of class PaymentCardConverter.
     */
    @Test
    public void testListDTO2Entity() {
        System.out.println("listDTO2Entity");
        List<PaymentCardDTO> dtos = new ArrayList();;
        List<PaymentCardEntity> expResult = new ArrayList();
        List<PaymentCardEntity> result = PaymentCardConverter.listDTO2Entity(dtos);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
