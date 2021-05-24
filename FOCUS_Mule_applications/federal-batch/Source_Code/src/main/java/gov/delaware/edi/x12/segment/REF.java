package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 2, name = "REF")
public class REF  extends gov.delaware.edi.x12.Segment {
    public REF() {
        super();
    }
    public REF(String content) {
        super(content);
    	//elements = content.split("\\*");
		this.referenceIdentificationQualifier = elements[1];
		this.referenceIdentification = elements[2];
/*		this.put("referenceIdentificationQualifier", elements[1]);
		this.put("referenceIdentification", elements[2]);*/
/*		Remaining elements not used in FOCUS
 * 		this.put("setDescription", elements[3]);
		this.put("setReferenceIdentifier", elements[4]);*/

    }
   // public String[] elements;
    public String referenceIdentificationQualifier;
    public String referenceIdentification;
    /* Below fields not used in FOCUS*/
    public String setDescription;
    public String setReferenceIdentifier;
   
    public REF(String referenceIdentificationQualifier, String referenceIdentification) {
		super();
		this.referenceIdentificationQualifier = referenceIdentificationQualifier;
		this.referenceIdentification = referenceIdentification;
	}
	public String getReferenceIdentificationQualifier() {
		return referenceIdentificationQualifier;
	}
	public void setReferenceIdentificationQualifier(String referenceIdentificationQualifier) {
		this.referenceIdentificationQualifier = referenceIdentificationQualifier;
	}
	public String getReferenceIdentification() {
		return referenceIdentification;
	}
	public void setReferenceIdentification(String referenceIdentification) {
		this.referenceIdentification = referenceIdentification;
	}
	public String getSetDescription() {
		return setDescription;
	}
	public void setSetDescription(String setDescription) {
		this.setDescription = setDescription;
	}
	public String getSetReferenceIdentifier() {
		return setReferenceIdentifier;
	}
	public void setSetReferenceIdentifier(String setReferenceIdentifier) {
		this.setReferenceIdentifier = setReferenceIdentifier;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		return "REF" + Writer.elementTerminator + referenceIdentificationQualifier
				+ Writer.elementTerminator + referenceIdentification + Writer.getSegmentTerminator() ;
	} 
}