/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;
import mx.com.its.sol.sist.fsw.orm.benchmark.repositories.UserRepositoryCustom;

/**
 * @author lentiummmx
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom<User, Long> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findByUsernameRetNotNull(String username) {
		LOGGER.debug("findByUsernameRetNotNull :: " + username);
		return null;
	}
	
}
