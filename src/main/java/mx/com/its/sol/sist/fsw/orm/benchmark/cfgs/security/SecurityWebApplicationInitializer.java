/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Sets up the Spring Security filter chain
 * 
 * @author lentiummmx
 *
 */
@Order(2)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
