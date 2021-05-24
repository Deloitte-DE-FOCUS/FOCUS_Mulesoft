package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 16, name = "ISA")
public class ISA extends gov.delaware.edi.x12.Segment 
{
    public ISA() {super();}
    public ISA(String content) {
        super(content);
		this.authInfoQualifier = elements[1];
		this.authInformation = elements[2];
		this.securityInfoQualifier = elements[3];
		this.securityInformation = elements[4];
		this.interchangeIDQualifierSender = elements[5];
		this.interchangeSenderID = elements[6];
		this.interchangeIDQualifierReceiver = elements[7];
		this.interchangeReceiverID = elements[8];
		this.interchangeDate = elements[9];
		this.interchangeTime = elements[10];
		this.repetitionSeparator = elements[11];
		this.interchangeControlVersionNumber = elements[12];
		this.interchangeControlNumber = elements[13];
		this.acknowledgmentRequested = elements[14];
		this.usageIndicator = elements[15];
		this.componentElementSeparator = elements[16];
/*		this.put("authInfoQualifier", elements[1]);
		this.put("authInformation", elements[2]);
		this.put("securityInfoQualifier", elements[3]);
		this.put("securityInformation", elements[4]);
		this.put("interchangeIDQualifierSender", elements[5]);
		this.put("interchangeSenderID", elements[6]);
		this.put("interchangeIDQualifierReceiver", elements[7]);
		this.put("interchangeReceiverID", elements[8]);
		this.put("interchangeDate", elements[9]);
		this.put("interchangeTime", elements[10]);
		this.put("repetitionSeparator", elements[11]);
		this.put("interchangeControlVersionNumber", elements[12]);
		this.put("interchangeControlNumber", elements[13]);
		this.put("acknowledgmentRequested", elements[14]);
		this.put("usageIndicator", elements[15]);
		this.put("componentElementSeparator", elements[16]);*/
    }
    
