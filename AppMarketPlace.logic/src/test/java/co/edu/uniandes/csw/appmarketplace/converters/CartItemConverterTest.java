/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.CartItemDTO;
import co.edu.uniandes.csw.appmarketplace.entities.CartItemEntity;
import co.edu.uniandes.csw.appmarketplace.entities.ClientEntity;
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
public class CartItemConverterTest {
    
    public CartItemConverterTest() {
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
     * Test of refEntity2DTO method, of class CartItemConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        CartItemEntity entity = new CartItemEntity();
        CartItemDTO expResult = null;
        CartItemDTO result = CartItemConverter.refEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class CartItemConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        CartItemDTO dto = new CartItemDTO();
        CartItemEntity expResult = null;
        CartItemEntity result = CartItemConverter.refDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullEntity2DTO method, of class CartItemConverter.
     */
    @Test
    public void testFullEntity2DTO() {
        System.out.println("fullEntity2DTO");
        CartItemEntity entity = new CartItemEntity();
        CartItemDTO expResult = null;
        CartItemDTO result = CartItemConverter.fullEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullDTO2Entity method, of class CartItemConverter.
     */
    @Test
    public void testFullDTO2Entity() {
        System.out.println("fullDTO2Entity");
        CartItemDTO dto = new CartItemDTO();
        CartItemEntity expResult = null;
        CartItemEntity result = CartItemConverter.fullDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class CartItemConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<CartItemEntity> entities =new ArrayList();
        List<CartItemDTO> expResult = new ArrayList();
        List<CartItemDTO> result = CartItemConverter.listEntity2DTO(entities);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listDTO2Entity method, of class CartItemConverter.
     */
    @Test
    public void testListDTO2Entity() {
        System.out.println("listDTO2Entity");
        List<CartItemDTO> dtos = new ArrayList();
        List<CartItemEntity> expResult = new ArrayList();
        List<CartItemEntity> result = CartItemConverter.listDTO2Entity(dtos);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of childDTO2Entity method, of class CartItemConverter.
     */
    @Test
    public void testChildDTO2Entity() {
        System.out.println("childDTO2Entity");
        CartItemDTO dto = new CartItemDTO();
        ClientEntity parent = null;
        CartItemEntity expResult = null;
        CartItemEntity result = CartItemConverter.childDTO2Entity(dto, parent);
        assertNull(expResult);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of childListDTO2Entity method, of class CartItemConverter.
     */
    @Test
    public void testChildListDTO2Entity() {
        System.out.println("childListDTO2Entity");
        List<CartItemDTO> dtos = new ArrayList();
        ClientEntity parent = null;
        List<CartItemEntity> expResult = new ArrayList();
        List<CartItemEntity> result = CartItemConverter.childListDTO2Entity(dtos, parent);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
