package gov.delaware.edi.x12.segment;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 1, name = "LX")
public class LX extends gov.delaware.edi.x12.Segment {
    public LX() { super(); }
    public LX(String content) {
    	super(content);
		//this.put("assignedNumber", elements[1]);
		this.assignedNumber = elements[1];
   }

	
	public List<DTP> getDTPList() {
		return DTPList;
	}
	public void setDTPList(List<DTP> dTP) {
		DTPList = dTP;
	}

	public String assignedNumber;
	public SV2 SV2;
	public SV1 SV1;
	public List<DTP> DTPList;
	
	public LX(String assignedNumber, gov.delaware.edi.x12.segment.SV2 sV2, gov.delaware.edi.x12.segment.SV1 sV1,
			List<DTP> dTPList) {
		super();
		this.assignedNumber = assignedNumber;
		SV2 = sV2;
		SV1 = sV1;
		DTPList = dTPList;
	}
	public SV1 getSV1() {
		return SV1;
	}
	public void setSV1(SV1 sV1) {
		SV1 = sV1;
	}
	public String getAssignedNumber() {
		return assignedNumber;
	}
	public void setAssignedNumber(String assignedNumber) {
		this.assignedNumber = assignedNumber;
	}

	public SV2 getSV2() {
		return SV2;
	}
	public void setSV2(SV2 sV2) {
		SV2 = sV2;
	}

	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder LXsb = new StringBuilder();
		String DTPListX12String = "";
		if(this.DTPList !=null && this.DTPList.size() > 0)
			DTPListX12String = this.DTPList.stream().map(Object::toString).collect(Collectors.joining(""));
		LXsb.append("LX").append(Writer.elementTerminator).append(assignedNumber).append(Writer.getSegmentTerminator());
		if(this.SV1 !=null)
		LXsb.append(SV1.toString()).append(DTPListX12String);
		
		if(this.SV2 !=null)
		LXsb.append(SV2.toString()).append(DTPListX12String);
		return LXsb.toString();
	}

	
}
