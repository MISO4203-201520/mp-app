/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.AppService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
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
    public final static List<AppDTO> oraculo = new ArrayList<>();

    @Deployment
   @Ignore public static Archive<?> createDeployment() {
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
            Response response2 = cliente.target(URLBASE).path("/developers")
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
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }

    }

    @Test
    @RunAsClient
    public void t2GetAppsByKeyWords() throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATH + "?keyword=" + oraculo.get(0).getName())
                .request().get();
        String listApps = response.readEntity(String.class);
        List<AppDTO> listAppsTest = new ObjectMapper().readValue(listApps, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listAppsTest.size());
    }

    @Test
    @RunAsClient
    public void t3GetAppsByCategory() throws IOException {
        String category = oraculo.get(0).getCategory();

        Client cliente = ClientBuilder.newClient();

        Response response = cliente.target(URLBASE + PATH).path("/categories/" + category)
                .request().get();
        // Receiving apps in string format in order to retrieving all of them via ObjectMapper with a List
        String stringApps = response.readEntity(String.class);
        List<AppDTO> appsByCategory = new ObjectMapper().readValue(stringApps, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, appsByCategory.size());
    }
    
    public void t4GetAppById() {
        Client cliente = ClientBuilder.newClient();
        AppDTO appTest = cliente.target(URLBASE + PATH).path("/" + oraculo.get(0).getId())
                .request().get(AppDTO.class);
        Assert.assertEquals(appTest.getId(), oraculo.get(0).getId());
    }
    
    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t5GetApps() throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATH)
                .request().get();
        String listApp = response.readEntity(String.class);
        List<AppDTO> listAppTest = new ObjectMapper().readValue(listApp, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listAppTest.size());
    }
    
    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t6UpdateApp() throws IOException {
        
        Client cliente = ClientBuilder.newClient();
        AppDTO app = cliente.target(URLBASE + PATH).path("/" + oraculo.get(0).getId())
                .request().get(AppDTO.class);
        PodamFactory factory = new PodamFactoryImpl();
        AppDTO appChanged = factory.manufacturePojo(AppDTO.class);
        app.setName(appChanged.getName());
        app.setDescription(appChanged.getDescription());
        app.setVersion(appChanged.getVersion());
        app.setPicture(appChanged.getPicture());
        app.setPrice(appChanged.getPrice());
        app.setSize(appChanged.getSize());
        
        Cookie cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"),
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        
                
        
        Response response = cliente.target(URLBASE + PATH).path("/" + app.getId())
                .request().cookie(cookie_session_id).put(Entity.entity(app, MediaType.APPLICATION_JSON));
        AppDTO appTest = (AppDTO) response.readEntity(AppDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(app.getId(), appTest.getId());
        Assert.assertEquals(app.getName(), appTest.getName());
        Assert.assertEquals(app.getDescription(), appTest.getDescription());
        Assert.assertEquals(app.getVersion(), appTest.getVersion());
        Assert.assertEquals(app.getPicture(), appTest.getPicture());
        Assert.assertEquals(app.getPrice(), appTest.getPrice());
        Assert.assertEquals(app.getSize(), appTest.getSize());

    }
    
    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t7DeleteApp() {
        Cookie cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"),
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        Client cliente = ClientBuilder.newClient();
        AppDTO app = oraculo.get(0);
        Response response = cliente.target(URLBASE + PATH).path("/" + app.getId())
                .request().cookie(cookie_session_id).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
    @Test
    @RunAsClient
    public void t8GetCheapestApp(){
        Cookie cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"),
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        if (cookie_session_id != null) {
            
            Client cliente = ClientBuilder.newClient();
            PodamFactory factory = new PodamFactoryImpl();
            DeveloperDTO developer = factory.manufacturePojo(DeveloperDTO.class);
            developer.setName(System.getenv("APPOTECA_DEVELOPER_USERNAME"));
            Response response2 = cliente.target(URLBASE).path("/developers")
                    .request().cookie(cookie_session_id)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
            List<AppDTO> lessPriceApps = new ArrayList<AppDTO>();
            Integer lessPrice = oraculo.get(0).getPrice();
            for(AppDTO app: oraculo){
                if(app.getPrice()<lessPrice){
                    lessPrice = app.getPrice();
                    lessPriceApps.clear();
                    lessPriceApps.add(app);
                }else if(app.getPrice().equals(lessPrice)){
                    lessPriceApps.add(app);
                }
                //Se agrega cada app
                Response response = cliente.target(URLBASE + PATH)
                    .request().cookie(cookie_session_id)
                    .post(Entity.entity(app, MediaType.APPLICATION_JSON));
            }
            Response response = cliente.target(URLBASE + PATH + "/cheapest/"+developer.getName())
                    .request(MediaType.APPLICATION_JSON).cookie(cookie_session_id)
                    .get();
            List<AppDTO> res = response.readEntity(new GenericType<ArrayList<AppDTO>>() {});
            Assert.assertEquals(lessPriceApps.size(), res.size() );
            for(AppDTO app: res){
                Assert.assertEquals(lessPrice, app.getPrice());
            }
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

}
