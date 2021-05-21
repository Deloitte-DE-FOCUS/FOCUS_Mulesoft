package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 8, name = "GS")
public class GS extends gov.delaware.edi.x12.Segment 
{
    public GS() {super();}
    public GS(String content) {
    	super(content);
		this.functionalIDCode = elements[1];
		this.applicationSendersCode = elements[2];
		this.applicationReceiversCode = elements[3];
		this.date = elements[4];
		this.time = elements[5];
		this.groupControlNumber = elements[6];
		this.responsibleAgencyCode = elements[7];
		this.versionReleaseIndustryIDCode = elements[8];
/*		this.put("functionalIDCode", elements[1]);
		this.put("applicationSendersCode", elements[2]);
		this.put("applicationReceiversCode", elements[3]);
		this.put("date", elements[4]);
		this.put("time", elements[5]);
		this.put("groupControlNumber", elements[6]);
		this.put("responsibleAgencyCode", elements[7]);
		this.put("versionReleaseIndustryIDCode", elements[8]);*/
    }

    public String functionalIDCode;
    public String applicationSendersCode;
    public String applicationReceiversCode;
    public String date;
    public String time;
    public String groupControlNumber;
    public String responsibleAgencyCode;
    public String versionReleaseIndustryIDCode;
	public GS(String functionalIDCode, String applicationSendersCode, String applicationReceiversCode, String date,
			String time, String groupControlNumber, String responsibleAgencyCode, String versionReleaseIndustryIDCode) {
		super();
		this.functionalIDCode = functionalIDCode;
		this.applicationSendersCode = applicationSendersCode;
		this.applicationReceiversCode = applicationReceiversCode;
		this.date = date;
		this.time = time;
		this.groupControlNumber = groupControlNumber;
		this.responsibleAgencyCode = responsibleAgencyCode;
		this.versionReleaseIndustryIDCode = versionReleaseIndustryIDCode;
	}
	public String getFunctionalIDCode() {
		return functionalIDCode;
	}
	public void setFunctionalIDCode(String functionalIDCode) {
		this.functionalIDCode = functionalIDCode;
	}
	public String getApplicationSendersCode() {
		return applicationSendersCode;
	}
	public void setApplicationSendersCode(String applicationSendersCode) {
		this.applicationSendersCode = applicationSendersCode;
	}
	public String getApplicationReceiversCode() {
		return applicationReceiversCode;
	}
	public void setApplicationReceiversCode(String applicationReceiversCode) {
		this.applicationReceiversCode = applicationReceiversCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGroupControlNumber() {
		return groupControlNumber;
	}
	public void setGroupControlNumber(String groupControlNumber) {
		this.groupControlNumber = groupControlNumber;
	}
	public String getResponsibleAgencyCode() {
		return responsibleAgencyCode;
	}
	public void setResponsibleAgencyCode(String responsibleAgencyCode) {
		this.responsibleAgencyCode = responsibleAgencyCode;
	}
	public String getVersionReleaseIndustryIDCode() {
		return versionReleaseIndustryIDCode;
	}
	public void setVersionReleaseIndustryIDCode(String versionReleaseIndustryIDCode) {
		this.versionReleaseIndustryIDCode = versionReleaseIndustryIDCode;
	}
/*	@Override
	public String toString() {
		return "GS*" + functionalIDCode + "*" + applicationSendersCode
				+ "*" + applicationReceiversCode + "*" + date + "*" + time
				+ "*" + groupControlNumber + "*" + responsibleAgencyCode
				+ "*" + versionReleaseIndustryIDCode + "~";
	}*/
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("GS");
		builder.append(Writer.elementTerminator);
		builder.append(functionalIDCode);
		builder.append(Writer.elementTerminator);
		builder.append(applicationSendersCode);
		builder.append(Writer.elementTerminator);
		builder.append(applicationReceiversCode);
		builder.append(Writer.elementTerminator);
		builder.append(date);
		builder.append(Writer.elementTerminator);
		builder.append(time);
		builder.append(Writer.elementTerminator);
		builder.append(groupControlNumber);
		builder.append(Writer.elementTerminator);
		builder.append(responsibleAgencyCode);
		builder.append(Writer.elementTerminator);
		builder.append(versionReleaseIndustryIDCode);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}
    
    

}
