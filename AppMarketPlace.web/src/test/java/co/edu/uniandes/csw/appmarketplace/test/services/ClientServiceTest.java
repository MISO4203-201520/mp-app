package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.ClientService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class ClientServiceTest {
    
    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public final static String PATH = "/clients";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static List<ClientDTO> oraculo = new ArrayList<ClientDTO>();
    
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
                .addPackage(ClientService.class.getPackage())
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
            ClientDTO client = factory.manufacturePojo(ClientDTO.class);
            oraculo.add(client);
        }
    }
    
    private Cookie login(String username, String password) {
        Client clienteWS = ClientBuilder.newClient();
        
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        
        Response response
                = clienteWS.target(URLBASE)
                .register(LoggingFilter.class)
                .path("users")
                .path("login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        
        UserDTO foundUser = (UserDTO) response.readEntity(UserDTO.class);
        
        if (foundUser != null && response.getStatus() == Ok) {
            return response.getCookies().get("JSESSIONID");
        } else {
            return null;
        }
    }
    
    @Test
    @RunAsClient
    public void t00CreateClient() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"),
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            ClientDTO client = oraculo.get(0);
            Client clienteWS = ClientBuilder.newClient();
            Response response = clienteWS.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(client, MediaType.APPLICATION_JSON));
            ClientDTO createdClient = (ClientDTO) response.readEntity(ClientDTO.class);
            
            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdClient);
            Assert.assertEquals(client.getId(), createdClient.getId());
            Assert.assertEquals(createdClient.getName(), oraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t02CreateClient2() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"),
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            ClientDTO client = oraculo.get(1);
            Client clienteWS = ClientBuilder.newClient();
            Response response = clienteWS.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(client, MediaType.APPLICATION_JSON));
            ClientDTO createdClient = (ClientDTO) response.readEntity(ClientDTO.class);
            
            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdClient);
            Assert.assertEquals(client.getId(), createdClient.getId());
            Assert.assertEquals(createdClient.getName(), oraculo.get(1).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t04GetClients() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"),
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            
            Client clienteWS = ClientBuilder.newClient();
            Response response = clienteWS.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .get();
            // Receiving clients in string format in order to retrieving all clients via ObjectMapper with a List
            String stringClients = response.readEntity(String.class);
            List<ClientDTO> allClients = new ObjectMapper().readValue(stringClients, List.class);
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertEquals(2, allClients.size());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t06GetClientById() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));
        
        if (cookieSessionId != null) {
            Client clienteWS = ClientBuilder.newClient();
            ClientDTO foundClient = clienteWS.target(URLBASE + PATH).path("/" + oraculo.get(0).getId())
                    .request().cookie(cookieSessionId).get(ClientDTO.class);
            
            Assert.assertNotNull(foundClient);
            Assert.assertEquals(foundClient.getName(), oraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

//    @Test
//    @RunAsClient
//    public void t08UpdateClient() throws IOException {
//        Cookie cookieSessionId = login(
//                System.getenv("APPOTECA_CLIENT_USERNAME"), 
//                System.getenv("APPOTECA_CLIENT_PASSWORD"));
//        
//        if (cookieSessionId != null) {
//            ClientDTO client = oraculo.get(0);
//            PodamFactory factory = new PodamFactoryImpl();
//            ClientDTO changedClient = factory.manufacturePojo(ClientDTO.class);
//            client.setName(changedClient.getName());
//            client.setEmail(changedClient.getEmail());
//
//            Client clienteWS = ClientBuilder.newClient();
//            Response response = clienteWS.target(URLBASE + PATH).path("/" + client.getId())
//                    .request().cookie(cookieSessionId)
//                    .put(Entity.entity(client, MediaType.APPLICATION_JSON));
//            ClientDTO updatedClient = (ClientDTO) response.readEntity(ClientDTO.class);
//            
//            Assert.assertEquals(Ok, response.getStatus());
//            Assert.assertNotNull(updatedClient);
//            Assert.assertEquals(updatedClient.getName(), client.getName());
//            Assert.assertEquals(updatedClient.getEmail(), client.getEmail());
//        } else {
//            Assert.fail("Access denied or Invalid credentials!");
//        }
//    }
    @Test
    @RunAsClient
    public void t10DeleteClient() {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"),
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            Client clienteWS = ClientBuilder.newClient();
            ClientDTO client = oraculo.get(0);
            Response response = clienteWS.target(URLBASE + PATH).path("/" + client.getId())
                    .request().cookie(cookieSessionId).delete();
            Assert.assertEquals(OkWithoutContent, response.getStatus());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t12GetClients() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"),
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            
            Client clienteWS = ClientBuilder.newClient();
            
            Response response = clienteWS.target(URLBASE + PATH)
                    .request().cookie(cookieSessionId)
                    .get();
            // Receiving clients in string format in order to retrieving all clients via ObjectMapper with a List
            String stringClients = response.readEntity(String.class);
            List<ClientDTO> allClients = new ObjectMapper().readValue(stringClients, List.class);
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertEquals(1, allClients.size());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
}
