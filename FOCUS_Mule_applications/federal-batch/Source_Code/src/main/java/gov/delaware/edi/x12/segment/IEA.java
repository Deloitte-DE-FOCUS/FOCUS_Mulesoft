package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 2, name = "IEA")
public class IEA extends gov.delaware.edi.x12.Segment {
    public IEA() {super();}
    public IEA(String content) {
    	super(content);
		this.numberOfIncludedFunctionalGroups = elements[1];
		this.interchangeControlNumber = elements[2];
    }
    
	public String numberOfIncludedFunctionalGroups;
	public String interchangeControlNumber;
	public IEA(String numberOfIncludedFunctionalGroups, String interchangeControlNumber) {
		super();
		this.numberOfIncludedFunctionalGroups = numberOfIncludedFunctionalGroups;
		this.interchangeControlNumber = interchangeControlNumber;
	}
	public String getNumberOfIncludedFunctionalGroups() {
		return numberOfIncludedFunctionalGroups;
	}
	public void setNumberOfIncludedFunctionalGroups(String numberOfIncludedFunctionalGroups) {
		this.numberOfIncludedFunctionalGroups = numberOfIncludedFunctionalGroups;
	}
	public String getInterchangeControlNumber() {
		return interchangeControlNumber;
	}
	public void setInterchangeControlNumber(String interchangeControlNumber) {
		this.interchangeControlNumber = interchangeControlNumber;
	}
	@Override
	public String toString() {
		return "IEA" + Writer.elementTerminator + numberOfIncludedFunctionalGroups
				+ Writer.elementTerminator + interchangeControlNumber + Writer.getSegmentTerminator();
	}	
}