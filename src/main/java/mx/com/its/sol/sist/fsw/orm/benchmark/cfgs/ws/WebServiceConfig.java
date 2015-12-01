/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.ws;

import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.WebServiceProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import mx.com.its.sol.sist.fsw.orm.benchmark.ws.soap.UserEndpoint;

/**
 * @author lentiummmx
 *
 */
@EnableWs
@Configuration
@ComponentScan(basePackages = { "mx.com.its.sol.sist.fsw.orm.benchmark.ws.soap" },
		includeFilters = @Filter({ Endpoint.class }),
		excludeFilters = { @Filter(WebService.class), @Filter(WebServiceProvider.class) },
		useDefaultFilters = false)
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Bean
	public XsdSchema usersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/users.xsd"));
	}
	
	@Bean(name = "users")
	public DefaultWsdl11Definition defaultWsdl11Definition() {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("UsersPort");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace(UserEndpoint.USERS_TARGET_NAMESPACE_URI);
		defaultWsdl11Definition.setSchema(usersSchema());
		return defaultWsdl11Definition;
	}

	/* (non-Javadoc)
	 * @see org.springframework.ws.config.annotation.WsConfigurerAdapter#addInterceptors(java.util.List)
	 */
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		PayloadValidatingInterceptor payloadValidatingInterceptor = new PayloadValidatingInterceptor();
		payloadValidatingInterceptor.setXsdSchema(usersSchema());
		payloadValidatingInterceptor.setValidateRequest(true);
		payloadValidatingInterceptor.setValidateResponse(true);
		interceptors.add(payloadValidatingInterceptor);
		
		interceptors.add(new PayloadLoggingInterceptor());
	}
	
}
