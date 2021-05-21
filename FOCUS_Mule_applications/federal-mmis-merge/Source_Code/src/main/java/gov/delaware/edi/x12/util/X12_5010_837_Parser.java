package gov.delaware.edi.x12.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import javax.xml.namespace.QName;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import gov.delaware.edi.x12.HealthCareClaim837.Reader;
import gov.delaware.edi.x12.message.InterchangeEnvelope;
import gov.delaware.edi.x12.segment.BHT;

/* Rahul - 3/24/2017 : Parser limitation : Reads entire content into MEMORY */
public class X12_5010_837_Parser extends AbstractTransformer{




	@Override
	public MuleEvent process(MuleEvent event) throws MuleException {
		// TODO Auto-generated method stub
		return super.process(event);
	}

	@Override
	public void setMuleContext(MuleContext context) {
		// TODO Auto-generated method stub
		super.setMuleContext(context);
	}

@Override
protected Object doTransform(Object src, String enc) throws TransformerException {
	InputStream is = null;
	InterchangeEnvelope interchangeEnvelope;
	Scanner sc = null;
	if(src instanceof InputStream)
	{
		is = (InputStream) src;
		sc = new Scanner(is);
	}
	else if(src instanceof String)
	{
		sc = new Scanner((String) src);
	}

	//sc.useDelimiter("~");
	//System.out.println("Segment Terminator set : " + System.getProperty("x12.hippamedicaidclaims837.segment.terminator"));
	//System.out.println("Element Terminator set : " + System.getProperty("x12.hippamedicaidclaims837.element.terminator"));
	sc.useDelimiter(System.getProperty("x12.hippamedicaidclaims837.segment.terminator"));
	Reader.setSegmentTerminator((System.getProperty("x12.hippamedicaidclaims837.segment.terminator").equals("") ? System.lineSeparator() : System.getProperty("x12.hippamedicaidclaims837.segment.terminator")));
	Reader.setElementTerminator(System.getProperty("x12.hippamedicaidclaims837.element.terminator"));
/*	System.out.println("Segment Terminator set : " + Reader.getSegmentTerminator());
	System.out.println("Element Terminator set : " + Reader.getElementTerminator());		
*/	
	interchangeEnvelope = Reader.getInterchangeEnvelope(sc);
	sc.close();
	if(is!=null)
	{
		try {
			is.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	return interchangeEnvelope;
}

/*public static void main(String[] args) throws FileNotFoundException {
	InputStream is = new FileInputStream("./src/main/resources/abc.txt");
	InterchangeEnvelope ev;
	Scanner sc = new Scanner(is);
	sc.useDelimiter("~");
	ev = Reader.getInterchangeEnvelope(sc);
	System.out.println(ev.toString());
}*/

}