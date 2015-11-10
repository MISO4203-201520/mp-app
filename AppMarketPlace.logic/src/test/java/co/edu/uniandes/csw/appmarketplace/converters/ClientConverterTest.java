/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
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
public class ClientConverterTest {
    
    public ClientConverterTest() {
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
     * Test of refEntity2DTO method, of class ClientConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        ClientEntity entity = new ClientEntity();
        ClientDTO expResult = null;
        ClientDTO result = ClientConverter.refEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class ClientConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        ClientDTO dto = new ClientDTO();
        ClientEntity expResult = null;
        ClientEntity result = ClientConverter.refDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of basicEntity2DTO method, of class ClientConverter.
     */
    @Test
    public void testBasicEntity2DTO() {
        System.out.println("basicEntity2DTO");
        ClientEntity entity = new ClientEntity();
        ClientDTO expResult = null;
        ClientDTO result = ClientConverter.basicEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of basicDTO2Entity method, of class ClientConverter.
     */
    @Test
    public void testBasicDTO2Entity() {
        System.out.println("basicDTO2Entity");
        ClientDTO dto = new ClientDTO();
        ClientEntity expResult = null;
        ClientEntity result = ClientConverter.basicDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullEntity2DTO method, of class ClientConverter.
     */
    @Test
    public void testFullEntity2DTO() {
        System.out.println("fullEntity2DTO");
        ClientEntity entity = new ClientEntity();
        ClientDTO expResult = null;
        ClientDTO result = ClientConverter.fullEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullDTO2Entity method, of class ClientConverter.
     */
    @Test
    public void testFullDTO2Entity() {
        System.out.println("fullDTO2Entity");
        ClientDTO dto = new ClientDTO();
        ClientEntity expResult = null;
        ClientEntity result = ClientConverter.fullDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class ClientConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<ClientEntity> entities = new ArrayList();;
        List<ClientDTO> expResult = new ArrayList();
        List<ClientDTO> result = ClientConverter.listEntity2DTO(entities);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listDTO2Entity method, of class ClientConverter.
     */
    @Test
    public void testListDTO2Entity() {
        System.out.println("listDTO2Entity");
        List<ClientDTO> dtos = new ArrayList();;
        List<ClientEntity> expResult = new ArrayList();
        List<ClientEntity> result = ClientConverter.listDTO2Entity(dtos);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
