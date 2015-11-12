/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ITS-AZJ
 *
 */
public class OrmBenchmarkRuntimeException extends RuntimeException {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2792315506793064952L;

	private String errorCode;
	
	private String errorMessage;

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public OrmBenchmarkRuntimeException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getStackTrace()
	 */
	@Override
	@JsonIgnore
	public StackTraceElement[] getStackTrace() {
		// TODO Auto-generated method stub
		return super.getStackTrace();
	}

}
