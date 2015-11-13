/**
 * 
 */
package javax.validation.mask;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.MaskFormat;

/**
 * @author lentiummmx
 *
 */
public class MaskFormatterValidation implements ConstraintValidator<MaskFormat, String> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MaskFormatterValidation.class);

	private MaskFormatter delegate;
	
	private String maskFormat;

	@Override
	public void initialize(MaskFormat constraintAnnotation) {
		this.maskFormat = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//To force data be fullfill
		/*
		if(value == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{NotNull}").addConstraintViolation();
			return false;
		}
		 */
		
		if(value == null) {
			return true;
		} else {
			try {
				this.delegate = new MaskFormatter(this.maskFormat);
				this.delegate.setValueContainsLiteralCharacters(false);
				this.delegate.valueToString(value);
			} catch (ParseException e) {
				//throw new IllegalStateException("Mask could not be parsed " + this.maskFormat, e);
				LOGGER.error("Mask {} or Value {} could not be parsed ", this.maskFormat , value);
				LOGGER.error("", e);
				return false;
			}
			return true;
		}
	}

}
