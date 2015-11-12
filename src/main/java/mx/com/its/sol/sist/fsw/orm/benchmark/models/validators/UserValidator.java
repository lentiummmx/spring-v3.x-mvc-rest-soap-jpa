/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.models.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mx.com.its.sol.sist.fsw.orm.benchmark.entities.User;
import mx.com.its.sol.sist.fsw.orm.benchmark.models.UserModel;

/**
 * @author ITS-AZJ
 *
 */
@Component
public class UserValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz) || UserModel.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.email");
	}

}
