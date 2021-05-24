package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 3, name = "ST")
public class ST extends gov.delaware.edi.x12.Segment {
    public ST() {super();}
    public ST(String content) {
       super(content);
        //elements = content.split("\\*");
/*		this.put("transactionSetIDCode", elements[1]);
		this.put("transactionSetControlNumber", elements[2]);
		this.put("implementationConventionReference", elements[3]);
*/		
		this.transactionSetIDCode = elements[1];
		this.transactionSetControlNumber = elements[2];
		this.implementationConventionReference = elements[3];

    }
    //public String[] elements;
    public String transactionSetIDCode;
    public String transactionSetControlNumber;
    public String implementationConventionReference;
	public ST(String transactionSetIDCode, String transactionSetControlNumber,
			String implementationConventionReference) {
		super();
		this.transactionSetIDCode = transactionSetIDCode;
		this.transactionSetControlNumber = transactionSetControlNumber;
		this.implementationConventionReference = implementationConventionReference;
	}
	public String getTransactionSetIDCode() {
		return transactionSetIDCode;
	}
	public void setTransactionSetIDCode(String transactionSetIDCode) {
		this.transactionSetIDCode = transactionSetIDCode;
	}
	public String getTransactionSetControlNumber() {
		return transactionSetControlNumber;
	}
	public void setTransactionSetControlNumber(String transactionSetControlNumber) {
		this.transactionSetControlNumber = transactionSetControlNumber;
	}
	public String getImplementationConventionReference() {
		return implementationConventionReference;
	}
	public void setImplementationConventionReference(String implementationConventionReference) {
		this.implementationConventionReference = implementationConventionReference;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount(); //Start Transaction Segments Counting
		return "ST" + Writer.elementTerminator + transactionSetIDCode + Writer.elementTerminator
				+ transactionSetControlNumber + Writer.elementTerminator
				+ implementationConventionReference + Writer.getSegmentTerminator() ;
	}
    
    
}
