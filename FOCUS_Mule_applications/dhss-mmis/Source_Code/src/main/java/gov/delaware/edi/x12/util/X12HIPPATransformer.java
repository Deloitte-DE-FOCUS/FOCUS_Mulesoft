package gov.delaware.edi.x12.util;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.message.InterchangeEnvelope;

public class X12HIPPATransformer extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		
		String elementSeparator = message.getProperty("elementSeparator", PropertyScope.INVOCATION); //Retrieve Flow Variable
		String componentSeparator = message.getProperty("componentSeparator", PropertyScope.INVOCATION); //Retrieve Flow Variable
		String segmentSeparator = message.getProperty("segmentSeparator", PropertyScope.INVOCATION); //Retrieve Flow Variable
		if(segmentSeparator.equals("LF") || segmentSeparator.equals("CRLF") )
            segmentSeparator = System.lineSeparator();
		Writer.setElementTerminator(elementSeparator);
		Writer.setComponentTerminator(componentSeparator);
		Writer.setSegmentTerminator((segmentSeparator));
		InterchangeEnvelope interchangeEnvelope = (InterchangeEnvelope) message.getPayload();
		return interchangeEnvelope.toString();
	}

}
