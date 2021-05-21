package gov.delaware.edi.x12.segment;




import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 2, name = "NTE")
public class NTE extends gov.delaware.edi.x12.Segment {
    // As per 837 FOCUS
	public NTE() { super(); }
    public NTE(String content) {
    	super(content);
/*		this.put("noteReferenceCode", elements[1]);
		this.put("claimNoteText", elements[2]);
*/		
    	this.noteReferenceCode = elements[1];
		this.claimNoteText = elements[2];
    }	
	public String noteReferenceCode;
	public String claimNoteText;
	public NTE(String noteReferenceCode, String claimNoteText) {
		super();
		this.noteReferenceCode = noteReferenceCode;
		this.claimNoteText = claimNoteText;
	}
	public String getNoteReferenceCode() {
		return noteReferenceCode;
	}
	public void setNoteReferenceCode(String noteReferenceCode) {
		this.noteReferenceCode = noteReferenceCode;
	}
	public String getClaimNoteText() {
		return claimNoteText;
	}
	public void setClaimNoteText(String claimNoteText) {
		this.claimNoteText = claimNoteText;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		String NTEString = "NTE" + Writer.elementTerminator + noteReferenceCode;
		if(claimNoteText != null && !claimNoteText.equals(""))
		{
			NTEString = NTEString +  Writer.elementTerminator + claimNoteText;
		}
		NTEString = NTEString + Writer.getSegmentTerminator() ;
		return NTEString;
	}
	
	
}