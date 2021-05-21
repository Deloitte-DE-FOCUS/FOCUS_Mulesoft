package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 2, name = "N3")
public class N3 extends gov.delaware.edi.x12.Segment {
    public N3() {super();}
    public N3(String content) {
    	super(content);	
		this.addressInformation1 = elements[1];
		this.addressInformation2 = elements[2];
    }

    public String addressInformation1;
    public String addressInformation2;
	public N3(String setAddressInformation1, String setAddressInformation2) {
		super();
		this.addressInformation1 = setAddressInformation1;
		this.addressInformation2 = setAddressInformation2;
	}
	public String getAddressInformation1() {
		return addressInformation1;
	}
	public void setAddressInformation1(String addressInformation1) {
		this.addressInformation1 = addressInformation1;
	}
	public String getAddressInformation2() {
		return addressInformation2;
	}
	public void setAddressInformation2(String addressInformation2) {
		this.addressInformation2 = addressInformation2;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("N3").append(Writer.elementTerminator);
		builder.append(addressInformation1 == null ? "" : addressInformation1);
		builder.append(addressInformation2 == null ? "" : Writer.elementTerminator +addressInformation2);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}

}