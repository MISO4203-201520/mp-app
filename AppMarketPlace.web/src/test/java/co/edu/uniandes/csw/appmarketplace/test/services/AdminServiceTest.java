package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.AdminService;
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
public class AdminServiceTest {

    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public final static String PATH = "/admin";
    public final static String PATHDEVELOPER = "/developers";
    public final static String PATHCLIENT = "/clients";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static List<DeveloperDTO> developersOraculo = new ArrayList<DeveloperDTO>();
    public final static List<ClientDTO> clientsOraculo = new ArrayList<ClientDTO>();

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
                .addPackage(AdminService.class.getPackage())
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
            developersOraculo.add(developer);
        }
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            ClientDTO client = factory.manufacturePojo(ClientDTO.class);
            clientsOraculo.add(client);
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
            DeveloperDTO developer = developersOraculo.get(0);
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATHDEVELOPER)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
            DeveloperDTO createdDeveloper = (DeveloperDTO) response.readEntity(DeveloperDTO.class);

            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdDeveloper);
            Assert.assertEquals(developer.getId(), createdDeveloper.getId());
            Assert.assertEquals(createdDeveloper.getName(), developersOraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t02GetDevelopers() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATH + PATHDEVELOPER)
                    .request().cookie(cookieSessionId)
                    .get();
            // Receiving developers in string format in order to retrieving all of them via ObjectMapper with a List
            String stringDevelopers = response.readEntity(String.class);
            List<DeveloperDTO> allDevelopers = new ObjectMapper().readValue(stringDevelopers, List.class);
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertEquals(1, allDevelopers.size());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t04CreateClient() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));

        if (cookieSessionId != null) {
            ClientDTO client = clientsOraculo.get(0);
            Client clienteWS = ClientBuilder.newClient();
            Response response = clienteWS.target(URLBASE + PATHCLIENT)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(client, MediaType.APPLICATION_JSON));
            ClientDTO createdClient = (ClientDTO) response.readEntity(ClientDTO.class);

            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdClient);
            Assert.assertEquals(client.getId(), createdClient.getId());
            Assert.assertEquals(createdClient.getName(), clientsOraculo.get(0).getName());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
    
    @Test
    @RunAsClient
    public void t06GetClients() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        
        if (cookieSessionId != null) {
            
            Client clienteWS = ClientBuilder.newClient();
            Response response = clienteWS.target(URLBASE + PATH + PATHCLIENT)
                    .request().cookie(cookieSessionId)
                    .get();
            // Receiving clients in string format in order to retrieving all of them via ObjectMapper with a List
            String stringClients = response.readEntity(String.class);
            List<ClientDTO> allClients = new ObjectMapper().readValue(stringClients, List.class);
            Assert.assertEquals(Ok, response.getStatus());
            Assert.assertEquals(1, allClients.size());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t08CreateDeveloperToDisable() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));

        if (cookieSessionId != null) {
            DeveloperDTO developer = developersOraculo.get(1);
            // Create a developer with a name existing in stormpath.
            developer.setName(System.getenv("APPOTECA_DEVELOPER_TODISABLE_USERNAME"));
            developer.setUserId(System.getenv("APPOTECA_DEVELOPER_TODISABLE_USERID"));
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATHDEVELOPER)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(developer, MediaType.APPLICATION_JSON));
            DeveloperDTO createdDeveloper = (DeveloperDTO) response.readEntity(DeveloperDTO.class);

            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdDeveloper);
            Assert.assertEquals(developer.getId(), createdDeveloper.getId());
            Assert.assertEquals(createdDeveloper.getName(), developer.getName());
            Assert.assertEquals(createdDeveloper.getUserId(), developer.getUserId());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t10DisableDeveloper() throws IOException {
        DeveloperDTO developerToDisable = new DeveloperDTO();
        
        Cookie cookieSessionIdGetDeveloper = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"), 
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        // Getting a developer to disable or enable the account
        if (cookieSessionIdGetDeveloper != null) {
            Client clienteWS = ClientBuilder.newClient();
            DeveloperDTO developer = developersOraculo.get(1);
            developer.setName(System.getenv("APPOTECA_DEVELOPER_TODISABLE_USERNAME"));
            
            // Find a developer to disable or enable by username (It dependes on current stormpath status)
            // It must exist in stormpath.
            developerToDisable = clienteWS.target(URLBASE + PATHDEVELOPER).path("/" + developer.getName())
                    .request().cookie(cookieSessionIdGetDeveloper).get(DeveloperDTO.class);
            Assert.assertNotNull(developerToDisable);
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
        
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        if (cookieSessionId != null) {
            Client clienteWS = ClientBuilder.newClient();
            // Changing developer status to disabled or enable (It dependes on current stormpath status)
            Response response = clienteWS.target(URLBASE + PATH + PATHDEVELOPER).path("/" + developerToDisable.getName() + "/disable")
                    .request().cookie(cookieSessionIdGetDeveloper)
                    .post(Entity.entity(developerToDisable, MediaType.APPLICATION_JSON));
            Assert.assertEquals(OkWithoutContent, response.getStatus());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t12CreateClientToDisable() throws IOException {
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));

        if (cookieSessionId != null) {
            ClientDTO client = clientsOraculo.get(1);
            // Create a client with a name existing in stormpath.
            client.setName(System.getenv("APPOTECA_CLIENT_TODISABLE_USERNAME"));
            client.setUserId(System.getenv("APPOTECA_CLIENT_TODISABLE_USERID"));
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATHCLIENT)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(client, MediaType.APPLICATION_JSON));
            ClientDTO createdClient = (ClientDTO) response.readEntity(ClientDTO.class);

            Assert.assertEquals(Created, response.getStatus());
            Assert.assertNotNull(createdClient);
            Assert.assertEquals(client.getId(), createdClient.getId());
            Assert.assertEquals(createdClient.getName(), client.getName());
            Assert.assertEquals(createdClient.getUserId(), client.getUserId());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }

    @Test
    @RunAsClient
    public void t14DisableClient() throws IOException {
        ClientDTO clientToDisable = new ClientDTO();
        
        Cookie cookieSessionIdGetClient = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"), 
                System.getenv("APPOTECA_CLIENT_PASSWORD"));
        // Getting a client to disable or enable the account
        if (cookieSessionIdGetClient != null) {
            Client clienteWS = ClientBuilder.newClient();
            ClientDTO client = clientsOraculo.get(1);
            client.setName(System.getenv("APPOTECA_CLIENT_TODISABLE_USERNAME"));
            
            // Find a client to disable or enable by username (It dependes on current stormpath status)
            // It must exist in stormpath.
            clientToDisable = clienteWS.target(URLBASE + PATHCLIENT).path("/" + client.getName())
                    .request().cookie(cookieSessionIdGetClient).get(ClientDTO.class);
            Assert.assertNotNull(clientToDisable);
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
        
        Cookie cookieSessionId = login(
                System.getenv("APPOTECA_ADMIN_USERNAME"), 
                System.getenv("APPOTECA_ADMIN_PASSWORD"));
        if (cookieSessionId != null) {
            Client clienteWS = ClientBuilder.newClient();
            // Changing client status to disabled or enable (It dependes on current stormpath status)
            Response response = clienteWS.target(URLBASE + PATH + PATHCLIENT).path("/" + clientToDisable.getName() + "/disable")
                    .request().cookie(cookieSessionIdGetClient)
                    .post(Entity.entity(clientToDisable, MediaType.APPLICATION_JSON));
            Assert.assertEquals(OkWithoutContent, response.getStatus());
        } else {
            Assert.fail("Access denied or Invalid credentials!");
        }
    }
}