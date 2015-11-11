/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import mx.com.its.sol.sist.fsw.orm.benchmark.Application;

/**
 *
 * The root context configuration of the application - the beans in this context will be globally visible
 * in all servlet contexts.
 *
 * @author lentiummmx
 *
 */
@Configuration
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableAspectJAutoProxy
@PropertySource(value = { "classpath:application.properties", "classpath:persistence.properties" })
@ComponentScan(basePackageClasses=Application.class, excludeFilters=@Filter({Controller.class, EnableWebMvc.class}))
public class AppConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Deprecated
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		//propertyPlaceholderConfigurer.setLocation(new ClassPathResource("/persistence.properties"));
		return propertyPlaceholderConfigurer;
	}
	
}
