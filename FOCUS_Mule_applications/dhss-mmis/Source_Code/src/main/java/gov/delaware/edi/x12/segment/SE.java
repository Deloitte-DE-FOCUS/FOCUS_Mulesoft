package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 2, name = "SE")
public class SE extends gov.delaware.edi.x12.Segment {
    public SE() {super();}
    public SE(String content) {
        super(content);
        //elements = content.split("\\*");
/*		this.put("transactionSegmentCount", elements[1]);
		this.put("transactionSetControlNumber", elements[2]);*/
		this.transactionSegmentCount = elements[1];
		this.transactionSetControlNumber = elements[2];
    }
    //public String[] elements;
	public String transactionSegmentCount;
	public String transactionSetControlNumber;
	public SE(String transactionSegmentCount, String transactionSetControlNumber) {
		super();
		this.transactionSegmentCount = transactionSegmentCount;
		this.transactionSetControlNumber = transactionSetControlNumber;
	}
	public String getTransactionSegmentCount() {
		return transactionSegmentCount;
	}
	public void setTransactionSegmentCount(String transactionSegmentCount) {
		this.transactionSegmentCount = transactionSegmentCount;
	}
	public String getTransactionSetControlNumber() {
		return transactionSetControlNumber;
	}
	public void setTransactionSetControlNumber(String transactionSetControlNumber) {
		this.transactionSetControlNumber = transactionSetControlNumber;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		this.setTransactionSegmentCount(new String("" +Writer.getTransactionSegmentCount()));
		Writer.setTransactionSegmentCount(0);//End TranctionSegment Counting
		return "SE" + Writer.elementTerminator + transactionSegmentCount + Writer.elementTerminator
				+ this.getTransactionSetControlNumber() + Writer.getSegmentTerminator() ;
	}
	
	
}
