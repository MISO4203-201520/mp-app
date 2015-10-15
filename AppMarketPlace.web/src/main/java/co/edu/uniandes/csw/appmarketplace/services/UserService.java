/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IAdminLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.AdminDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.DeveloperDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ForgotPasswordDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountStatus;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.shiro.realm.ApplicationRealm;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Jhonatan
 */
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @Inject
    private IClientLogic clientLogic;

    @Inject
    private IDeveloperLogic developerLogic;

    @Inject
    private IAdminLogic adminLogic;

    @Context
    private HttpServletRequest req;

    @Path("/login")
    @POST
    public Response login(UserDTO user) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword(), user.isRememberMe());
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            ClientDTO client = clientLogic.getClientByUserId(currentUser.getPrincipal().toString());
            if (client != null) {
                currentUser.getSession().setAttribute("Client", client);
            } else {
                DeveloperDTO provider = developerLogic.getDeveloperByUserId(currentUser.getPrincipal().toString());
                if (provider != null) {
                    currentUser.getSession().setAttribute("Developer", provider);
                } else {
                    AdminDTO admin = adminLogic.getAdminByUserId(currentUser.getPrincipal().toString());
                    if (admin != null) {
                        currentUser.getSession().setAttribute("Admin", admin);
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity(" User is not registered")
                                .type(MediaType.TEXT_PLAIN)
                                .build();
                    }
                }
            }
            return Response.ok(subjectToUserDTO(currentUser)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @Path("/logout")
    @GET
    public Response logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/currentUser")
    @GET
    public Response getCurrentUser() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            return Response.ok(subjectToUserDTO(currentUser)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    private UserDTO subjectToUserDTO(Subject subject) {
        String href = req.getRemoteUser();
        if (href != null) {
            UserDTO user = new UserDTO();
            Account account = getClient().getResource(href, Account.class);
            user.setName(account.getGivenName());
            user.setLastName(account.getSurname());
            user.setEmail(account.getEmail());
            user.setUserName(account.getUsername());
            user.setRole(account.getGroups().iterator().next().getName());
            return user;
        } else {
            return null;
        }
    }

    @Path("/register")
    @POST
    public Response setUser(UserDTO user) {
        try {
            Account account = createUser(user);
            switch (user.getRole()) {
                case "user":
                    ClientDTO client = new ClientDTO();
                    client.setName(user.getUserName());
                    client.setUserId(account.getHref());
                    clientLogic.createClient(client);
                    break;

                case "developer":
                    DeveloperDTO developer = new DeveloperDTO();
                    developer.setName(user.getUserName());
                    developer.setUserId(account.getHref());
                    developer.setFirstName(user.getName());
                    developer.setLastName(user.getLastName());
                    developer.setEmail(user.getEmail());
                    developerLogic.createDeveloper(developer);
                    break;
            }
            return Response.ok().build();
        } catch (ResourceException e) {
            return Response.status(e.getStatus())
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    private ApplicationRealm getRealm() {
        return ((ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next());
    }

    private Client getClient() {
        return getRealm().getClient();
    }

    private Application getApplication() {
        return getClient().getResource(getRealm().getApplicationRestUrl(), Application.class);
    }

    private Account createUser(UserDTO user) {
        Application application = getApplication();
        Account acct = getClient().instantiate(Account.class);
        acct.setUsername(user.getUserName());
        acct.setPassword(user.getPassword());
        acct.setEmail(user.getEmail());
        acct.setGivenName(user.getName());
        acct.setSurname(user.getLastName());
        acct.setStatus(AccountStatus.ENABLED);
        GroupList groups = application.getGroups();
        for (Group grp : groups) {
            if (grp.getName().equals(user.getRole())) {
                acct = application.createAccount(acct);
                acct.addGroup(grp);
                break;
            }
        }
        return acct;
    }

    @Path("/forgot")
    @POST
    public Response forgotPassword(UserDTO user) {
        ApplicationRealm realm = ((ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next());
        Client client = realm.getClient();
        Application application = client.getResource(realm.getApplicationRestUrl(), Application.class);
        try {
            Account account = application.sendPasswordResetEmail(user.getEmail());
            return Response.ok().build();
        } catch (ResourceException e) {
            return Response.status(e.getStatus())
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @Path("/verify")
    @GET
    public Response verifyToken(@QueryParam("sptoken") String token) {
        ApplicationRealm realm = ((ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next());
        Client client = realm.getClient();
        Application application = client.getResource(realm.getApplicationRestUrl(), Application.class);
        try {
            Account account = application.verifyPasswordResetToken(token);
            return Response.ok().type(MediaType.APPLICATION_JSON).build();
        } catch (ResourceException e) {
            return Response.status(e.getStatus())
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @Path("/change")
    @POST
    public Response changePassword(ForgotPasswordDTO forgot) {
        ApplicationRealm realm = ((ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next());
        Client client = realm.getClient();
        Application application = client.getResource(realm.getApplicationRestUrl(), Application.class);
        try {
            Account account = application.resetPassword(forgot.getToken(), forgot.getNewPassword());
            return Response.ok().type(MediaType.APPLICATION_JSON).build();
        } catch (ResourceException e) {
            return Response.status(e.getStatus())
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }
}
