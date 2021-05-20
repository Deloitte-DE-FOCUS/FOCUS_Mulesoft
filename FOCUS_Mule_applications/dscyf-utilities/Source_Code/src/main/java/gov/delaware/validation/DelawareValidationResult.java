package gov.delaware.validation;
import org.mule.extension.validation.api.ValidationResult;
public class DelawareValidationResult implements ValidationResult{

	private  String message;
	private  boolean error;
	private Exception exception;
	
	public DelawareValidationResult(String message, boolean error, Exception exception) {
		super();
		this.message = message;
		this.error = error;
		this.exception = exception;
	}

	@Override
	public String getMessage() {
		
		return this.message;
	}

	@Override
	public boolean isError() {
		
		return this.error;
	}
	
	public Exception getExceptionDetails()
	{
		return this.exception;
		
	}
	
}
