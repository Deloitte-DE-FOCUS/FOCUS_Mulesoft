package gov.delaware.edi.x12.segment;

import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;

public class HLLoop {

	public HL parentHL; //Provider 
	public List<HL> childHLList; //Subscriber

	public HLLoop() {
		super();
	}
	
	public HLLoop(HL parentHL, List<HL> childHLList) {
		super();
		this.parentHL = parentHL;
		this.childHLList = childHLList;
	}

	public HL getParentHL() {
		return parentHL;
	}
	public void setParentHL(HL parentHL) {
		this.parentHL = parentHL;
	}
	public List<HL> getChildHLList() {
		return childHLList;
	}
	public void setChildHLList(List<HL> childHLList) {
		this.childHLList = childHLList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Writer.setHierarchicalIDNumber(Writer.getHierarchicalIDNumber() + 1);
		int parentHLHierarchicalIDNumber = Writer.getHierarchicalIDNumber();
		parentHL.setHierarchicalIDNumber(parentHLHierarchicalIDNumber);
		builder.append(parentHL.toString());
	    String childHLListEDIString = null;
		//Writer.setChildhHerarchicalIDNumber(2);
		if(this.childHLList != null && this.childHLList.size() >0){
			
			childHLListEDIString = childHLList.stream().sequential().map(childHL -> { 
				childHL.setHierarchicalParentIDNumber(parentHLHierarchicalIDNumber);
				Writer.setHierarchicalIDNumber(Writer.getHierarchicalIDNumber() + 1);			
				childHL.setHierarchicalIDNumber(Writer.getHierarchicalIDNumber());	
			return childHL.toString(); 
			}).collect(Collectors.joining(""));
		
		}
		
		builder.append(childHLListEDIString);
		
		
		return builder.toString();
	}
	
	
}
