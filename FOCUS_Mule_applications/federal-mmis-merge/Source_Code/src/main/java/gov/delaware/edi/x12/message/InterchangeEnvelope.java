package gov.delaware.edi.x12.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Reader;
import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.message.FunctionalGroup;
import gov.delaware.edi.x12.segment.IEA;
import gov.delaware.edi.x12.segment.ISA;


public class InterchangeEnvelope {
    private ISA interchangeControlHeader;
    private List<FunctionalGroup> functionalGroups;
    private IEA interchangeControlTrailer;

    


	public InterchangeEnvelope(ISA interchangeControlHeader, List<FunctionalGroup> functionalGroups,
			IEA interchangeControlTrailer) {
		super();
		this.interchangeControlHeader = interchangeControlHeader;
		this.functionalGroups = functionalGroups;
		this.interchangeControlTrailer = interchangeControlTrailer;
	}

	public InterchangeEnvelope() {
		interchangeControlHeader = new ISA();
        functionalGroups = new ArrayList<FunctionalGroup>();
        interchangeControlTrailer = new IEA();
    }

	public ISA getInterchangeControlHeader() {
		return interchangeControlHeader;
	}

	public void setInterchangeControlHeader(ISA interchangeControlHeader) {
		this.interchangeControlHeader = interchangeControlHeader;
	}

	public List<FunctionalGroup> getFunctionalGroups() {
		return functionalGroups;
	}

	public void setFunctionalGroups(List<FunctionalGroup> functionalGroups) {
		this.functionalGroups = functionalGroups;
	}

	public IEA getInterchangeControlTrailer() {
		return interchangeControlTrailer;
	}

	public void setInterchangeControlTrailer(IEA interchangeControlTrailer) {
		this.interchangeControlTrailer = interchangeControlTrailer;
	}

	@Override
	public String toString() {
		
		/* Set Reader configurations*/
/*		String segmentTerminator = System.getProperty("x12.segment.terminator") !=null ? System.getProperty("x12.segment.terminator") : "~";
		
		if(segmentTerminator.equals("LF") || segmentTerminator.equals("CR") || segmentTerminator.equals("CRLF"))
			segmentTerminator = System.lineSeparator();
		
		Reader.setSegmentTerminator(segmentTerminator);
		Reader.setElementTerminator((System.getProperty("x12.element.terminator") !=null ? System.getProperty("x12.element.terminator") : "*"));
		Reader.setComponentTerminator((System.getProperty("x12.component.terminator") !=null ? System.getProperty("x12.component.terminator") : ":"));

		Writer.setSegmentTerminator(segmentTerminator);
		Writer.setElementTerminator((System.getProperty("x12.element.terminator") !=null ? System.getProperty("x12.element.terminator") : "*"));
		Writer.setComponentTerminator((System.getProperty("x12.component.terminator") !=null ? System.getProperty("x12.component.terminator") : ":"));
*/		
		StringBuilder builder = new StringBuilder();
		builder.append(this.interchangeControlHeader.toString());
		if(this.functionalGroups !=null && this.functionalGroups.size() >0) {
		builder.append(functionalGroups.stream().map(Object::toString).collect(Collectors.joining("")));
		}
		builder.append(this.interchangeControlTrailer.toString());
		return builder.toString();
	} 
}
