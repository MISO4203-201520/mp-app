/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.dtos;

import java.util.Date;
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
public class AppDTOTest {
    
    public AppDTOTest() {
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
     * Test of isEnabled method, of class AppDTO.
     */
    @Test
    public void testIsEnabled() {
        System.out.println("isEnabled");
        AppDTO instance = new AppDTO();
        boolean expResult = false;
        boolean result = instance.isEnabled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setEnabled method, of class AppDTO.
     */
    @Test
    public void testSetEnabled() {
        System.out.println("setEnabled");
        boolean enabled = false;
        AppDTO instance = new AppDTO();
        instance.setEnabled(enabled);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of getStartDiscountDate method, of class AppDTO.
     */
    @Test
    public void testGetStartDiscountDate() {
        System.out.println("getStartDiscountDate");
        AppDTO instance = new AppDTO();
        Date expResult = null;
        Date result = instance.getStartDiscountDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setStartDiscountDate method, of class AppDTO.
     */
    @Test
    public void testSetStartDiscountDate() {
        System.out.println("setStartDiscountDate");
        Date startDiscountDate = null;
        AppDTO instance = new AppDTO();
        instance.setStartDiscountDate(startDiscountDate);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of getFinishDiscountDate method, of class AppDTO.
     */
    @Test
    public void testGetFinishDiscountDate() {
        System.out.println("getFinishDiscountDate");
        AppDTO instance = new AppDTO();
        Date expResult = null;
        Date result = instance.getFinishDiscountDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setFinishDiscountDate method, of class AppDTO.
     */
    @Test
    public void testSetFinishDiscountDate() {
        System.out.println("setFinishDiscountDate");
        Date finishDiscountDate = null;
        AppDTO instance = new AppDTO();
        instance.setFinishDiscountDate(finishDiscountDate);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getId method, of class AppDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AppDTO instance = new AppDTO();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setId method, of class AppDTO.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        AppDTO instance = new AppDTO();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of getName method, of class AppDTO.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        AppDTO instance = new AppDTO();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setName method, of class AppDTO.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        AppDTO instance = new AppDTO();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
      //  // fail"The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class AppDTO.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        AppDTO instance = new AppDTO();
        String expResult = null;
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  // fail"The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class AppDTO.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        AppDTO instance = new AppDTO();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
      // fail"The test case is a prototype.");
    }

    /**
     * Test of getVersion method, of class AppDTO.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        AppDTO instance = new AppDTO();
        String expResult = null;
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      // fail"The test case is a prototype.");
    }

    /**
     * Test of setVersion method, of class AppDTO.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String version = "";
        AppDTO instance = new AppDTO();
        instance.setVersion(version);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of getPicture method, of class AppDTO.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        AppDTO instance = new AppDTO();
        String expResult = null;
        String result = instance.getPicture();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setPicture method, of class AppDTO.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");
        String picture = "";
        AppDTO instance = new AppDTO();
        instance.setPicture(picture);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class AppDTO.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        AppDTO instance = new AppDTO();
        Integer expResult = null;
        Integer result = instance.getPrice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class AppDTO.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Integer price = null;
        AppDTO instance = new AppDTO();
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
      // fail"The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class AppDTO.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        AppDTO instance = new AppDTO();
        Integer expResult = null;
        Integer result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setSize method, of class AppDTO.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        Integer size = null;
        AppDTO instance = new AppDTO();
        instance.setSize(size);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of getDeveloper method, of class AppDTO.
     */
    @Test
    public void testGetDeveloper() {
        System.out.println("getDeveloper");
        AppDTO instance = new AppDTO();
        DeveloperDTO expResult = null;
        DeveloperDTO result = instance.getDeveloper();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail"The test case is a prototype.");
    }

    /**
     * Test of setDeveloper method, of class AppDTO.
     */
    @Test
    public void testSetDeveloper() {
        System.out.println("setDeveloper");
        DeveloperDTO developer = null;
        AppDTO instance = new AppDTO();
        instance.setDeveloper(developer);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getPlatform method, of class AppDTO.
     */
    @Test
    public void testGetPlatform() {
        System.out.println("getPlatform");
        AppDTO instance = new AppDTO();
        String expResult = null;
        String result = instance.getPlatform();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setPlatform method, of class AppDTO.
     */
    @Test
    public void testSetPlatform() {
        System.out.println("setPlatform");
        String platform = "";
        AppDTO instance = new AppDTO();
        instance.setPlatform(platform);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getDiscount method, of class AppDTO.
     */
    @Test
    public void testGetDiscount() {
        System.out.println("getDiscount");
        AppDTO instance = new AppDTO();
        Integer expResult = null;
        Integer result = instance.getDiscount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setDiscount method, of class AppDTO.
     */
    @Test
    public void testSetDiscount() {
        System.out.println("setDiscount");
        Integer discount = null;
        AppDTO instance = new AppDTO();
        instance.setDiscount(discount);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getComments method, of class AppDTO.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        AppDTO instance = new AppDTO();
        List<CommentDTO> expResult = null;
        List<CommentDTO> result = instance.getComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setComments method, of class AppDTO.
     */
    @Test
    public void testSetComments() {
        System.out.println("setComments");
        List<CommentDTO> comments = null;
        AppDTO instance = new AppDTO();
        instance.setComments(comments);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class AppDTO.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        AppDTO instance = new AppDTO();
        String expResult = null;
        String result = instance.getCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setCategory method, of class AppDTO.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        String category = "";
        AppDTO instance = new AppDTO();
        instance.setCategory(category);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getRate method, of class AppDTO.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        AppDTO instance = new AppDTO();
        Double expResult = null;
        Double result = instance.getRate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setRate method, of class AppDTO.
     */
    @Test
    public void testSetRate() {
        System.out.println("setRate");
        Double rate = null;
        AppDTO instance = new AppDTO();
        instance.setRate(rate);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getDownloads method, of class AppDTO.
     */
    @Test
    public void testGetDownloads() {
        System.out.println("getDownloads");
        AppDTO instance = new AppDTO();
        Long expResult = null;
        Long result = instance.getDownloads();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setDownloads method, of class AppDTO.
     */
    @Test
    public void testSetDownloads() {
        System.out.println("setDownloads");
        Long downloads = null;
        AppDTO instance = new AppDTO();
        instance.setDownloads(downloads);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getImages method, of class AppDTO.
     */
    @Test
    public void testGetImages() {
        System.out.println("getImages");
        AppDTO instance = new AppDTO();
        List<MediaDTO> expResult = null;
        List<MediaDTO> result = instance.getImages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setImages method, of class AppDTO.
     */
    @Test
    public void testSetImages() {
        System.out.println("setImages");
        List<MediaDTO> images = null;
        AppDTO instance = new AppDTO();
        instance.setImages(images);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getVideos method, of class AppDTO.
     */
    @Test
    public void testGetVideos() {
        System.out.println("getVideos");
        AppDTO instance = new AppDTO();
        List<MediaDTO> expResult = null;
        List<MediaDTO> result = instance.getVideos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setVideos method, of class AppDTO.
     */
    @Test
    public void testSetVideos() {
        System.out.println("setVideos");
        List<MediaDTO> videos = null;
        AppDTO instance = new AppDTO();
        instance.setVideos(videos);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of getSources method, of class AppDTO.
     */
    @Test
    public void testGetSources() {
        System.out.println("getSources");
        AppDTO instance = new AppDTO();
        List<SourceDTO> expResult = null;
        List<SourceDTO> result = instance.getSources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }

    /**
     * Test of setSources method, of class AppDTO.
     */
    @Test
    public void testSetSources() {
        System.out.println("setSources");
        List<SourceDTO> sources = null;
        AppDTO instance = new AppDTO();
        instance.setSources(sources);
        // TODO review the generated test code and remove the default call to fail.
        // fail"The test case is a prototype.");
    }
    
}
