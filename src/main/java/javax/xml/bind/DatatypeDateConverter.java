/**
 * 
 */
package javax.xml.bind;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author lentiummmx
 *
 */
public class DatatypeDateConverter {
	public static Date parseDate(String s) {
		return DatatypeConverter.parseDate(s).getTime();
	}

	public static String printDate(Date dt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDate(cal);
	}
}
