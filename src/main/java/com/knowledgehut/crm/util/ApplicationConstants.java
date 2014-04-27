/**
 * 
 */
package com.knowledgehut.crm.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * All constants of the crm application should be mentioned here
 *
 */
@ManagedBean(name="appConstants")
@ApplicationScoped
public class ApplicationConstants {
	
	// constants related to authentication functionality
	public static final String USER_LOGIN_ERROR = "crm.login.invalid.user";
	public static final String USER_LOGIN_PASSWORD_ERROR ="crm.login.invalid.password";
	public static final String USER_LOGIN_EXCEPTION = "crm.login.exception";
	public static final String USER_LOGIN_SUCCESSFUL_PAGE_URI = "/jsp/dashboard.xhtml";
	public static final String USER_LOGIN_PAGE_URI = "/jsp/welcome.xhtml";
	public static final String USER_DASHBOARD_PAGE_URI = "/jsp/dashboard.xhtml";
	public static final String USER_LOGOUT_PAGE = "/jsp/logout.xhtml";
	public static final String USER_LOGIN_SERVLET = "LoginUser";
	public static final String USER_LOGOUT_SERVLET = "LogoutUser";
	public static final String USER_LOGOUT_PAGE_URI = "/jsp/logout.xhtml";
	public static final String USER_ERROR_PAGE_URI = "/jsp/error.xhtml";
	public static final String USER_LOGOUT_MESSAGE = "crm.logout.message";
	public static final String USER_LOGIN_INVALID_SESSION = "crm.login.invalid.session";	
	
	public static final String USERNAME_KEY="username";
	
	// ROLES
		public static final String ADMIN_ROLE_NAME = "b2b_admin";
		public static final String SUPPLIER_ROLE_NAME = "b2b_supplier";
		public static final String EXECUTIVE_ROLE_NAME = "b2b_executive";
		public static final String SUPER_USER_ROLE_NAME = "b2b_super_user";
		
		 public enum FileType {
       SI("Supplier Inventory");
       private String label;

       FileType(String label) {
           this.label = label;
       }

       public String getLabel() {
           return label;
       }
   }

    public enum Severity{
		
		INFO(0),
	    WARN(1),
	    ERROR(2),
	    FATAL(3);
	    
		private Integer level;

	    Severity(int level) {
	        this.level = level;
	    }
	    
	    public Integer getLevel() {
			return level;
		}
	};
	
}

