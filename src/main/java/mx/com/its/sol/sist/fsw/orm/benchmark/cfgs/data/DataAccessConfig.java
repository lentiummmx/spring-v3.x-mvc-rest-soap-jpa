/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 
 * Implementation by @Configuration classes annotated
 * with @EnableTransactionManagement that wish to or need to explicitly specify
 * the default PlatformTransactionManager bean to be used for annotation-driven
 * transaction management, as opposed to the default approach of a by-type
 * lookup. One reason this might be necessary is if there are two
 * PlatformTransactionManager beans present in the container.
 * 
 * @author lentiummmx
 *
 */
@Configuration
@MapperScan(basePackages="mx.com.its.sol.sist.fsw.orm.benchmark.mappers")
@EnableJpaRepositories(basePackages="mx.com.its.sol.sist.fsw.orm.benchmark.repositories")
@EnableTransactionManagement
public class DataAccessConfig implements TransactionManagementConfigurer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessConfig.class);
	
	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		return basicDataSource;
	}
	
    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        driverManagerDataSource.setUrl(environment.getProperty("jdbc.url"));
		driverManagerDataSource.setUsername(environment.getProperty("jdbc.username"));
		driverManagerDataSource.setPassword(environment.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }

	@Bean
	public DataSource jndiDataSource() {
		LOGGER.debug("dataSource.jndi :: " + environment.getProperty("dataSource.jndi"));
		final JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		jndiDataSourceLookup.setResourceRef(true);
		DataSource jndiDataSource = jndiDataSourceLookup.getDataSource(environment.getProperty("dataSource.jndi"));
		return jndiDataSource;
	}
	
	private JpaVendorAdapter eclipseLinkJpaVendorAdapter() {
		EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
		eclipseLinkJpaVendorAdapter.setGenerateDdl(true);
		eclipseLinkJpaVendorAdapter.setShowSql(true);
		return eclipseLinkJpaVendorAdapter;
	}
	
	private JpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.TRUE);
		hibernateJpaVendorAdapter.setShowSql(Boolean.TRUE);
		return hibernateJpaVendorAdapter;
	}
	
	private Properties eclipselinkJpaProperties() {
		Properties ecslnkJpaProperties = new Properties();

		ecslnkJpaProperties.put("eclipselink.deploy-on-startup", environment.getProperty("eclipselink.deploy-on-startup"));
		ecslnkJpaProperties.put("eclipselink.ddl-generation", environment.getProperty("eclipselink.ddl-generation"));	//"create-or-extend-tables");
		ecslnkJpaProperties.put("eclipselink.ddl-generation.output-mode", environment.getProperty("eclipselink.ddl-generation.output-mode"));
		ecslnkJpaProperties.put("eclipselink.logging.level.sql", environment.getProperty("eclipselink.logging.level.sql"));
		ecslnkJpaProperties.put("eclipselink.logging.parameters", environment.getProperty("eclipselink.logging.parameters"));
		ecslnkJpaProperties.put("eclipselink.weaving", environment.getProperty("eclipselink.weaving"));
		ecslnkJpaProperties.put("eclipselink.weaving.lazy", environment.getProperty("eclipselink.weaving.lazy"));
		ecslnkJpaProperties.put("eclipselink.weaving.internal", environment.getProperty("eclipselink.weaving.internal"));
		ecslnkJpaProperties.put("eclipselink.logging.level", environment.getProperty("eclipselink.logging.level"));
		ecslnkJpaProperties.put("eclipselink.jdbc.batch-writing", environment.getProperty("eclipselink.jdbc.batch-writing"));
		ecslnkJpaProperties.put("eclipselink.jdbc.batch-writing.size", environment.getProperty("eclipselink.jdbc.batch-writing.size"));
		ecslnkJpaProperties.put("eclipselink.jdbc.cache-statements", environment.getProperty("eclipselink.jdbc.cache-statements"));
		ecslnkJpaProperties.put("eclipselink.jdbc.cache-statements.size", environment.getProperty("eclipselink.jdbc.cache-statements.size"));
		ecslnkJpaProperties.put("eclipselink.cache.shared.default", environment.getProperty("eclipselink.cache.shared.default"));
		ecslnkJpaProperties.put("eclipselink.flush-clear.cache", environment.getProperty("eclipselink.flush-clear.cache"));
		ecslnkJpaProperties.put("eclipselink.cache.size.default", environment.getProperty("eclipselink.cache.size.default"));
		ecslnkJpaProperties.put("eclipselink.target-database", environment.getProperty("eclipselink.target-database"));

		return ecslnkJpaProperties;
	}
	
	private Properties hibernateJpaProperties() {
		Properties hbtJpaProperties = new Properties();

		hbtJpaProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hbtJpaProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		hbtJpaProperties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		hbtJpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));	//"create-drop");
		hbtJpaProperties.put("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));
		hbtJpaProperties.put("hibernate.generate_statistics", environment.getProperty("hibernate.generate_statistics"));

		//hbtJpaProperties.put("hibernate.current_session_context_class", "thread");
		//hbtJpaProperties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
		
		return hbtJpaProperties;
	}
	
	private Map<String, String> hibernateJpaPropertyMap() {
		Map<String, String> hbtJpaPropertyMap = new HashMap<String, String>();

		hbtJpaPropertyMap.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hbtJpaPropertyMap.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		hbtJpaPropertyMap.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		hbtJpaPropertyMap.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));	//"create-drop");
		hbtJpaPropertyMap.put("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));
		hbtJpaPropertyMap.put("hibernate.generate_statistics", environment.getProperty("hibernate.generate_statistics"));

		//hbtJpaPropertyMap.put("hibernate.current_session_context_class", "thread");
		//hbtJpaPropertyMap.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
		
		return hbtJpaPropertyMap;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		LOGGER.debug("after defining LocalContainerEntityManagerFactoryBean entityManagerFactory()");
		entityManagerFactoryBean.setDataSource(jndiDataSource());
		//Configuración para utilizar EclipseLink como provedor de JPA
		entityManagerFactoryBean.setJpaProperties(eclipselinkJpaProperties());
		entityManagerFactoryBean.setJpaVendorAdapter(eclipseLinkJpaVendorAdapter());
		//Configuración para utilizar Hibernate como provedor de JPA
		//entityManagerFactoryBean.setJpaPropertyMap(hibernateJpaPropertyMap());
		//entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		entityManagerFactoryBean.setPackagesToScan(new String[]{"mx.com.its.sol.sist.fsw.orm.benchmark.entities"});
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getObject();
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
	 */
	@Bean(name = "transactionManager")
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		LOGGER.debug("inside PlatformTransactionManager annotationDrivenTransactionManager()");
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		//jpaTransactionManager.setDataSource(jndiDataSource());
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}

	public PlatformTransactionManager annotationDrivenTransactionManagerCustom() {
		return annotationDrivenTransactionManager();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
	
	/**
	 * Configuración necesaria para poder implementar persistencia con MyBatis
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean =  new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(jndiDataSource());
		sqlSessionFactoryBean.setTypeAliasesPackage("mx.com.itssoluciones.spring.orm.benchmark.entities");
		return sqlSessionFactoryBean;
	}
	
	/**
	 * Configuración necesaria para poder implementar persistencia con Hibernate
	 * 
	 * @return
	 */
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(jndiDataSource());
		localSessionFactoryBuilder.addProperties(hibernateJpaProperties());
		localSessionFactoryBuilder.scanPackages("mx.com.itssoluciones.spring.orm.benchmark.entities");
		return localSessionFactoryBuilder.buildSessionFactory();
	}
	
	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory());
		return hibernateTransactionManager;
	}
	
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
	
	/**
	 * Configuración necesaria para poder implementar persistencia con SpringJDBC
	 * 
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(jndiDataSource());
	}

}
