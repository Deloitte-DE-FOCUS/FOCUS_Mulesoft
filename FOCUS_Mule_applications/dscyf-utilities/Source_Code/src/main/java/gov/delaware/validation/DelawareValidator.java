package gov.delaware.validation;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.extension.validation.api.ValidationResult;
import org.mule.extension.validation.api.Validator;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
public class DelawareValidator implements Validator {

	final ConnectionExceptionCode interfaceSFDCConnectionCode = ConnectionExceptionCode.UNKNOWN;
	final String thirdPartyCode = "";
	
	@Override
	public ValidationResult validate(MuleEvent event) {
		
		boolean isError = false;
		DelawareValidationResult result = null;
		Exception exception = null;
		MuleMessage message = event.getMessage();
		Integer SFDCConnectionErrorCount = message.getInvocationProperty("total_sfdc_con_errorcount",0);
		String exceptionDetails = message.getInvocationProperty("AggregatedErrorDetails","");
/*		System.out.println("ExceptionDetails : " + exceptionDetails);
		System.out.println("SFDCConnectionErrorCount : " + SFDCConnectionErrorCount);
*/		if(exceptionDetails !=null && exceptionDetails.trim().equals(""))
		{
			isError = false;
			result = new DelawareValidationResult(null, isError, null);
			//System.out.println("NO exceptionDetails : " + exceptionDetails);
			return result;
		}
		
		if(SFDCConnectionErrorCount > 0){
			exception = new ConnectionException(interfaceSFDCConnectionCode, thirdPartyCode, exceptionDetails);
			isError = true;
			result = new DelawareValidationResult(exceptionDetails, isError, exception);
			//System.out.println("Returning SFDCConnectionException");
			//System.out.println(result.getExceptionDetails().getMessage());
			return result;
		}
		else{
			exception = new Exception(exceptionDetails);
			isError = true;
			result = new DelawareValidationResult(exceptionDetails, isError, exception);
			//System.out.println("Returning Exception");
			return result;
		}
		
	}

}