    public String authInfoQualifier;
    public String authInformation;
    public String securityInfoQualifier;
    public String securityInformation;
    public String interchangeIDQualifierSender;
    public String interchangeSenderID;
    public String interchangeIDQualifierReceiver;
    public String interchangeReceiverID;
    public String interchangeDate;
    public String interchangeTime;
    public String repetitionSeparator;
    public String interchangeControlVersionNumber;
    public String interchangeControlNumber;
    public String acknowledgmentRequested;
    public String usageIndicator;
    public String componentElementSeparator;
	public ISA(String authInfoQualifier, String authInformation, String securityInfoQualifier,
			String securityInformation, String interchangeIDQualifierSender, String interchangeSenderID,
			String interchangeIDQualifierReceiver, String interchangeReceiverID, String interchangeDate,
			String interchangeTime, String repetitionSeparator, String interchangeControlVersionNumber,
			String interchangeControlNumber, String acknowledgmentRequested, String usageIndicator,
			String componentElementSeparator) {
		super();
		this.authInfoQualifier = authInfoQualifier;
		this.authInformation = authInformation;
		this.securityInfoQualifier = securityInfoQualifier;
		this.securityInformation = securityInformation;
		this.interchangeIDQualifierSender = interchangeIDQualifierSender;
		this.interchangeSenderID = interchangeSenderID;
		this.interchangeIDQualifierReceiver = interchangeIDQualifierReceiver;
		this.interchangeReceiverID = interchangeReceiverID;
		this.interchangeDate = interchangeDate;
		this.interchangeTime = interchangeTime;
		this.repetitionSeparator = repetitionSeparator;
		this.interchangeControlVersionNumber = interchangeControlVersionNumber;
		this.interchangeControlNumber = interchangeControlNumber;
		this.acknowledgmentRequested = acknowledgmentRequested;
		this.usageIndicator = usageIndicator;
		this.componentElementSeparator = componentElementSeparator;
	}
	public String getAuthInfoQualifier() {
		return authInfoQualifier;
	}
	public void setAuthInfoQualifier(String authInfoQualifier) {
		this.authInfoQualifier = authInfoQualifier;
	}
	public String getAuthInformation() {
		return authInformation;
	}
	public void setAuthInformation(String authInformation) {
		this.authInformation = authInformation;
	}
	public String getSecurityInfoQualifier() {
		return securityInfoQualifier;
	}
	public void setSecurityInfoQualifier(String securityInfoQualifier) {
		this.securityInfoQualifier = securityInfoQualifier;
	}
	public String getSecurityInformation() {
		return securityInformation;
	}
	public void setSecurityInformation(String securityInformation) {
		this.securityInformation = securityInformation;
	}
	public String getInterchangeIDQualifierSender() {
		return interchangeIDQualifierSender;
	}
	public void setInterchangeIDQualifierSender(String interchangeIDQualifierSender) {
		this.interchangeIDQualifierSender = interchangeIDQualifierSender;
	}
	public String getInterchangeSenderID() {
		return interchangeSenderID;
	}
	public void setInterchangeSenderID(String interchangeSenderID) {
		this.interchangeSenderID = interchangeSenderID;
	}
	public String getInterchangeIDQualifierReceiver() {
		return interchangeIDQualifierReceiver;
	}
	public void setInterchangeIDQualifierReceiver(String interchangeIDQualifierReceiver) {
		this.interchangeIDQualifierReceiver = interchangeIDQualifierReceiver;
	}
	public String getInterchangeReceiverID() {
		return interchangeReceiverID;
	}
	public void setInterchangeReceiverID(String interchangeReceiverID) {
		this.interchangeReceiverID = interchangeReceiverID;
	}
	public String getInterchangeDate() {
		return interchangeDate;
	}
	public void setInterchangeDate(String interchangeDate) {
		this.interchangeDate = interchangeDate;
	}
	public String getInterchangeTime() {
		return interchangeTime;
	}
	public void setInterchangeTime(String interchangeTime) {
		this.interchangeTime = interchangeTime;
	}
	public String getRepetitionSeparator() {
		return repetitionSeparator;
	}
	public void setRepetitionSeparator(String repetitionSeparator) {
		this.repetitionSeparator = repetitionSeparator;
	}
	public String getInterchangeControlVersionNumber() {
		return interchangeControlVersionNumber;
	}
	public void setInterchangeControlVersionNumber(String interchangeControlVersionNumber) {
		this.interchangeControlVersionNumber = interchangeControlVersionNumber;
	}
	public String getInterchangeControlNumber() {
		return interchangeControlNumber;
	}
	public void setInterchangeControlNumber(String interchangeControlNumber) {
		this.interchangeControlNumber = interchangeControlNumber;
	}
	public String getAcknowledgmentRequested() {
		return acknowledgmentRequested;
	}
	public void setAcknowledgmentRequested(String acknowledgmentRequested) {
		this.acknowledgmentRequested = acknowledgmentRequested;
	}
	public String getUsageIndicator() {
		return usageIndicator;
	}
	public void setUsageIndicator(String usageIndicator) {
		this.usageIndicator = usageIndicator;
	}
	public String getComponentElementSeparator() {
		return componentElementSeparator;
	}
	public void setComponentElementSeparator(String componentElementSeparator) {
		this.componentElementSeparator = componentElementSeparator;
	}
	@Override
	public String toString() {
		return "ISA" + Writer.elementTerminator + authInfoQualifier + Writer.elementTerminator + authInformation
				+ Writer.elementTerminator + securityInfoQualifier + Writer.elementTerminator + securityInformation
				+ Writer.elementTerminator + interchangeIDQualifierSender + Writer.elementTerminator
				+ interchangeSenderID + Writer.elementTerminator + interchangeIDQualifierReceiver
				+ Writer.elementTerminator + interchangeReceiverID + Writer.elementTerminator + interchangeDate
				+ Writer.elementTerminator + interchangeTime + Writer.elementTerminator + repetitionSeparator
				+ Writer.elementTerminator + interchangeControlVersionNumber + Writer.elementTerminator
				+ interchangeControlNumber + Writer.elementTerminator + acknowledgmentRequested
				+ Writer.elementTerminator + usageIndicator + Writer.elementTerminator + Writer.componentTerminator
				+ Writer.getSegmentTerminator();
	}
  
	
	
}
