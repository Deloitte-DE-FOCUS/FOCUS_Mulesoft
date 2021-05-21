package gov.delaware.edi.x12.segment;

import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 7, name = "CLM")
public class CLM extends gov.delaware.edi.x12.Segment { //Redo
    public CLM() { super(); }
    public CLM(String content) {
    	super(content);   
		this.patientAccountNumber = elements[1];
		this.totalClaimChargeAmount = elements[2];
	    this.claimFilingIndicatorCode = elements[3];
	    this.nonInstitutionalClaimTypeCode = elements[4];
		this.healthCareServiceLocationInformation = elements[5];

	    this.yesNoConditionOrResponseCode1 = elements[6];
	    this.medicareAssignmentCode = elements[7];
	    this.benefitsAssignmentCertificationIndicator = elements[8];
	    this.releaseofInformationCode = elements[9];

/*		this.put("patientAccountNumber", elements[1]);
		this.put("totalClaimChargeAmount", elements[2]);
		//this.put("claimFilingIndicatorCode", elements[2]);// Maybe not required
		//this.put("nonInstitutionalClaimTypeCode", elements[3]);// Maybe not required
		this.put("healthCareServiceLocationInformation", elements[3]);
		this.put("facilityTypeCode", elements[4]);
		this.put("facilityCodeQualifier", elements[5]);
		this.put("claimFrequencyCode", elements[6]);
		this.put("benefitsAssignmentCertificationIndicator", elements[7]);
		this.put("benefitsAssignmentCertificationIndicator", elements[8]);
		this.put("benefitsAssignmentCertificationIndicator", elements[9]);
		//this.put("ReleaseofInformationCode", elements[10]);
*/    }

    public String patientAccountNumber;
    public String totalClaimChargeAmount;
    public String claimFilingIndicatorCode;
    public String nonInstitutionalClaimTypeCode;
    public String healthCareServiceLocationInformation;
    
/*    public String facilityTypeCode;
    public String facilityCodeQualifier;
    public String claimFrequencyCode;*/
    public String yesNoConditionOrResponseCode1;
    public String medicareAssignmentCode;
    public String benefitsAssignmentCertificationIndicator;
    public String releaseofInformationCode;
    
    /*Complex Elements Below*/
	public List<DTP> DTPList;
    public CL1 CL1;
    public CN1 CN1;
    public REF REF;
    public NTE NTE;
    public List<HI> HIList;
    public List<NM1> NM1List;
    
    public List<SBR> SBRList;
    public PRV PRV; //Looks like a deviation from the standard x12
    
    public List<LX> LXList;
    
    
    public List<HI> getHIList() {
		return HIList;
	}
	public List<DTP> getDTPList() {
		return DTPList;
	}
	public void setDTPList(List<DTP> dTP) {
		DTPList = dTP;
	}
	public CL1 getCL1() {
		return CL1;
	}
	public void setCL1(CL1 cL1) {
		CL1 = cL1;
	}
	public CN1 getCN1() {
		return CN1;
	}
	public void setCN1(CN1 cN1) {
		CN1 = cN1;
	}
	public NTE getNTE() {
		return NTE;
	}
	public void setNTE(NTE nTE) {
		NTE = nTE;
	}
	public List<HI> getHI() {
		return HIList;
	}
	public void setHIList(List<HI> hIList) {
		HIList = hIList;
	}
	public List<NM1> getNM1List() {
		return NM1List;
	}
	public void setNM1List(List<NM1> nM1List) {
		NM1List = nM1List;
	}
	public List<SBR> getSBRList() {
		return SBRList;
	}
	public void setSBRList(List<SBR> sBRList) {
		SBRList = sBRList;
	}
	
	public PRV getPRV() {
		return PRV;
	}
	public void setPRV(PRV pRV) {
		PRV = pRV;
	}
	public List<LX> getLXList() {
		return LXList;
	}
	public void setLXList(List<LX> lXList) {
		LXList = lXList;
	}
    /*	Below Fields are not used */
    public String patientSignatureSourceCode;
    public String relatedCausesInformation;
    public String specialProgramIndicator;
    
