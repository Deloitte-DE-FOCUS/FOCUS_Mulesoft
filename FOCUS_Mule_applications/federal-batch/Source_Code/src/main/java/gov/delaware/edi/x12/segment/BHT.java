package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 6, name = "BHT")
public class BHT extends gov.delaware.edi.x12.Segment {
    public BHT() {super();}
    public BHT(String content) {
        super(content);
		this.hierarchicalStructureCode = elements[1];
		this.transactionSetPurposeCode = elements[2];
		this.referenceIdentification = elements[3];
		this.date = elements[4];
		this.time = elements[5];
		this.transactionTypeCode = elements[6];
    }

	public BHT(String hierarchicalStructureCode, String transactionSetPurposeCode, String referenceIdentification,
			String date, String time, String transactionTypeCode) {
		super();
		this.hierarchicalStructureCode = hierarchicalStructureCode;
		this.transactionSetPurposeCode = transactionSetPurposeCode;
		this.referenceIdentification = referenceIdentification;
		this.date = date;
		this.time = time;
		this.transactionTypeCode = transactionTypeCode;
	}
	public String getHierarchicalStructureCode() {
		return hierarchicalStructureCode;
	}
	public void setHierarchicalStructureCode(String hierarchicalStructureCode) {
		this.hierarchicalStructureCode = hierarchicalStructureCode;
	}
	public String getTransactionSetPurposeCode() {
		return transactionSetPurposeCode;
	}
	public void setTransactionSetPurposeCode(String transactionSetPurposeCode) {
		this.transactionSetPurposeCode = transactionSetPurposeCode;
	}
	public String getReferenceIdentification() {
		return referenceIdentification;
	}
	public void setReferenceIdentification(String referenceIdentification) {
		this.referenceIdentification = referenceIdentification;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}
	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	
    public String hierarchicalStructureCode;
    public String transactionSetPurposeCode;
    public String referenceIdentification;
    public String date;
    public String time;
    public String transactionTypeCode;
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("BHT");
		builder.append(Writer.elementTerminator);
		builder.append(hierarchicalStructureCode);
		builder.append(Writer.elementTerminator);
		builder.append(transactionSetPurposeCode);
		builder.append(Writer.elementTerminator);
		builder.append(referenceIdentification);
		builder.append(Writer.elementTerminator);
		builder.append(date);
		builder.append(Writer.elementTerminator);
		builder.append(time);
		builder.append(Writer.elementTerminator);
		builder.append(transactionTypeCode);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}

    
}
