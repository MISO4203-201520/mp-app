/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.converters.DeveloperConverter;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.persistence.DeveloperPersistence;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.AppService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



/**
 *
 * @author jd.patino10
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class AppServiceTest {

    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public final static String PATH = "/apps";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static List<AppDTO> oraculo = new ArrayList<AppDTO>();

    @Deployment
    public static Archive<?> createDeployment() {
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        WebArchive war = ShrinkWrap
                // Nombre del Proyecto "AppMarketPlace.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "AppMarketPlace.web.war")
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.appmarketplace:AppMarketPlace.logic:1.0")
                        .resolveAsFiles())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(AppService.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos. 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo shiro.ini 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo beans.xml es necesario para injeccion de dependencias. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
        return war;
    }
    
    @BeforeClass
    public static void setUp() throws IOException {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            AppDTO app = factory.manufacturePojo(AppDTO.class);
            oraculo.add(app);
        }
        
           
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

    /**
     * Test of createApp method, of class AppService.
     */
    @Test
    @RunAsClient
    public void t1CreateApp() {
        Cookie cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"), 
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        if (cookie_session_id != null) {
           AppDTO app = oraculo.get(0);
           Client cliente = ClientBuilder.newClient();
           PodamFactory factory = new PodamFactoryImpl();
           DeveloperDTO developer = factory.manufacturePojo(DeveloperDTO.class);
           developer.setName(System.getenv("APPOTECA_DEVELOPER_USERNAME"));
           Response response2 = cliente.target(URLBASE + "/developers")
                    .request().cookie(cookie_session_id)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
        
        Response response = cliente.target(URLBASE + PATH)
                .request().cookie(cookie_session_id)
                .post(Entity.entity(app, MediaType.APPLICATION_JSON));
        AppDTO appTest = (AppDTO) response.readEntity(AppDTO.class);
        Assert.assertEquals(app.getName(), appTest.getName());
        Assert.assertEquals(app.getDescription(), appTest.getDescription());
        Assert.assertEquals(app.getId(), appTest.getId());
        Assert.assertEquals(Created, response.getStatus()); 
        }else{
            Assert.fail("Access denied or Invalid credentials!");
        }
        
    }
    
    @Test
    @RunAsClient
    public void t2getAppsByKeyWords() throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATH + "?keyword=" + oraculo.get(0).getName()).register(LoggingFilter.class)
                .request().get();
        String listApps = response.readEntity(String.class);
        List<AppDTO> listAppsTest = new ObjectMapper().readValue(listApps, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listAppsTest.size()); 
        //Assert.assertEquals(listAppsTest.get(0).getName(), oraculo.get(0).getName());
    }
    
}