package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 3, name = "DTP")
public class DTP  extends gov.delaware.edi.x12.Segment {
    public DTP() { super(); }
    public DTP(String content) {
		super(content);	
		this.dateTimeQualifier = elements[1];
		this.dateTimePeriodFormatQualifier = elements[2];
		this.dateTimePeriod = elements[3];
    }

    public String dateTimeQualifier;
    public String dateTimePeriodFormatQualifier;
    public String dateTimePeriod;
    
	public String getDateTimeQualifier() {
		return dateTimeQualifier;
	}
	public void setDateTimeQualifier(String dateTimeQualifier) {
		this.dateTimeQualifier = dateTimeQualifier;
	}
	public String getDateTimePeriodFormatQualifier() {
		return dateTimePeriodFormatQualifier;
	}
	public void setDateTimePeriodFormatQualifier(String dateTimePeriodFormatQualifier) {
		this.dateTimePeriodFormatQualifier = dateTimePeriodFormatQualifier;
	}
	public String getDateTimePeriod() {
		return dateTimePeriod;
	}
	public void setDateTimePeriod(String dateTimePeriod) {
		this.dateTimePeriod = dateTimePeriod;
	}
	public DTP(String dateTimeQualifier, String dateTimePeriodFormatQualifier, String dateTimePeriod) {
		super();
		this.dateTimeQualifier = dateTimeQualifier;
		this.dateTimePeriodFormatQualifier = dateTimePeriodFormatQualifier;
		this.dateTimePeriod = dateTimePeriod;
	}
/*	@Override
	public String toString() {
		return "DTP*" + dateTimeQualifier + "*"
				+ dateTimePeriodFormatQualifier + "*" + dateTimePeriod + "~";
	}
	*/
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("DTP");
		builder.append(Writer.elementTerminator);
		builder.append(dateTimeQualifier);
		builder.append(Writer.elementTerminator);
		builder.append(dateTimePeriodFormatQualifier);
		builder.append(Writer.elementTerminator);
		builder.append(dateTimePeriod);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}

}
