/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.cfgs.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 
 * Set ups the utilities needed inside application, like: sending-mail, fileupload
 * excel files, etc.
 * 
 * @author lentiummmx
 *
 */
@Configuration
public class UtilsConfig {

	@Autowired
	private Environment env;

	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setDefaultEncoding("UTF-8");
		mailSenderImpl.setHost(env.getProperty("smtp.host"));
		mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
		mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
		mailSenderImpl.setUsername(env.getProperty("smtp.username"));
		mailSenderImpl.setPassword(env.getProperty("smtp.password"));

		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", env.getProperty("smtp.auth"));
		// javaMailProps.put("mail.smtp.starttls.enable",
		// env.getProperty("smtp.starttls.enable"));
		javaMailProps.put("mail.debug", env.getProperty("smtp.debug"));
		// javaMailProps.put("mail.smtp.socketFactory.class",
		// env.getProperty("smtp.socketFactory.port"));
		// javaMailProps.put("mail.smtp.socketFactory.port",
		// env.getProperty("smtp.socketFactory.class"));
		javaMailProps.put("mail.smtps.ssl.checkserveridentity", "false");
		javaMailProps.put("mail.smtps.ssl.trust", "*");
		mailSenderImpl.setJavaMailProperties(javaMailProps);

		return mailSenderImpl;
	}

}
