/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs;

import org.springframework.core.annotation.Order;
import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

import mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.ws.WebServiceConfig;

/**
 * @author lentiummmx
 *
 */
@Order(3)
public class WebServiceAppInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebServiceConfig.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.ws.transport.http.support.AbstractMessageDispatcherServletInitializer#isTransformWsdlLocations()
	 */
	@Override
	public boolean isTransformWsdlLocations() {
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see org.springframework.ws.transport.http.support.AbstractMessageDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/ws/*" };
	}

}
