/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.ws.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;
import mx.com.its.sol.sist.fsw.orm.benchmark.services.UserService;

/**
 * @author lentiummmx
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserRestSvc {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestSvc.class);
	
	@Autowired
	private UserService<User, Long> userService;
	
	@RequestMapping(value="/findById/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public User findById(@PathVariable(value = "userId") Long userId) {
		User user = new User();
		user = userService.findById(userId);
		LOGGER.debug("findById - user :: " + user);
		return user;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User save(@RequestBody User user) {
		user = userService.save(user);
		LOGGER.debug("save - user :: " + user);
		return user;
	}
	
}
