/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;
import mx.com.its.sol.sist.fsw.orm.benchmark.models.UserModel;
import mx.com.its.sol.sist.fsw.orm.benchmark.models.validators.UserValidator;
import mx.com.its.sol.sist.fsw.orm.benchmark.services.UserService;

/**
 * @author lentiummmx
 *
 */
@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService<User, Long> userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new UserModel());
		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model) {
		request.getSession().invalidate();
		model.addAttribute("user", new UserModel());
		LOGGER.debug("after invalidateSession");
		return "redirect:login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registrationForm(Model model) {
		model.addAttribute("user", new UserModel());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String handleRegistration(@Valid @ModelAttribute("user") UserModel userModel, BindingResult errors, Model model) {
		if (errors.hasErrors()) {
			return "register";
		}
		try {
			User user = new User();
			BeanUtils.copyProperties(user, userModel);
			userService.save(user);
			return "redirect:login";
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error al registrar el usuario.", e);
			model.addAttribute("ERROR", e.getMessage());
			return "register";
		}
	}

}
