/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.test.services;

import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.appmarketplace.services.QuestionService;
import co.edu.uniandes.csw.appmarketplace.services.UserService;
import static co.edu.uniandes.csw.appmarketplace.test.services.AdminServiceTest.OkWithoutContent;
import static co.edu.uniandes.csw.appmarketplace.test.services._TestUtil.generateRandom;
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
 * @author jd.patino10
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class UsersServiceTest {

    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/AppMarketPlace.web/webresources";
    public final static String PATH = "/users";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static int badRequest = 400;

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
                .addPackage(UserService.class.getPackage())
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
    
    @Test
    @RunAsClient
    public void t1Login(){
        Client cliente = ClientBuilder.newClient();
        UserDTO admin = new UserDTO();
        admin.setUserName(System.getenv("APPOTECA_ADMIN_USERNAME"));
        admin.setPassword(System.getenv("APPOTECA_ADMIN_PASSWORD"));
        UserDTO dev = new UserDTO();
        dev.setUserName(System.getenv("APPOTECA_CLIENT_USERNAME"));
        dev.setPassword(System.getenv("APPOTECA_CLIENT_PASSWORD"));
        UserDTO user = new UserDTO();
        user.setUserName(System.getenv("APPOTECA_DEVELOPER_USERNAME"));
        user.setPassword(System.getenv("APPOTECA_DEVELOPER_PASSWORD"));
        PodamFactory factory = new PodamFactoryImpl();
        UserDTO wrong = new UserDTO();
        wrong.setUserName(generateRandom(String.class));
        wrong.setPassword(generateRandom(String.class));
        //Log an admin
        Response response = cliente.target(URLBASE+PATH).path("/login").request().
                post(Entity.entity(admin, MediaType.APPLICATION_JSON));

        UserDTO foundUser = (UserDTO) response.readEntity(UserDTO.class);
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(Ok, response.getStatus());
        //Log a Dev
        response = cliente.target(URLBASE+PATH).path("/login").request().
                post(Entity.entity(dev, MediaType.APPLICATION_JSON));

        foundUser = (UserDTO) response.readEntity(UserDTO.class);
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(Ok, response.getStatus());
        //Log a user
        response = cliente.target(URLBASE+PATH).path("/login").request().
                post(Entity.entity(user, MediaType.APPLICATION_JSON));

        foundUser = (UserDTO) response.readEntity(UserDTO.class);
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(Ok, response.getStatus());
        //Log a Bad user
        response = cliente.target(URLBASE+PATH).path("/login").request().
                post(Entity.entity(wrong, MediaType.APPLICATION_JSON));
        Assert.assertEquals(badRequest, response.getStatus());
        try{
            foundUser = (UserDTO) response.readEntity(UserDTO.class);   
            Assert.fail("No deberia llegar aquí");
        }catch(Exception e){
            
        }
    }
}
