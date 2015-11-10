/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.converters;

import co.edu.uniandes.csw.appmarketplace.dtos.CommentDTO;
import co.edu.uniandes.csw.appmarketplace.entities.Comment;
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
public class CommentConverterTest {
    
    public CommentConverterTest() {
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
     * Test of refEntity2DTO method, of class CommentConverter.
     */
    @Test
    public void testRefEntity2DTO() {
        System.out.println("refEntity2DTO");
        Comment entity = new Comment();
        CommentDTO expResult = null;
        CommentDTO result = CommentConverter.refEntity2DTO(entity);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refDTO2Entity method, of class CommentConverter.
     */
    @Test
    public void testRefDTO2Entity() {
        System.out.println("refDTO2Entity");
        CommentDTO dto = new CommentDTO();
        Comment expResult = null;
        Comment result = CommentConverter.refDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of childListDTO2Entity method, of class CommentConverter.
     */
    @Test
    public void testChildListDTO2Entity() {
        System.out.println("childListDTO2Entity");
        List<Comment> entities = new ArrayList();;
        CommentConverter instance = null;
        List<CommentDTO> expResult = new ArrayList();
        List<CommentDTO> result = instance.childListDTO2Entity(entities);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of childListEntity2DTO method, of class CommentConverter.
     */
    @Test
    public void testChildListEntity2DTO() throws Exception {
        System.out.println("childListEntity2DTO");
        List<CommentDTO> dtos = new ArrayList();;
        CommentConverter instance = null;
        List<Comment> expResult = new ArrayList();
        List<Comment> result = instance.childListEntity2DTO(dtos);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of basicDTO2Entity method, of class CommentConverter.
     */
    @Test
    public void testBasicDTO2Entity() throws Exception {
        System.out.println("basicDTO2Entity");
        CommentDTO dto = new CommentDTO();
        Comment expResult = null;
        Comment result = CommentConverter.basicDTO2Entity(dto);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listEntity2DTO method, of class CommentConverter.
     */
    @Test
    public void testListEntity2DTO() {
        System.out.println("listEntity2DTO");
        List<Comment> entities = new ArrayList();;
        List<CommentDTO> expResult = new ArrayList();
        List<CommentDTO> result = CommentConverter.listEntity2DTO(entities);
        assertNull(null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

  
    
}
