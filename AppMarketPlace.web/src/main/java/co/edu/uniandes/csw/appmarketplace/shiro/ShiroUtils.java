package co.edu.uniandes.csw.appmarketplace.shiro;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.shiro.realm.ApplicationRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

public abstract class ShiroUtils {

    private ShiroUtils() {
    }

    public static ApplicationRealm getRealm() {
        return (ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next();
    }

    public static Client getClient() {
        return getRealm().getClient();
    }

    public static Application getApplication() {
        return getClient().getResource(getRealm().getApplicationRestUrl(), Application.class);
    }
}
