package com.knowledgehut.crm.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.knowledgehut.crm.exception.UnauthorizedRoleException;
import com.knowledgehut.crm.i18.MessageBundle;
import com.knowledgehut.crm.service.LoginService;
import com.knowledgehut.crm.util.ApplicationConstants;
import com.knowledgehut.crm.util.JdbcAuthRealm;
import com.knowledgehut.crm.util.SpringUtils;

@Component
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1167429274063992244L;

	@Autowired
	private LoginService loginService;

	private LoginBean loginBean;
	private String error;
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private void clearSubject() {
		Subject subject = SecurityUtils.getSubject();
		JdbcAuthRealm jdbcAuthRealm = (JdbcAuthRealm) SpringUtils.getBean("jdbcAuthRealm");
		if(jdbcAuthRealm != null) {
			jdbcAuthRealm.clear(subject.getPrincipals());			
		}
		subject.logout();
	}
	
	public String login() {
		HttpSession session = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		try {
			if (!StringUtils.hasText(loginBean.getLoginName()) || !StringUtils.hasText(loginBean.getPassword())) {
				setError(MessageBundle.getInstance().getString(ApplicationConstants.USER_LOGIN_ERROR));
				return null;
			}
			Subject currentUser = loginService.login(loginBean.getLoginName(), loginBean.getPassword());
			
			if (currentUser.isAuthenticated()) {
				session.setAttribute(ApplicationConstants.USERNAME_KEY, loginBean.getLoginName());
				return redirect(ApplicationConstants.USER_LOGIN_SUCCESSFUL_PAGE_URI);
			}
			else {
				setError(MessageBundle.getInstance().getString(ApplicationConstants.USER_LOGIN_ERROR));
				return null;
			}
			
		} catch(UnauthorizedRoleException e) {
			logger.error("Exception occurred while logging into the application. Message: {}", e);
			setError(e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occurred while logging into the application. Message: {}", e);
			setError(MessageBundle.getInstance().getString(ApplicationConstants.USER_LOGIN_EXCEPTION));
		}
		return null;
	}

	public String logout() {
		clearSubject();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return logoutPage();
	}

	public LoginBean getLoginBean() {
		if (loginBean == null) {
			loginBean = new LoginBean();
		}
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void setError(String error) {
		this.error = error;
		clearSubject();
	}
	public String getError() {
		return error;
	}
	
	public static class LoginBean implements Serializable {
		private static final long serialVersionUID = -3239163665106810435L;

		private String loginName;
		private String password;

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
