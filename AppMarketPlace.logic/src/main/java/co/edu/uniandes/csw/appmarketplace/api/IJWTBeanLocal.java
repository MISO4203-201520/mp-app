package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.ejbs.AutorizacionException;

import org.jose4j.lang.JoseException;

import javax.ejb.Local;

/**
 * Created by andre on 26/09/2015.
 */
@Local
public interface IJWTBeanLocal {
    public String generateJWT(UserDTO user);
    public UserDTO verifyToken(String token) throws AutorizacionException;
}
