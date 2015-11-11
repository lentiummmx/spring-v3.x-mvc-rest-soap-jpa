/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;
import mx.com.its.sol.sist.fsw.orm.benchmark.repositories.UserRepository;
import mx.com.its.sol.sist.fsw.orm.benchmark.services.UserService;

/**
 * @author lentiummmx
 *
 */
@Service
public class UserServiceImpl implements UserService<User, Long> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		LOGGER.debug("before save user :: " + user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		LOGGER.debug("after save user :: " + user);
		return user;
	}

	@Override
	public User findById(Long userId) {
		//return userRepository.findOne(userId);
		return userRepository.findById(userId);
	}

}