    public String levelOfServiceCode;
    public String yesNoConditionOrResponseCode2;
    public String participationAgreement;
    public String claimStatusCode;
    public String yesNoConditionOrResponseCode3;
    public String claimSubmissionReasonCode;
    public String delayReasonCode;
    
	public String getMedicareAssignmentCode() {
		return medicareAssignmentCode;
	}
	public void setMedicareAssignmentCode(String medicareAssignmentCode) {
		this.medicareAssignmentCode = medicareAssignmentCode;
	}
	public REF getREF() {
		return REF;
	}
	public void setREF(REF rEF) {
		REF = rEF;
	}
	public CLM(String patientAccountNumber, String totalClaimChargeAmount, String claimFilingIndicatorCode,
			String nonInstitutionalClaimTypeCode, String healthCareServiceLocationInformation,
			String yesNoConditionOrResponseCode1, String medicareAssignmentCode,
			String benefitsAssignmentCertificationIndicator, String releaseofInformationCode) {
		super();
		this.patientAccountNumber = patientAccountNumber;
		this.totalClaimChargeAmount = totalClaimChargeAmount;
		this.claimFilingIndicatorCode = claimFilingIndicatorCode;
		this.nonInstitutionalClaimTypeCode = nonInstitutionalClaimTypeCode;
		this.healthCareServiceLocationInformation = healthCareServiceLocationInformation;
		this.yesNoConditionOrResponseCode1 = yesNoConditionOrResponseCode1;
		this.medicareAssignmentCode = medicareAssignmentCode;
		this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
		this.releaseofInformationCode = releaseofInformationCode;
	}
	public String getPatientAccountNumber() {
		return patientAccountNumber;
	}
	public void setPatientAccountNumber(String patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}
	public String getTotalClaimChargeAmount() {
		return totalClaimChargeAmount;
	}
	public void setTotalClaimChargeAmount(String totalClaimChargeAmount) {
		this.totalClaimChargeAmount = totalClaimChargeAmount;
	}
	public String getHealthCareServiceLocationInformation() {
		return healthCareServiceLocationInformation;
	}
	public void setHealthCareServiceLocationInformation(String healthCareServiceLocationInformation) {
		this.healthCareServiceLocationInformation = healthCareServiceLocationInformation;
	}

