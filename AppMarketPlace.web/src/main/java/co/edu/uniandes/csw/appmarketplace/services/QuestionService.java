/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.services;

import co.edu.uniandes.csw.appmarketplace.api.IAppLogic;
import co.edu.uniandes.csw.appmarketplace.api.IDeveloperLogic;
import co.edu.uniandes.csw.appmarketplace.api.IQuestionLogic;
import co.edu.uniandes.csw.appmarketplace.dtos.AppDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.appmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.appmarketplace.providers.StatusCreated;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.client.Client;
import com.stormpath.shiro.realm.ApplicationRealm;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author ca.forero10
 */
@Path("/question")
public class QuestionService {
    @Inject private IQuestionLogic questionLogic;
    @Inject private IAppLogic appLogic;
    
    @POST
    @StatusCreated
    @Consumes("application/json")
    public void createQuestion(QuestionDTO dto) {
        Subject currentUser = SecurityUtils.getSubject();
        ClientDTO client = (ClientDTO) currentUser.getSession().getAttribute("Client");
        if(client==null){          
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, new Exception("User is not a registered client"));
            return;
        }        
        Map<String, String> userAttributes = (Map<String, String>) currentUser.getPrincipals().oneByType(java.util.Map.class);
        AppDTO app = appLogic.getApp(dto.getApp().getId());
        if(app!=null){            
            dto.setApp(app);
            dto.setDate(new Date());
            dto.setClient(client);
            dto.setEmail(userAttributes.get("email"));
            
            ApplicationRealm realm = (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
            Client cli = realm.getClient();
            Account account = cli.getResource(app.getDeveloper().getUserId(), Account.class);
            
            questionLogic.doQuestion(dto, app.getDeveloper(), app, account.getEmail());
        }        
    }
}
