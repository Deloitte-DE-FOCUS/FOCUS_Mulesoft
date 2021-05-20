package gov.delaware.validation;

import java.util.Date;
import org.apache.commons.validator.routines.DateValidator;

public class DateValidationUtil {

	
	public static boolean dateValidate(String textDate, String format) {
		if (textDate.length() != format.length())
			return true;
	/* Using Apache Common's DateValidator to validate a string with a given pattern */
		Date d = DateValidator.getInstance().validate(textDate, format); 
		if (d == null)
			return true;

		return false;
	}

}
