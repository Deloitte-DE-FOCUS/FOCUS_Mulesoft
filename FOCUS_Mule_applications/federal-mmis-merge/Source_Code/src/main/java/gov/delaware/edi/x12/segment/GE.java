package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 2, name = "GE")
public class GE extends gov.delaware.edi.x12.Segment {
    public GE() {super();}
    public GE(String content) {
    	super(content);
		this.numberOfTransactionsSetsIncluded = elements[1];
		this.groupControlNumber = elements[2];
    }
    
    public String numberOfTransactionsSetsIncluded;
    public String groupControlNumber;
	public String getNumberOfTransactionsSetsIncluded() {
		return numberOfTransactionsSetsIncluded;
	}
	public void setNumberOfTransactionsSetsIncluded(String numberOfTransactionsSetsIncluded) {
		this.numberOfTransactionsSetsIncluded = numberOfTransactionsSetsIncluded;
	}
	public String getGroupControlNumber() {
		return groupControlNumber;
	}
	public void setGroupControlNumber(String groupControlNumber) {
		this.groupControlNumber = groupControlNumber;
	}
	public GE(String numberOfTransactionsSetsIncluded, String groupControlNumber) {
		super();
		this.numberOfTransactionsSetsIncluded = numberOfTransactionsSetsIncluded;
		this.groupControlNumber = groupControlNumber;
	}
/*	@Override
	public String toString() {
		return "GE*" + numberOfTransactionsSetsIncluded + "*"
				+ groupControlNumber + "~";
	}*/
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("GE");
		builder.append(Writer.elementTerminator);
		builder.append(numberOfTransactionsSetsIncluded);
		builder.append(Writer.elementTerminator);
		builder.append(groupControlNumber);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}
	

    
}