	public String getBenefitsAssignmentCertificationIndicator() {
		return benefitsAssignmentCertificationIndicator;
	}
	public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
		this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
	}
	public String getClaimFilingIndicatorCode() {
		return claimFilingIndicatorCode;
	}
	public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
		this.claimFilingIndicatorCode = claimFilingIndicatorCode;
	}
	public String getNonInstitutionalClaimTypeCode() {
		return nonInstitutionalClaimTypeCode;
	}
	public void setNonInstitutionalClaimTypeCode(String nonInstitutionalClaimTypeCode) {
		this.nonInstitutionalClaimTypeCode = nonInstitutionalClaimTypeCode;
	}
	public String getReleaseofInformationCode() {
		return releaseofInformationCode;
	}
	public void setReleaseofInformationCode(String releaseofInformationCode) {
		this.releaseofInformationCode = releaseofInformationCode;
	}
	public String getPatientSignatureSourceCode() {
		return patientSignatureSourceCode;
	}
	public void setPatientSignatureSourceCode(String patientSignatureSourceCode) {
		this.patientSignatureSourceCode = patientSignatureSourceCode;
	}
	public String getRelatedCausesInformation() {
		return relatedCausesInformation;
	}
	public void setRelatedCausesInformation(String relatedCausesInformation) {
		this.relatedCausesInformation = relatedCausesInformation;
	}
	public String getSpecialProgramIndicator() {
		return specialProgramIndicator;
	}
	public void setSpecialProgramIndicator(String specialProgramIndicator) {
		this.specialProgramIndicator = specialProgramIndicator;
	}
	public String getYesNoConditionOrResponseCode1() {
		return yesNoConditionOrResponseCode1;
	}
	public void setYesNoConditionOrResponseCode1(String yesNoConditionOrResponseCode1) {
		this.yesNoConditionOrResponseCode1 = yesNoConditionOrResponseCode1;
	}
	public String getLevelOfServiceCode() {
		return levelOfServiceCode;
	}
	public void setLevelOfServiceCode(String levelOfServiceCode) {
		this.levelOfServiceCode = levelOfServiceCode;
	}
	public String getYesNoConditionOrResponseCode2() {
		return yesNoConditionOrResponseCode2;
	}
	public void setYesNoConditionOrResponseCode2(String yesNoConditionOrResponseCode2) {
		this.yesNoConditionOrResponseCode2 = yesNoConditionOrResponseCode2;
	}
	public String getParticipationAgreement() {
		return participationAgreement;
	}
	public void setParticipationAgreement(String participationAgreement) {
		this.participationAgreement = participationAgreement;
	}
	public String getClaimStatusCode() {
		return claimStatusCode;
	}
	public void setClaimStatusCode(String claimStatusCode) {
		this.claimStatusCode = claimStatusCode;
	}
	public String getYesNoConditionOrResponseCode3() {
		return yesNoConditionOrResponseCode3;
	}
	public void setYesNoConditionOrResponseCode3(String yesNoConditionOrResponseCode3) {
		this.yesNoConditionOrResponseCode3 = yesNoConditionOrResponseCode3;
	}
	public String getClaimSubmissionReasonCode() {
		return claimSubmissionReasonCode;
	}
	public void setClaimSubmissionReasonCode(String claimSubmissionReasonCode) {
		this.claimSubmissionReasonCode = claimSubmissionReasonCode;
	}
	public String getDelayReasonCode() {
		return delayReasonCode;
	}
	public void setDelayReasonCode(String delayReasonCode) {
		this.delayReasonCode = delayReasonCode;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("CLM");
		builder.append(Writer.elementTerminator);
		builder.append(patientAccountNumber);
		builder.append(Writer.elementTerminator);
		builder.append(totalClaimChargeAmount);
		builder.append(Writer.elementTerminator);
		builder.append(claimFilingIndicatorCode);
		builder.append(Writer.elementTerminator);
		builder.append(nonInstitutionalClaimTypeCode);
		builder.append(Writer.elementTerminator);
		builder.append(healthCareServiceLocationInformation);
		builder.append(Writer.elementTerminator);
		/*builder.append(facilityTypeCode);
		builder.append(Writer.elementTerminator);
		builder.append(facilityCodeQualifier);
		builder.append(Writer.elementTerminator);
		builder.append(claimFrequencyCode);
		builder.append(Writer.elementTerminator);*/
	    builder.append(yesNoConditionOrResponseCode1);
	    builder.append(Writer.elementTerminator);
	    builder.append(medicareAssignmentCode);
	    builder.append(Writer.elementTerminator);
	    builder.append(benefitsAssignmentCertificationIndicator);
	    builder.append(Writer.elementTerminator);
		builder.append(releaseofInformationCode);
		builder.append(Writer.getSegmentTerminator());
		
		builder.append((DTPList != null && DTPList.size() > 0)  ?  DTPList.stream().map(Object::toString).collect(Collectors.joining("")) : "");
		builder.append(CL1 == null ? "" :CL1.toString());
		builder.append(CN1 == null ? "" :CN1.toString());
		builder.append(REF == null ? "" :REF.toString());
		builder.append(NTE == null ? "" :NTE.toString());
		
		builder.append((HIList != null && HIList.size() > 0)  ?  HIList.stream().map(Object::toString).collect(Collectors.joining("")) : "");
		builder.append((NM1List != null && NM1List.size() > 0)  ?  NM1List.stream().map(Object::toString).collect(Collectors.joining("")) : "");
		
		builder.append((SBRList != null && SBRList.size() > 0)  ?  SBRList.stream().map(Object::toString).collect(Collectors.joining("")) : "");		
		builder.append(this.PRV != null ? this.PRV.toString() : ""); // Looks like a deviation
		
		builder.append((LXList != null && LXList.size() > 0)  ?  LXList.stream().map(Object::toString).collect(Collectors.joining("")) : "");
		return builder.toString();
	} 
	
	
}