package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 1, name = "HI")
public class HI  extends gov.delaware.edi.x12.Segment{
    // As per 837 FOCUS
	public HI() { super(); }
    public HI(String content) {

    	super(content);
    	this.content = elements[1];
   }	

	public String content;
	
	public HI(String content,String filler) {
		super();
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		return "HI" + Writer.elementTerminator + this.content + Writer.getSegmentTerminator();
	}
	
}