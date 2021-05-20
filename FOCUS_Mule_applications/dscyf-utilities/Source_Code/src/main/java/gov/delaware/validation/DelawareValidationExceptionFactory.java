package gov.delaware.validation;
import org.mule.api.MuleEvent;
import org.mule.extension.validation.api.ExceptionFactory;
import org.mule.extension.validation.api.ValidationResult;
public class DelawareValidationExceptionFactory  implements ExceptionFactory{

	@Override
	public <T extends Exception> T createException(ValidationResult result, Class<T> exceptionClass, MuleEvent event) {
		
		DelawareValidationResult validationResult = (DelawareValidationResult) result;
		return (T)  validationResult.getExceptionDetails();
	}

	@Override
	public Exception createException(ValidationResult result, String exceptionClassName, MuleEvent event) {
		DelawareValidationResult validationResult = (DelawareValidationResult) result;
		return validationResult.getExceptionDetails();
	}
}
