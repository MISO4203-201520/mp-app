package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.dtos.*;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.CartItemService;

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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class CartItemServiceTest {

    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public final static String PATH = "/cartItems";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static List<CartItemDTO> data = new ArrayList<>();
    public static Cookie cookie_session_id = null;

    /**
     * @generated
     */
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
                .addPackage(CartItemService.class.getPackage())
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

    /**
     * @generated
     */
    @BeforeClass
    public static void setUp() throws IOException {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            CartItemDTO dto = factory.manufacturePojo(CartItemDTO.class);
            data.add(dto);
        }
    }

    //Metodo para autenticarse de ser necesario, recuerde que esto depende de los permisos que se encuentran en el archivo shiro.ini
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
     * @generated
     */
    @Test
    @RunAsClient
    public void t1CreateCartItem() {
        cookie_session_id = login(
                System.getenv("APPOTECA_DEVELOPER_USERNAME"),
                System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        CartItemDTO dto = data.get(0);
        Client cliente = ClientBuilder.newClient();
        PodamFactory factory = new PodamFactoryImpl();
        Response response;

        DeveloperDTO developer = factory.manufacturePojo(DeveloperDTO.class);
        developer.setName(System.getenv("APPOTECA_DEVELOPER_USERNAME"));
        Response response2 = cliente.target(URLBASE + "/developers")
                .request().cookie(cookie_session_id)
                .post(Entity.entity(developer, MediaType.APPLICATION_JSON));

        AppDTO app = factory.manufacturePojo(AppDTO.class);
        response = cliente.target(URLBASE).path("/apps")
                .request().cookie(cookie_session_id)
                .post(Entity.entity(app, MediaType.APPLICATION_JSON));
        app = (AppDTO) response.readEntity(AppDTO.class);
        dto.setApp(app);
        cookie_session_id = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));

        ClientDTO client = factory.manufacturePojo(ClientDTO.class);
        client.setName(System.getenv("APPOTECA_CLIENT_USERNAME"));
        response = cliente.target(URLBASE).path("/clients")
                .request().cookie(cookie_session_id)
                .post(Entity.entity(client, MediaType.APPLICATION_JSON));
        client = (ClientDTO) response.readEntity(ClientDTO.class);
        dto.setClient(client);
        cookie_session_id = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));

        response = cliente.target(URLBASE + PATH)
                .request().cookie(cookie_session_id)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        CartItemDTO result = (CartItemDTO) response.readEntity(CartItemDTO.class);
        Assert.assertEquals(dto.getId(), result.getId());
        Assert.assertEquals(Created, response.getStatus());
    }

    /**
     * @generated
     */
    @Ignore
    @Test
    @RunAsClient
    public void t2GetCartItemById() {
        cookie_session_id = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));
        Client cliente = ClientBuilder.newClient();
        CartItemDTO cartitemTest = cliente.target(URLBASE + PATH).path("/" + data.get(0).getId())
                .request().cookie(cookie_session_id).get(CartItemDTO.class);
        Assert.assertEquals(cartitemTest.getId(), data.get(0).getId());
    }

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t3GetCartItems() throws IOException {
        cookie_session_id = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATH)
                .request().cookie(cookie_session_id).get();
        String listCartItem = response.readEntity(String.class);
        List<CartItemDTO> listCartItemTest = new ObjectMapper().readValue(listCartItem, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listCartItemTest.size());
    }

    /**
     * @generated
     */
    @Ignore
    @Test
    @RunAsClient
    public void t4UpdateCartItem() throws IOException {
        cookie_session_id = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));
        Client cliente = ClientBuilder.newClient();
        CartItemDTO cartitem = cliente.target(URLBASE + PATH).path("/" + data.get(0).getId())
                .request().cookie(cookie_session_id).get(CartItemDTO.class);
        PodamFactory factory = new PodamFactoryImpl();
        CartItemDTO cartitemChanged = factory.manufacturePojo(CartItemDTO.class);
        cartitem.setId(cartitemChanged.getId());
        cartitem.setName(cartitemChanged.getName());
        cartitem.setQuantity(cartitemChanged.getQuantity());

        Response response = cliente.target(URLBASE + PATH).path("/" + cartitem.getId())
                .request().cookie(cookie_session_id).put(Entity.entity(cartitem, MediaType.APPLICATION_JSON));
        CartItemDTO cartitemTest = (CartItemDTO) response.readEntity(CartItemDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(cartitem.getId(), cartitemTest.getId());
        Assert.assertEquals(cartitem.getName(), cartitemTest.getName());
        Assert.assertEquals(cartitem.getQuantity(), cartitemTest.getQuantity());

    }

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t5DeleteCartItem() {
        cookie_session_id = login(
                System.getenv("APPOTECA_CLIENT_USERNAME"),
                System.getenv("APPOTECA_CLIENT_PASSWORD"));
        Client cliente = ClientBuilder.newClient();
        CartItemDTO cartitem = data.get(0);
        Response response = cliente.target(URLBASE + PATH).path("/" + cartitem.getId())
                .request().cookie(cookie_session_id).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
