package gov.delaware.edi.x12.segment;
import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 9, name = "SBR")
public class SBR extends gov.delaware.edi.x12.Segment {
  
  public String payerResponsibilitySequenceNumberCode;
  public String individualRelationshipCode;
  public String insuredGrouporPolicyNumber;
  public String otherInsuredGroupName;
  public String insuranceTypeCode;
  public String coordinationofBenefitsCode;
  public String conditionorResponseCode;
  public String employmentStatusCode;
  public String claimFilingIndicatorCode;
  
  /* Complex Children */
  public List<NM1> NM1List;
  public DMG DMG;
  
  public SBR() {}
  
  public SBR(String content) {
    super(content);
/*    put("payerResponsibilitySequenceNumberCode", elements[1]);
    put("individualRelationshipCode", elements[2]);
    put("insuredGrouporPolicyNumber", elements[3]);
    put("otherInsuredGroupName", elements[4);
    put("insuranceTypeCode", elements[5]);
    put("coordinationofBenefitsCode", elements[6]);
    put("conditionorResponseCode", elements[7]);
    put("employmentStatusCode", elements[8]);
    put("claimFilingIndicatorCode", elements[9]);*/
	this.payerResponsibilitySequenceNumberCode = elements[1];
	this.individualRelationshipCode = elements[2];
	this.insuredGrouporPolicyNumber = elements[3];
	this.otherInsuredGroupName = elements[4];
	this.insuranceTypeCode = elements[5];
	this.coordinationofBenefitsCode = elements[6];
	this.conditionorResponseCode = elements[7];
	this.employmentStatusCode = elements[8];
	this.claimFilingIndicatorCode = elements[9];
    
  }

public SBR(String payerResponsibilitySequenceNumberCode, String individualRelationshipCode,
		String insuredGrouporPolicyNumber, String otherInsuredGroupName, String insuranceTypeCode,
		String coordinationofBenefitsCode, String conditionorResponseCode, String employmentStatusCode,
		String claimFilingIndicatorCode) {
	super();
	this.payerResponsibilitySequenceNumberCode = payerResponsibilitySequenceNumberCode;
	this.individualRelationshipCode = individualRelationshipCode;
	this.insuredGrouporPolicyNumber = insuredGrouporPolicyNumber;
	this.otherInsuredGroupName = otherInsuredGroupName;
	this.insuranceTypeCode = insuranceTypeCode;
	this.coordinationofBenefitsCode = coordinationofBenefitsCode;
	this.conditionorResponseCode = conditionorResponseCode;
	this.employmentStatusCode = employmentStatusCode;
	this.claimFilingIndicatorCode = claimFilingIndicatorCode;
}

public String getPayerResponsibilitySequenceNumberCode() {
	return payerResponsibilitySequenceNumberCode;
}

public void setPayerResponsibilitySequenceNumberCode(String payerResponsibilitySequenceNumberCode) {
	this.payerResponsibilitySequenceNumberCode = payerResponsibilitySequenceNumberCode;
}

public String getIndividualRelationshipCode() {
	return individualRelationshipCode;
}

public void setIndividualRelationshipCode(String individualRelationshipCode) {
	this.individualRelationshipCode = individualRelationshipCode;
}

public String getInsuredGrouporPolicyNumber() {
	return insuredGrouporPolicyNumber;
}

public void setInsuredGrouporPolicyNumber(String insuredGrouporPolicyNumber) {
	this.insuredGrouporPolicyNumber = insuredGrouporPolicyNumber;
}

public String getOtherInsuredGroupName() {
	return otherInsuredGroupName;
}

public void setOtherInsuredGroupName(String otherInsuredGroupName) {
	this.otherInsuredGroupName = otherInsuredGroupName;
}

public String getInsuranceTypeCode() {
	return insuranceTypeCode;
}

public void setInsuranceTypeCode(String insuranceTypeCode) {
	this.insuranceTypeCode = insuranceTypeCode;
}

public String getCoordinationofBenefitsCode() {
	return coordinationofBenefitsCode;
}

public void setCoordinationofBenefitsCode(String coordinationofBenefitsCode) {
	this.coordinationofBenefitsCode = coordinationofBenefitsCode;
}

public String getConditionorResponseCode() {
	return conditionorResponseCode;
}

public void setConditionorResponseCode(String conditionorResponseCode) {
	this.conditionorResponseCode = conditionorResponseCode;
}

public String getEmploymentStatusCode() {
	return employmentStatusCode;
}

public void setEmploymentStatusCode(String employmentStatusCode) {
	this.employmentStatusCode = employmentStatusCode;
}

public String getClaimFilingIndicatorCode() {
	return claimFilingIndicatorCode;
}

public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
	this.claimFilingIndicatorCode = claimFilingIndicatorCode;
}



public List<NM1> getNM1List() {
	return NM1List;
}

public void setNM1List(List<NM1> nM1List) {
	NM1List = nM1List;
}

public DMG getDMG() {
	return DMG;
}

public void setDMG(DMG dMG) {
	DMG = dMG;
}

@Override
public String toString() {
	updateTransactionSegmentCount();
	String NM1ListEDIX12String = "";
	if(this.NM1List != null && NM1List.size()>0)
		NM1ListEDIX12String = this.NM1List.stream().map(Object::toString).collect(Collectors.joining(""));
	StringBuilder  SBRsb = new StringBuilder();
	SBRsb.append("SBR").append(Writer.elementTerminator).append(payerResponsibilitySequenceNumberCode).append(Writer.elementTerminator).append(individualRelationshipCode).append(Writer.elementTerminator).append(insuredGrouporPolicyNumber);
	SBRsb.append(Writer.elementTerminator).append(otherInsuredGroupName);
	SBRsb.append(Writer.elementTerminator).append(insuranceTypeCode);
	SBRsb.append(Writer.elementTerminator).append(coordinationofBenefitsCode);
	SBRsb.append(Writer.elementTerminator).append(conditionorResponseCode);
	SBRsb.append(Writer.elementTerminator).append(employmentStatusCode);
	SBRsb.append(Writer.elementTerminator).append(claimFilingIndicatorCode);
	SBRsb.append(Writer.getSegmentTerminator() );
	SBRsb.append(NM1ListEDIX12String);
	SBRsb.append((this.DMG == null ? "" : this.DMG.toString()));
	return SBRsb.toString();
}
  
  
}