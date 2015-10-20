package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.DeveloperService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author djimenez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class DeveloperServiceTest {

    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public final static String PATH = "/developers";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static List<DeveloperDTO> oraculo = new ArrayList<DeveloperDTO>();

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
                .addPackage(DeveloperService.class.getPackage())
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
    public static void setUp() {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            DeveloperDTO developer = factory.manufacturePojo(DeveloperDTO.class);
            oraculo.add(developer);
        }
    }

    private Cookie login(String username, String password) {
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

    @Test
    @RunAsClient
    public void t00CreateDeveloper() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));

        if (cookieSessionId != null) {
            DeveloperDTO developer = oraculo.get(0);
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
            DeveloperDTO createdDeveloper = (DeveloperDTO) response.readEntity(DeveloperDTO.class);

            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdDeveloper);
            Assert.assertEquals(developer.getId(), createdDeveloper.getId());
            Assert.assertEquals(createdDeveloper.getName(), oraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t02CreateDeveloper2() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));

        if (cookieSessionId != null) {
            DeveloperDTO developer = oraculo.get(1);
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
            DeveloperDTO createdDeveloper = (DeveloperDTO) response.readEntity(DeveloperDTO.class);

            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdDeveloper);
            Assert.assertEquals(developer.getId(), createdDeveloper.getId());
            Assert.assertEquals(createdDeveloper.getName(), oraculo.get(1).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t04GetDevelopers() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .get();
            // Receiving developers in string format in order to retrieving all developers via ObjectMapper with a List
            String stringDevelopers = response.readEntity(String.class);
            List<DeveloperDTO> allDevelopers = new ObjectMapper().readValue(stringDevelopers, List.class);
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertEquals(2, allDevelopers.size());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t06GetDeveloperById() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"), 
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));

        if (cookieSessionId != null) {
            Client cliente = ClientBuilder.newClient();
            DeveloperDTO foundDeveloper = cliente.target(URLBASE + PATH).path("/" + oraculo.get(0).getId())
                    .request().cookie(cookieSessionId).get(DeveloperDTO.class);

            Assert.assertNotNull(foundDeveloper);
            Assert.assertEquals(foundDeveloper.getName(), oraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t08UpdateDeveloper() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"), 
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        
        if (cookieSessionId != null) {
            DeveloperDTO developer = oraculo.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            DeveloperDTO changedDeveloper = factory.manufacturePojo(DeveloperDTO.class);
            developer.setName(changedDeveloper.getName());
            developer.setEmail(changedDeveloper.getEmail());

            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATH).path("/" + developer.getId())
                    .request().cookie(cookieSessionId)
                    .put(Entity.entity(developer, MediaType.APPLICATION_JSON));
            DeveloperDTO updatedDeveloper = (DeveloperDTO) response.readEntity(DeveloperDTO.class);
            
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertNotNull(updatedDeveloper);
            Assert.assertEquals(updatedDeveloper.getName(), developer.getName());
            Assert.assertEquals(updatedDeveloper.getEmail(), developer.getEmail());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t10GetDeveloperByUsername() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"), 
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));

        if (cookieSessionId != null) {
            Client cliente = ClientBuilder.newClient();
            DeveloperDTO foundDeveloper = cliente.target(URLBASE + PATH).path("/" + oraculo.get(0).getName())
                    .request().cookie(cookieSessionId).get(DeveloperDTO.class);

            Assert.assertNotNull(foundDeveloper);
            Assert.assertEquals(foundDeveloper.getName(), oraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t12DeleteDeveloper() {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));

        if (cookieSessionId != null) {
            Client cliente = ClientBuilder.newClient();
            DeveloperDTO developer = oraculo.get(0);
            Response response = cliente.target(URLBASE + PATH).path("/" + developer.getId())
                    .request().cookie(cookieSessionId).delete();
            Assert.assertEquals(OkWithoutContent, response.getStatus());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t14GetDevelopers() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            
            Client cliente = ClientBuilder.newClient();
            
            Response response = cliente.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .get();
            // Receiving developers in string format in order to retrieving all developers via ObjectMapper with a List
            String stringDevelopers = response.readEntity(String.class);
            List<DeveloperDTO> allDevelopers = new ObjectMapper().readValue(stringDevelopers, List.class);
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertEquals(1, allDevelopers.size());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
}
