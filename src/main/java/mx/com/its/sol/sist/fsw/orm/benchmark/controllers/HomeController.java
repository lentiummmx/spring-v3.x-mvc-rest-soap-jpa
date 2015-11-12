/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lentiummmx
 *
 */
@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/signin")
	public String signin() {
		LOGGER.debug("inside signin before render");
		return "signin";
	}
	
	@RequestMapping(value="/showMessage.html")
	public ModelAndView showMessage() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "Test message");
		LOGGER.debug("before render page :: " + model);
		return new ModelAndView("showMessage", model);
	}
	
}
