/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lentiummmx
 *
 */
public class OrmBenchmarkException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4719681854368645684L;

	/**
	 * 
	 */
	public OrmBenchmarkException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public OrmBenchmarkException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param message
	 */
	public OrmBenchmarkException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param cause
	 */
	public OrmBenchmarkException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
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
