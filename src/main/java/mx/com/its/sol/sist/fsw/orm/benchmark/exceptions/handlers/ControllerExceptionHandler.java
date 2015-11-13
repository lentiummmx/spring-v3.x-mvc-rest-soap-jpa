/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.exceptions.handlers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

import mx.com.its.sol.sist.fsw.orm.benchmark.exceptions.OrmBenchmarkException;
import mx.com.its.sol.sist.fsw.orm.benchmark.exceptions.OrmBenchmarkRuntimeException;

/**
 * @author lentiummmx
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Handle exceptions thrown by handlers.
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView allExceptions(Exception exception, WebRequest request) {
		OrmBenchmarkException ormBenchmarkException = new OrmBenchmarkException(Throwables.getRootCause(exception));
		ModelAndView modelAndView = new ModelAndView("error/general");
		modelAndView.addObject("error", ormBenchmarkException);
		modelAndView.addObject("errorMessage", ormBenchmarkException.getCause());
		return modelAndView;
	}
	
	@ExceptionHandler(value = OrmBenchmarkRuntimeException.class)
	public ModelAndView customException(OrmBenchmarkRuntimeException exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/general");
		modelAndView.addObject("error", exception);
		modelAndView.addObject("errorMessage", resolveLocalizedErrorMessage(exception));
		return modelAndView;
	}
 
	private String resolveLocalizedErrorMessage(OrmBenchmarkRuntimeException exception) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(exception.getErrorCode(), null,
				exception.getErrorMessage(), currentLocale);
		return localizedErrorMessage;
	}

}
