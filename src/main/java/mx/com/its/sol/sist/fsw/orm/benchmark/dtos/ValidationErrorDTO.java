/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.dtos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lentiummmx
 *
 */
public class ValidationErrorDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1445643945718852868L;
	
	private List<FieldErrorDTO> fieldErrors;
	
	public ValidationErrorDTO() {
		this.fieldErrors = new LinkedList<FieldErrorDTO>();
	}

	/**
	 * @param fieldErrors
	 */
	public ValidationErrorDTO(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	/**
	 * 
	 * @param path
	 * @param message
	 */
	public void addFieldError(String path, String message) {
		FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(path, message);
		fieldErrors.add(fieldErrorDTO);
	}

	/**
	 * @return the fieldErrors
	 */
	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * @param fieldErrors the fieldErrors to set
	 */
	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
}
