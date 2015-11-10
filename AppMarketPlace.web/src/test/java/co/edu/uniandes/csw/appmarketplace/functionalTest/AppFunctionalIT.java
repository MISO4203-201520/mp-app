/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.functionalTest;

import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.RateDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.AppService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.patino10
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
 public class AppFunctionalIT {
    public static String URLRESOURCES = "src/main/webapp";
    public static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public static String PATH = "/apps";
    private static WebDriver driver;
    public static int Ok = 200;
    public static int Created = 201;
    public static int OkWithoutContent = 204;
    private static String URLIMAGE = "http://www.seleniumhq.org/images/big-logo.png";
    public static List<AppDTO> data = new ArrayList<>();
    // Mediante la anotacion @ArquillianResource se obtiene la URL de despliegue de la aplicacion
    @ArquillianResource
    URL deploymentURL;
    
    @Deployment
    public static Archive<?> createDeployment() {
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

        WebArchive war = ShrinkWrap
                // Nombre del Proyecto "BookBasico.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "AppMarketPlace.web.war")
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.appmarketplace:AppMarketPlace.logic:1.0")
                        .resolveAsFiles())
                // Se agregan los compilados de los paquetes que se van a probar
                .addPackage(AppService.class.getPackage())
                // Se agrega contenido estatico: html y modulos de javascript. 
                .addAsWebResource(new File(URLRESOURCES, "index.html"))
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(URLRESOURCES + "/src/").as(GenericArchive.class), "/src/", Filters.includeAll())
                // El archivo que contiene la configuracion a la base de datos. 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo beans.xml es necesario para injeccion de dependencias. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));

        return war;
    }
    
    @BeforeClass
    public static void setUp() {
        // Crea una instancia del driver de firefox sobre el que se ejecutara la aplicacion.
        driver = new FirefoxDriver();
    }   
    
    @Before
    public void setUpTest() {        
        insertData();
        // El browser  va a la url de despliegue. Se ejecuta al inicar cada uno de los metodos de prueba indicados con @Test
        driver.get(deploymentURL.toString());
        
    }

    @AfterClass
    public static void tearDown() throws Exception {
        //Se ejecuta al terminar todos los metodos de prueba indicados con @Test Cierra el browser
        driver.quit();
    }
    
    private static Cookie login(String username, String password) {
        Client cliente = ClientBuilder.newClient();

        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);

        Response response = cliente.target(URLBASE).path("/users/login").request().
                post(Entity.entity(user, MediaType.APPLICATION_JSON));

        UserDTO foundUser = (UserDTO) response.readEntity(UserDTO.class);

        if (foundUser != null && response.getStatus() == Ok) {
            return response.getCookies().get("JSESSIONID");
        } else {
            return null;
        }
    }
    
    private void insertData() {
        Cookie cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"),
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        if (cookie_session_id != null) {
            Client cliente = ClientBuilder.newClient();
            PodamFactory factory = new PodamFactoryImpl();
            DeveloperDTO developer = factory.manufacturePojo(DeveloperDTO.class);
            developer.setName(System.getenv("APPOTECA_DEVELOPER_USERNAME"));
            Response response2 = cliente.target(URLBASE + "/developers")
                    .request().cookie(cookie_session_id)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
        for (int i = 0; i < 3; i++) {                       
            AppDTO app = factory.manufacturePojo(AppDTO.class);
            app.setPicture(URLIMAGE);            
            Response response = cliente.target(URLBASE + PATH)
                .request().cookie(cookie_session_id)
                .post(Entity.entity(app, MediaType.APPLICATION_JSON));
            if (response.getStatus()== Created)
                data.add(app);
        }    
        }
        
    }
    
    @After
    public void clearData() {
        Cookie cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"),
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        if (cookie_session_id != null) {
          for (int i = 0; i < data.size(); i++) {            
            PodamFactory factory = new PodamFactoryImpl();
            AppDTO app = factory.manufacturePojo(AppDTO.class);
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATH + '/' + data.get(i).getId())
                .request().cookie(cookie_session_id)
                .delete();
            if (response.getStatus()== OkWithoutContent)
                data.remove(app);
          }  
        }
        
    }
   @Test
    @RunAsClient
    public void t01GetAppsByKeyWords() throws InterruptedException {
        boolean success = false;
        Thread.sleep(3000);
        driver.findElement(By.id("txtBuscarApp")).clear();
        driver.findElement(By.id("txtBuscarApp")).sendKeys(data.get(0).getName());
        driver.findElement(By.id("btnBuscar")).click();        
        Thread.sleep(2000);
        List<WebElement> apps = driver.findElements(By.xpath("//div[contains(@ng-repeat,'record in records')]"));
        for (WebElement app : apps) {
            if (app.getText().contains(data.get(0).getName())) {
                success = true;
            }
        }
        assertTrue(success);
        Thread.sleep(1000);
    }    
}
