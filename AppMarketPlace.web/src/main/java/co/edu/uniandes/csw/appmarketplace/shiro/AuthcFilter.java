/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.shiro;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 *
 * @author Jhonatan
 */
public class AuthcFilter extends FormAuthenticationFilter {

    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return SecurityUtils.getSubject().isAuthenticated();
    }
    
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (mappedValue != null) {
            String[] methodRef = (String[]) mappedValue;
            for (String methodRef1 : methodRef) {
                if (((HttpServletRequest) request).getMethod().equals(methodRef1)) {
                   return false;
                }
            }
            return true;
        }else{
           return super.isAccessAllowed(request, response, mappedValue);
        }
    }

}
