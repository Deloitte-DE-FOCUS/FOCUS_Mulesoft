package gov.delaware.edi.x12.message;

import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.segment.BHT;
import gov.delaware.edi.x12.segment.REF;
import gov.delaware.edi.x12.segment.NM1;
import gov.delaware.edi.x12.segment.HL;
import gov.delaware.edi.x12.segment.HLLoop;

public class DetailMessage837 {
	
	
	public DetailMessage837() {
		super();
	}


	public DetailMessage837(gov.delaware.edi.x12.segment.BHT bHT, gov.delaware.edi.x12.segment.REF rEF,
			List<NM1> nM1List, List<HLLoop> hLLoopList) {
		super();
		BHT = bHT;
		REF = rEF;
		NM1List = nM1List;
		HLLoopList = hLLoopList;
	}

	private BHT BHT;
	private REF REF;
	private List<NM1> NM1List;
	private List<HL> HLList;
	private List<HLLoop> HLLoopList;



	public List<HLLoop> getHLLoopList() {
		return HLLoopList;
	}
	public void setHLLoopList(List<HLLoop> hLLoopList) {
		HLLoopList = hLLoopList;
	}
	public BHT getBHT() {
		return BHT;
	}
	public void setBHT(BHT bHT) {
		BHT = bHT;
	}
	public REF getREF() {
		return REF;
	}
	public void setREF(REF rEF) {
		REF = rEF;
	}
	public List<NM1> getNM1List() {
		return NM1List;
	}
	public void setNM1List(List<NM1> nM1List) {
		NM1List = nM1List;
	}
	public List<HL> getHLList() {
		return HLList;
	}
	public void setHLList(List<HL> hLList) {
		HLList = hLList;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(this.BHT != null)
			builder.append(BHT);
		if(this.REF != null)
			builder.append(REF);
		if(this.NM1List != null && this.NM1List.size() >0) {
			builder.append(NM1List.stream().map(Object::toString).collect(Collectors.joining("")));
		}
		/*	if(this.HLList != null && this.HLList.size() >0) {
				builder.append(HLList.stream().map(Object::toString).collect(Collectors.joining("")));
		}*/
		if(this.HLLoopList != null && this.HLLoopList.size() >0) {
			builder.append(this.HLLoopList.stream().sequential().map(Object::toString).collect(Collectors.joining("")));
			Writer.resetHierarchicalIDNumber();
		}
			
		return builder.toString();
	}
	
	
	
	
	

	
}
