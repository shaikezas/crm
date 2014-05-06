package com.knowledgehut.crm.util;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JdbcAuthRealm extends JdbcRealm {

	public JdbcAuthRealm() {
		// default config to resolve permissions & cache it while initializing
		// AuthorizationInfo
		permissionsLookupEnabled = true;
		setCacheManager(new MemoryConstrainedCacheManager());
	}

	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return super.doGetAuthorizationInfo(principals);
	}
	
	public void clear(PrincipalCollection principals) {
		super.clearCache(principals);
	}
}
