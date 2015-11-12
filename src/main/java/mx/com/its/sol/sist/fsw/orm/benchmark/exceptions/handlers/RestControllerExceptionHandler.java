/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.exceptions.handlers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Throwables;

import mx.com.its.sol.sist.fsw.orm.benchmark.dtos.ValidationErrorDTO;
import mx.com.its.sol.sist.fsw.orm.benchmark.exceptions.OrmBenchmarkException;
import mx.com.its.sol.sist.fsw.orm.benchmark.exceptions.OrmBenchmarkRuntimeException;

/**
 * @author ITS-AZJ
 *
 */
@ControllerAdvice
public class RestControllerExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody OrmBenchmarkException allExceptionsResponse(Exception exception) {
		exception.printStackTrace(System.err);
		/*
		return new OrmBenchmarkException(
				exception.getCause() != null ?
						exception.getCause().getMessage() : exception.getMessage());
		*/
		return new OrmBenchmarkException(Throwables.getRootCause(exception));
	}
	
	@ExceptionHandler(value = OrmBenchmarkRuntimeException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody OrmBenchmarkRuntimeException customExceptionResponse(OrmBenchmarkRuntimeException exception) {
		exception.printStackTrace(System.err);
		exception.setErrorMessage(resolveLocalizedErrorMessage(exception));
		return exception;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO processValidationError(MethodArgumentNotValidException exception) {
		exception.printStackTrace(System.err);
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}
 
	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

		// If the message was not found, return the most accurate field error
		// code instead.
		// You can remove this check if you prefer to get the default error
		// message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}

	private String resolveLocalizedErrorMessage(OrmBenchmarkRuntimeException exception) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(exception.getErrorCode(), null,
				exception.getErrorMessage(), currentLocale);
		return localizedErrorMessage;
	}

}
