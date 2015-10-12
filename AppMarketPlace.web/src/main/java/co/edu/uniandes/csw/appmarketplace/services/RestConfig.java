package co.edu.uniandes.csw.appmarketplace.services;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("webresources")
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        packages("co.edu.uniandes.csw.appmarketplace.services");
        packages("co.edu.uniandes.csw.appmarketplace.providers");
        register(MultiPartFeature.class);
        register(LoggingFilter.class);
    }
}
