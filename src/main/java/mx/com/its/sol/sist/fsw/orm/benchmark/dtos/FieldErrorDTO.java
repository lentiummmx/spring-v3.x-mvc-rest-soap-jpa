/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.dtos;

import java.io.Serializable;

/**
 * @author ITS-AZJ
 *
 */
public class FieldErrorDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1928450764145152788L;

	private String field;
	
	private String message;

	/**
	 * @param field
	 * @param message
	 */
	public FieldErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
