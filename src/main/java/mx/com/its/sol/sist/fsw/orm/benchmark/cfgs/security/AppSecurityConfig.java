/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * The Spring Security configuration for the application - its a form login
 * config with authentication via session cookie (once logged in)
 * 
 * @author lentiummmx
 *
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key", userDetailsService());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
	public RequestMatcher csrfMatcher() {
		return new RequestMatcher() {
			/**
			 * Copy of default request matcher from
			 * CsrfFilter$DefaultRequiresCsrfMatcher
			 */
			private Pattern allowedMethods = Pattern.compile("^(GET|POST|HEAD|TRACE|OPTIONS)$");
			
			/*
			 * (non-Javadoc)
			 * @see org.springframework.security.web.util.matcher.RequestMatcher#matches(javax.servlet.http.HttpServletRequest)
			 */
			public boolean matches(HttpServletRequest request) {
				return !allowedMethods.matcher(request.getMethod()).matches();
			}
		};
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()).and().eraseCredentials(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/jsp-resources/**");
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().requireCsrfProtectionMatcher(csrfMatcher()).and()
			.formLogin()
				//.loginPage("/").permitAll()
				//.loginPage("/signin").permitAll()
				.loginPage("/login").permitAll()
				//.loginPage("/resources").permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/showMessage.html")
				.failureUrl("/login?error=1").permitAll().and()
			.logout().logoutUrl("/logout").permitAll().and()
			.rememberMe().rememberMeServices(rememberMeServices()).key("remember-me-key").and()
			.authorizeRequests()
				//.antMatchers("/", "/showMessage.html", 
				.antMatchers("/favicon.ico", "/login", "/register", "/logout").permitAll()
	            //.antMatchers("/resources/public/**").permitAll()
	            //.antMatchers("/resources/img/**").permitAll()
	            //.antMatchers("/resources/bower_components/**").permitAll()
				//.antMatchers("/resources/**").permitAll()	// see configure(WebSecurity web)
				//.antMatchers("/**").authenticated()
				.anyRequest().authenticated()
				/*-.antMatchers("/**").permitAll()-*/
				.and();
	}

}
