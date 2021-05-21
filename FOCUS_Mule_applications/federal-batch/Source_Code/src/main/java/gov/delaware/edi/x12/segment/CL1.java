package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 3, name = "CL1")
public class CL1 extends gov.delaware.edi.x12.Segment {
    // As per 837 FOCUS
	public CL1() { super(); }
    public CL1(String content) {
    	super(content);
    	this.admissionTypeCode = elements[1];
    	this.admissionSourceCode = elements[2];
    	this.patientStatusCode = elements[3];

    	/*		this.put("admissionTypeCode", elements[1]);
		this.put("admissionSourceCode", elements[2]);
		this.put("patientStatusCode", elements[3]);*/
   }
   

    public CL1(String admissionTypeCode, String admissionSourceCode, String patientStatusCode) {
		super();
		this.admissionTypeCode = admissionTypeCode;
		this.admissionSourceCode = admissionSourceCode;
		this.patientStatusCode = patientStatusCode;
	}

    public String admissionTypeCode;
    public String admissionSourceCode;
    public String patientStatusCode;
    
	public String getAdmissionTypeCode() {
		return admissionTypeCode;
	}
	public void setAdmissionTypeCode(String admissionTypeCode) {
		this.admissionTypeCode = admissionTypeCode;
	}
	public String getAdmissionSourceCode() {
		return admissionSourceCode;
	}
	public void setAdmissionSourceCode(String admissionSourceCode) {
		this.admissionSourceCode = admissionSourceCode;
	}
	public String getPatientStatusCode() {
		return patientStatusCode;
	}
	public void setPatientStatusCode(String patientStatusCode) {
		this.patientStatusCode = patientStatusCode;
	}
/*	@Override
	public String toString() {
		return "CL1*" + this.admissionTypeCode
				+ "*" + this.admissionSourceCode + "*" + this.patientStatusCode + "~";
	}*/
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("CL1");
		builder.append(Writer.elementTerminator);
		builder.append(admissionTypeCode);
		builder.append(Writer.elementTerminator);
		builder.append(admissionSourceCode);
		builder.append(Writer.elementTerminator);
		builder.append(patientStatusCode);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}
	
    
    
}