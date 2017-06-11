package pers.train.admin.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;


/**
 * 
 * Allows access if current user has at least one role of the specified list.
 * @author mingshan
 *
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
	    Subject subject = getSubject(request, response);  
        String[] rolesArray = (String[]) mappedValue;  
        //没有角色限制，有权限访问  
        if (rolesArray == null || rolesArray.length == 0) { 
            return true;  
        }  
        for (int i = 0; i < rolesArray.length; i++) {  
        	//若当前用户是rolesArray中的任何一个，则有权限访问  
        	if (subject.hasRole(rolesArray[i])) { 
                return true;  
            }  
        }  
  
        return false;  
	}

}
