package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 4, name = "PER")
public class PER extends gov.delaware.edi.x12.Segment {
    public PER() { super(); }
    public PER(String content) {
    	super(content);    	
		this.contactFunctionCode = elements[1];
		this.name = elements[2];
		this.communicationNumberQualifier1 = elements[3];
		this.communiationNumber1 = elements[4];
		
/*		this.put("contactFunctionCode", elements[1]);
		this.put("name", elements[2]);
		this.put("communicationNumberQualifier1", elements[3]);
		this.put("communiationNumber1", elements[4]);*/
		/* Not used in FOCUS */
		//this.put("communicationNumberQualifier2", elements[5]);
		//this.put("communicationNumber2", elements[6]);
		//this.put("communicationNumberQualifier3", elements[7]);
		//this.put("communicationNumber3", elements[8]);
		//this.put("contactInquiryReference", elements[9]);

    }

    //public String[] elements;
    public String contactFunctionCode;
    public String name;
    public String communicationNumberQualifier1;
    public String communiationNumber1;
    /* Below fields not used in FOCUS*/
    public String communicationNumberQualifier2;
    public String communicationNumber2;
    public String communicationNumberQualifier3;
    public String communicationNumber3;
    public String contactInquiryReference;
	public PER(String contactFunctionCode, String name, String communicationNumberQualifier1,
			String communiationNumber1) {
		super();
		this.contactFunctionCode = contactFunctionCode;
		this.name = name;
		this.communicationNumberQualifier1 = communicationNumberQualifier1;
		this.communiationNumber1 = communiationNumber1;
	}
	public String getContactFunctionCode() {
		return contactFunctionCode;
	}
	public void setContactFunctionCode(String contactFunctionCode) {
		this.contactFunctionCode = contactFunctionCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommunicationNumberQualifier1() {
		return communicationNumberQualifier1;
	}
	public void setCommunicationNumberQualifier1(String communicationNumberQualifier1) {
		this.communicationNumberQualifier1 = communicationNumberQualifier1;
	}
	public String getCommuniationNumber1() {
		return communiationNumber1;
	}
	public void setCommuniationNumber1(String communiationNumber1) {
		this.communiationNumber1 = communiationNumber1;
	}
	public String getCommunicationNumberQualifier2() {
		return communicationNumberQualifier2;
	}
	public void setCommunicationNumberQualifier2(String communicationNumberQualifier2) {
		this.communicationNumberQualifier2 = communicationNumberQualifier2;
	}
	public String getCommunicationNumber2() {
		return communicationNumber2;
	}
	public void setCommunicationNumber2(String communicationNumber2) {
		this.communicationNumber2 = communicationNumber2;
	}
	public String getCommunicationNumberQualifier3() {
		return communicationNumberQualifier3;
	}
	public void setCommunicationNumberQualifier3(String communicationNumberQualifier3) {
		this.communicationNumberQualifier3 = communicationNumberQualifier3;
	}
	public String getCommunicationNumber3() {
		return communicationNumber3;
	}
	public void setCommunicationNumber3(String communicationNumber3) {
		this.communicationNumber3 = communicationNumber3;
	}
	public String getContactInquiryReference() {
		return contactInquiryReference;
	}
	public void setContactInquiryReference(String contactInquiryReference) {
		this.contactInquiryReference = contactInquiryReference;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		return "PER" + Writer.elementTerminator + contactFunctionCode + Writer.elementTerminator + name + Writer.elementTerminator
				+ communicationNumberQualifier1 + Writer.elementTerminator + communiationNumber1 + Writer.getSegmentTerminator() ;
	}
    
    
}
