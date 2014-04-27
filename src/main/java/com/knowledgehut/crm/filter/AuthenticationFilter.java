/**
 * 
 */
package com.knowledgehut.crm.filter;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knowledgehut.crm.i18.MessageBundle;
import com.knowledgehut.crm.util.ApplicationConstants;

public class AuthenticationFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	public void init(FilterConfig filterconfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {/*
		
			HttpServletRequest request = (HttpServletRequest) servletrequest;
			HttpServletResponse response = (HttpServletResponse) servletresponse;

			String requestURI = request.getRequestURI();
			Subject loggedInUser = SecurityUtils.getSubject();
			
			if (!loggedInUser.isAuthenticated() && !requestURI.contains(ApplicationConstants.USER_LOGIN_PAGE_URI)
					&& !request.getRequestURI().contains(ApplicationConstants.USER_LOGIN_SERVLET)) {
				request.getSession().setAttribute("error", MessageBundle.getInstance().getString(ApplicationConstants.USER_LOGIN_INVALID_SESSION));
				response.sendRedirect(request.getContextPath()+ApplicationConstants.USER_LOGIN_PAGE_URI);
				return;
			}
			
			if (requestURI.contains(ApplicationConstants.USER_LOGOUT_PAGE_URI)) {
				filterchain.doFilter(request, response);
				//response.sendRedirect(request.getContextPath()+ApplicationConstants.USER_LOGOUT_PAGE_URI);
				return;
			}
			if(loggedInUser.isAuthenticated() && requestURI.endsWith(".xhtml") && 
					!isAllowedForAll(requestURI)) {
				//check for permissions here.
				String pageName = requestURI.substring(requestURI.lastIndexOf('/') + 1, requestURI.indexOf('.'));
				try {
					loggedInUser.checkPermission(pageName);
				}catch(AuthorizationException e) {
					log.error("User: {} doesn't have permission to view page: " + pageName, loggedInUser.getPrincipal(), e);
					request.setAttribute("error", "You don't have permissions to view this page.");
			        
			        RequestDispatcher dispatcher = request.getRequestDispatcher(ApplicationConstants.USER_ERROR_PAGE_URI);
			        dispatcher.forward(request, response);
					return;
				}
			}
			filterchain.doFilter(request, response);
	*/}
	
	boolean isAllowedForAll(String uri) {
	    return uri.contains(ApplicationConstants.USER_LOGOUT_PAGE_URI) || 
	    		uri.contains(ApplicationConstants.USER_ERROR_PAGE_URI) ||
	    		uri.contains(ApplicationConstants.USER_LOGIN_PAGE_URI)|| 
	    		uri.contains(ApplicationConstants.USER_DASHBOARD_PAGE_URI);
	}

	public void destroy() {
		
	}

}