/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.ws.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;
import mx.com.its.sol.sist.fsw.orm.benchmark.services.UserService;
import mx.com.its.sol.sist.fsw.orm.benchmark.ws.soap.users.GetByIdRequest;
import mx.com.its.sol.sist.fsw.orm.benchmark.ws.soap.users.GetByIdResponse;
import mx.com.its.sol.sist.fsw.orm.benchmark.ws.soap.users.UserSoap;

/**
 * @author lentiummmx
 *
 */
@Endpoint
public class UserEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserEndpoint.class);
	
	public static final String USERS_TARGET_NAMESPACE_URI = "http://benchmark.orm.fsw.sist.sol.its.com.mx/ws/soap/users";
	
	@Autowired
	private UserService<User, Long> userService;
	
	@PayloadRoot(namespace=USERS_TARGET_NAMESPACE_URI, localPart="getByIdRequest")
	public @ResponsePayload GetByIdResponse getById(@RequestPayload GetByIdRequest byIdRequest) {
		GetByIdResponse byIdResponse = new GetByIdResponse();
		UserSoap userSoap = new UserSoap();
		User user = userService.findById(byIdRequest.getUserId());
		BeanUtils.copyProperties(user, userSoap);
		byIdResponse.setUser(userSoap);
		return byIdResponse;
	}
	
}
