package gov.delaware.edi.x12.segment;

import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 4, name = "HL")
public class HL extends gov.delaware.edi.x12.Segment {
    public HL() {
        super();
    }
    public HL(String content) {
    	super(content);
		this.hierarchicalIDNumber = Integer.parseInt(elements[1]);
		this.hierarchicalParentIDNumber = Integer.parseInt(elements[2]);
		this.hierarchicalLevelCode = Integer.parseInt(elements[3]);
		this.hierarchicalChildCode = Integer.parseInt(elements[4]);
/*    		this.put("hierarchicalIDNumber", elements[1]);
    		this.put("hierarchicalParentIDNumber", elements[2]);
    		this.put("hierarchicalLevelCode", elements[3]);
    		this.put("hierarchicalChildCode", elements[4]);*/
        }


        
public HL(int hierarchicalIDNumber, int hierarchicalParentIDNumber, int hierarchicalLevelCode,
			int hierarchicalChildCode, gov.delaware.edi.x12.segment.PRV pRV, List<SBR> sBRList, List<DTP> dTPList,
			List<NM1> nM1List, List<CLM> cLMList) {
		super();
		this.hierarchicalIDNumber = hierarchicalIDNumber;
		this.hierarchicalParentIDNumber = hierarchicalParentIDNumber;
		this.hierarchicalLevelCode = hierarchicalLevelCode;
		this.hierarchicalChildCode = hierarchicalChildCode;
		PRV = pRV;
		SBRList = sBRList;
		DTPList = dTPList;
		NM1List = nM1List;
		CLMList = cLMList;
	}

public int hierarchicalIDNumber;
public int hierarchicalParentIDNumber;
public int hierarchicalLevelCode;
public int hierarchicalChildCode;

/* Complex Children below */
public PRV PRV;
public List<SBR> SBRList;
public List<DTP> DTPList;
public List<NM1> NM1List;
public List<CLM> CLMList;

public int getHierarchicalIDNumber() {
	return hierarchicalIDNumber;
}
public void setHierarchicalIDNumber(int hierarchicalIDNumber) {
	this.hierarchicalIDNumber = hierarchicalIDNumber;
}
public int getHierarchicalParentIDNumber() {
	return hierarchicalParentIDNumber;
}
public void setHierarchicalParentIDNumber(int hierarchicalParentIDNumber) {
	this.hierarchicalParentIDNumber = hierarchicalParentIDNumber;
}
public int getHierarchicalLevelCode() {
	return hierarchicalLevelCode;
}
public void setHierarchicalLevelCode(int hierarchicalLevelCode) {
	this.hierarchicalLevelCode = hierarchicalLevelCode;
}
public int getHierarchicalChildCode() {
	return hierarchicalChildCode;
}
public void setHierarchicalChildCode(int hierarchicalChildCode) {
	this.hierarchicalChildCode = hierarchicalChildCode;
}
public PRV getPRV() {
	return PRV;
}
public void setPRV(PRV pRV) {
	PRV = pRV;
}
public List<SBR> getSBRList() {
	return SBRList;
}
public void setSBRList(List<SBR> sBRList) {
	SBRList = sBRList;
}
public List<DTP> getDTPList() {
	return DTPList;
}
public void setDTPList(List<DTP> dTPList) {
	DTPList = dTPList;
}
public List<NM1> getNM1List() {
	return NM1List;
}
public void setNM1List(List<NM1> nM1List) {
	NM1List = nM1List;
}
public List<CLM> getCLMList() {
	return CLMList;
}
public void setCLMList(List<CLM> cLMList) {
	CLMList = cLMList;
}
@Override
public String toString() {
	updateTransactionSegmentCount();
	String PRVEDIX12String = "";
	String SBRListEDIX12String = "";
	String DTPListEDIX12String = "";
	String NM1ListEDIX12String = "";
	String CLMListEDIX12String = "";
	if(this.PRV != null && !this.PRV.equals(""))
		PRVEDIX12String = this.PRV.toString();
	if(this.SBRList != null && this.SBRList.size()>0 )
		SBRListEDIX12String = this.SBRList.stream().map(Object::toString).collect(Collectors.joining(""));
	if(this.DTPList != null && this.DTPList.size()>0 )
		DTPListEDIX12String = this.DTPList.stream().map(Object::toString).collect(Collectors.joining(""));
	if(this.NM1List != null && NM1List.size()>0 )
		NM1ListEDIX12String = this.NM1List.stream().map(Object::toString).collect(Collectors.joining(""));
	if(this.CLMList != null && CLMList.size()>0 )
		CLMListEDIX12String = this.CLMList.stream().map(Object::toString).collect(Collectors.joining(""));
		
	StringBuilder  HLSb = new StringBuilder();
	HLSb.append("HL").append(Writer.elementTerminator).append(hierarchicalIDNumber).append(Writer.elementTerminator).append((this.hierarchicalParentIDNumber == 0 ? "" :this.hierarchicalParentIDNumber)).append(Writer.elementTerminator).append(hierarchicalLevelCode);
	HLSb.append(Writer.elementTerminator).append(hierarchicalChildCode).append(Writer.getSegmentTerminator());

	HLSb.append(PRVEDIX12String);
	HLSb.append(SBRListEDIX12String);
	HLSb.append(DTPListEDIX12String);
	HLSb.append(NM1ListEDIX12String);
	HLSb.append(CLMListEDIX12String);

	return HLSb.toString();
}

        
        
}
