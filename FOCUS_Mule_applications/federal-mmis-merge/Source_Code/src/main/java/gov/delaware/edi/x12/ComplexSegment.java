package gov.delaware.edi.x12;

import java.util.LinkedHashMap;

public class ComplexSegment extends java.util.LinkedHashMap {

	public ComplexSegment()
	{
		super();
		this.put("SubElements", new LinkedHashMap());
	}
	
	
}
