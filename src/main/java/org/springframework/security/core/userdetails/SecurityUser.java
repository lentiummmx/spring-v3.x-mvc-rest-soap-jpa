/**
 * 
 */
package org.springframework.security.core.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lentiummmx
 *
 */
public class SecurityUser extends User {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1697493648836573698L;

	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
