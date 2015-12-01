/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.data.DataAccessConfig;
import mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.filter.CustomConfigurableSiteMeshFilter;
import mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.security.AppSecurityConfig;
import mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.util.UtilsConfig;
import mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.web.WebMvcConfig;

/**
 *
 * Replacement for most of the content of web.xml, sets up the root and the servlet context config.
 *
 * @author lentiummmx
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class, DataAccessConfig.class, AppSecurityConfig.class, UtilsConfig.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		//Instead we used SecurityWebApplicationInitializer
		//DelegatingFilterProxy springSecurityFilter = new DelegatingFilterProxy("springSecurityFilterChain");
		
		//Instead we used CustomConfigurableSiteMeshFilter
		//ConfigurableSiteMeshFilter configurableSiteMeshFilter = new ConfigurableSiteMeshFilter();
		CustomConfigurableSiteMeshFilter configurableSiteMeshFilter = new CustomConfigurableSiteMeshFilter();
		
		MultipartFilter multipartFilter = new MultipartFilter();
		
		return new Filter[]{characterEncodingFilter, configurableSiteMeshFilter, multipartFilter};	//, springSecurityFilter};
	}
	
	/**
	 * LOCATION <br> Temporary location where files will be stored
	 */
	private static final String LOCATION = "/temp/";
	
	/**
	 * MAX_FILE_SIZE <br> 5MB : Max file size. Beyond that size spring will throw exception.
	 */
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 5;
	
	/**
	 * MAX_REQUEST_SIZE <br> 20MB : Total request size containing Multi part.
	 */
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 20;
	
	/**
	 * FILE_SIZE_THRESHOLD <br> Size threshold after which files will be written to disk
	 */
	private static final int FILE_SIZE_THRESHOLD = 0;
	
	/**
	 * 
	 * @return
	 */
	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#customizeRegistration(javax.servlet.ServletRegistration.Dynamic)
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("defaultHtmlEscape", "true");
		//registration.setInitParameter("spring.profiles.active", "default");
		registration.setMultipartConfig(getMultipartConfigElement());
	}
	
}
