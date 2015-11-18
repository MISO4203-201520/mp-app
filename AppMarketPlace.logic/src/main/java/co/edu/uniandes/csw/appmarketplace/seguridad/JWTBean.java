package co.edu.uniandes.csw.appmarketplace.seguridad;

import co.edu.uniandes.csw.appmarketplace.api.IJWTBeanLocal;
import co.edu.uniandes.csw.appmarketplace.dtos.UserDTO;
import co.edu.uniandes.csw.appmarketplace.ejbs.AutorizacionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import javax.ejb.Stateless;

/**
 * Created by andre on 25/09/2015.
 */

public class JWTBean implements IJWTBeanLocal {
    private RsaJsonWebKey rsaJsonWebKey;

    public JWTBean() {
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId(System.getenv("JWT_KEY"));
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }

    public String generateJWT(UserDTO user)  {

        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("client");  // who creates the token and signs it
        claims.setAudience("server"); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject("auth"); // the subject/principal is whom the token is about
        claims.setClaim("email", user.getEmail()); // additional claims/attributes about the subject can be added
        claims.setClaim("username", user.getUserName()); // additional claims/attributes about the subject can be added
        claims.setClaim("role", user.getRole()); // additional claims/attributes about the subject can be added
        claims.setClaim("name", user.getName()); // additional claims/attributes about the subject can be added
        claims.setClaim("lastName", user.getLastName()); // additional claims/attributes about the subject can be added
        claims.setClaim("password", user.getPassword()); // additional claims/attributes about the subject can be added

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        try {
            // Sign the JWS and produce the compact serialization or the complete JWT/JWS
            // representation, which is a string consisting of three dot ('.') separated
            // base64url-encoded parts in the form Header.Payload.Signature
            // If you wanted to encrypt it, you can simply set this jwt as the payload
            // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
            return jws.getCompactSerialization();
        } catch (Exception ex) {
            return null;
        }
    }

    public UserDTO verifyToken(String token) throws AutorizacionException {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("client") // whom the JWT needs to have been issued by
                .setExpectedAudience("server") // to whom the JWT is intended for
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instances
        JwtClaims jwtClaims = null; 
        try {
            jwtClaims = jwtConsumer.processToClaims(token); 
            UserDTO user =new UserDTO();
            user.setEmail(jwtClaims.getClaimValue("email").toString());
            user.setEmail(jwtClaims.getClaimValue("username").toString());
            user.setEmail(jwtClaims.getClaimValue("role").toString());
            user.setEmail(jwtClaims.getClaimValue("name").toString());
            user.setEmail(jwtClaims.getClaimValue("lastName").toString());
            user.setPassword(jwtClaims.getClaimValue("password").toString());
            return user;
        } catch (InvalidJwtException e) {           
            throw new AutorizacionException("El usuario no tiene acceso al recurso");
        }
        //System.out.println("JWT validation succeeded! " + jwtClaims);
        
    }
}