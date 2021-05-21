package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 2, name = "CN1")
public class CN1 extends gov.delaware.edi.x12.Segment {
    // As per 837 FOCUS
	public CN1() { super(); }
    public CN1(String content) {
    	super(content);
		this.contractTypeCode = elements[1];
		this.contractAmount = elements[2];
   }

	public String contractTypeCode;
    public String contractAmount;
    public CN1(String contractTypeCode, String contractAmount) {
		super();
		this.contractTypeCode = contractTypeCode;
		this.contractAmount = contractAmount;
	}

	public String getContractTypeCode() {
		return contractTypeCode;
	}
	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}
	public String getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}
/*	@Override
	public String toString() {
		return "CN1*" + contractTypeCode + "*" + contractAmount + "~";
	}*/
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("CN1");
		builder.append(Writer.elementTerminator);
		builder.append(contractTypeCode);
		builder.append(Writer.elementTerminator);
		builder.append(contractAmount);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}
	
	
	
}