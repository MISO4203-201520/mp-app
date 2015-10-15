/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.shiro;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

/**
 *
 * @author Jonatan
 */
public class AuthzFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);   
        return SecurityUtils.getSubject().isAuthenticated();
    }
    
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if (mappedValue != null) {
            String[] methodRef = (String[]) mappedValue;
            for (String methodRef1 : methodRef) {
                String[] data = methodRef1.split(":");
                if (data.length > 1) {
                    String[] methods = data[1].split("-");
                    for (String method : methods) {
                        if (((HttpServletRequest) request).getMethod().equals(method)) {
                            return SecurityUtils.getSubject().hasRole(data[0]);
                        }
                    }
                } else {
                    return super.isAccessAllowed(request, response, mappedValue);
                }
                return true;
            }
            return true;
        } else {
            return true;
        }
    }

}
