package com.knowledgehut.crm.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledgehut.crm.common.CurrentUser;
import com.knowledgehut.crm.entities.UserEntity;
import com.knowledgehut.crm.exception.UnauthorizedRoleException;
import com.knowledgehut.crm.repositories.UserRepository;
import com.knowledgehut.crm.util.ApplicationConstants;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CurrentUser currentUser;

	
	private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	public Subject login(String loginName, String password) {
		Subject user = SecurityUtils.getSubject();
		user.login(new UsernamePasswordToken(loginName, password));
		
		if (user.isAuthenticated()) {
			checkRoles(user, loginName);
			setCurrentUser(user, loginName);
		}
		return user;
	}

	private void setCurrentUser(Subject subject, final String loginName) {
		UserEntity user = userRepository
			.findOne(new org.springframework.data.jpa.domain.Specification<UserEntity>() {
				public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
					return cb.equal(root.get("loginName"), loginName);
				}
			});

		currentUser.setLoginTime(new Date());
		currentUser.setUserId(user.getId());
		currentUser.setUserName(user.getLoginName());

	}
	

	private void checkRoles(Subject subject, String loginName) {
		if(subject.hasRole(ApplicationConstants.MANAGER_ROLE_NAME) || 
				subject.hasRole(ApplicationConstants.EXECUTIVE_ROLE_NAME) ||
				subject.hasRole(ApplicationConstants.SUPER_USER_ROLE_NAME) || 
				subject.hasRole(ApplicationConstants.ADMIN_ROLE_NAME)){
			return;
		} else {
			throw new UnauthorizedRoleException("User: "+ loginName + " is not authorized to login into CRM!");
		}
	}
}
