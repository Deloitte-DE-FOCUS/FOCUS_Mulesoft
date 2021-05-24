package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 12, name = "SV2")
public class SV2  extends gov.delaware.edi.x12.Segment {
    // As per 837 FOCUS
	public SV2() { super(); }
    public SV2(String content) {
    	super(content);
/*		this.put("productOrServiceID", elements[1]);
		this.put("compositeMedicalProcedureIdentifier", elements[2]);
		this.put("lineItemChargeAmount", elements[3]);
		this.put("unitorBasisforMeasurementCode", elements[4]); 
		this.put("serviceUnitsOrDays", elements[5]);
		*/
		this.productOrServiceID = elements[1];
		this.compositeMedicalProcedureIdentifier = elements[2];
		this.lineItemChargeAmount = elements[3];
		this.unitorBasisforMeasurementCode = elements[4];
		this.serviceUnitsOrDays = elements[5];
		this.productOrServiceID = elements[6];
		this.compositeDiagnosisCodePointer = elements[7];
   }

	public String productOrServiceID;
	public String compositeMedicalProcedureIdentifier;
	public String lineItemChargeAmount;
	public String unitorBasisforMeasurementCode;
	public String serviceUnitsOrDays;
	
	/*837 Pro Fields*/
	public String serviceTypeCode;
	public String compositeDiagnosisCodePointer;
	
	/*Below fields not used by FOCUS*/
	public String monetaryAmount;
	public String emergencyIndicator;
	public String multipleProcedureCode;
	public String EPSDTIndicator;
	public String familyPlanningIndicator;
	

	public SV2(String productOrServiceID, String compositeMedicalProcedureIdentifier, String lineItemChargeAmount,
			String unitorBasisforMeasurementCode, String serviceUnitsOrDays, String serviceTypeCode,
			String compositeDiagnosisCodePointer, String monetaryAmount, String emergencyIndicator,
			String multipleProcedureCode, String ePSDTIndicator, String familyPlanningIndicator) {
		super();
		this.productOrServiceID = productOrServiceID;
		this.compositeMedicalProcedureIdentifier = compositeMedicalProcedureIdentifier;
		this.lineItemChargeAmount = lineItemChargeAmount;
		this.unitorBasisforMeasurementCode = unitorBasisforMeasurementCode;
		this.serviceUnitsOrDays = serviceUnitsOrDays;
		this.serviceTypeCode = serviceTypeCode;
		this.compositeDiagnosisCodePointer = compositeDiagnosisCodePointer;
		this.monetaryAmount = monetaryAmount;
		this.emergencyIndicator = emergencyIndicator;
		this.multipleProcedureCode = multipleProcedureCode;
		this.EPSDTIndicator = ePSDTIndicator;
		this.familyPlanningIndicator = familyPlanningIndicator;
	}
	public String getProductOrServiceID() {
		return productOrServiceID;
	}
	public void setProductOrServiceID(String productOrServiceID) {
		this.productOrServiceID = productOrServiceID;
	}
	public String getCompositeMedicalProcedureIdentifier() {
		return compositeMedicalProcedureIdentifier;
	}
	public void setCompositeMedicalProcedureIdentifier(String compositeMedicalProcedureIdentifier) {
		this.compositeMedicalProcedureIdentifier = compositeMedicalProcedureIdentifier;
	}
	public String getLineItemChargeAmount() {
		return lineItemChargeAmount;
	}
	public void setLineItemChargeAmount(String lineItemChargeAmount) {
		this.lineItemChargeAmount = lineItemChargeAmount;
	}
	public String getUnitorBasisforMeasurementCode() {
		return unitorBasisforMeasurementCode;
	}
	public void setUnitorBasisforMeasurementCode(String unitorBasisforMeasurementCode) {
		this.unitorBasisforMeasurementCode = unitorBasisforMeasurementCode;
	}
	public String getServiceUnitsOrDays() {
		return serviceUnitsOrDays;
	}
	public void setServiceUnitsOrDays(String serviceUnitsOrDays) {
		this.serviceUnitsOrDays = serviceUnitsOrDays;
	}
	
	
	
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
	public String getCompositeDiagnosisCodePointer() {
		return compositeDiagnosisCodePointer;
	}
	public void setCompositeDiagnosisCodePointer(String compositeDiagnosisCodePointer) {
		this.compositeDiagnosisCodePointer = compositeDiagnosisCodePointer;
	}
	public String getMonetaryAmount() {
		return monetaryAmount;
	}
	public void setMonetaryAmount(String monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}
	public String getEmergencyIndicator() {
		return emergencyIndicator;
	}
	public void setEmergencyIndicator(String emergencyIndicator) {
		this.emergencyIndicator = emergencyIndicator;
	}
	public String getMultipleProcedureCode() {
		return multipleProcedureCode;
	}
	public void setMultipleProcedureCode(String multipleProcedureCode) {
		this.multipleProcedureCode = multipleProcedureCode;
	}
	public String getEPSDTIndicator() {
		return EPSDTIndicator;
	}
	public void setEPSDTIndicator(String ePSDTIndicator) {
		EPSDTIndicator = ePSDTIndicator;
	}
	public String getFamilyPlanningIndicator() {
		return familyPlanningIndicator;
	}
	public void setFamilyPlanningIndicator(String familyPlanningIndicator) {
		this.familyPlanningIndicator = familyPlanningIndicator;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder sb = new StringBuilder();
		/*837 Ins and Pro*/
		sb.append("SV2").append(Writer.elementTerminator).append(productOrServiceID).append(Writer.elementTerminator).append(compositeMedicalProcedureIdentifier).append(Writer.elementTerminator).append(lineItemChargeAmount).append(Writer.elementTerminator).append(unitorBasisforMeasurementCode).append(Writer.elementTerminator).append(serviceUnitsOrDays);
		
		/*837 Pro*/
		if(this.serviceTypeCode !=null)
			sb.append(Writer.elementTerminator).append(this.serviceTypeCode).append(Writer.elementTerminator).append(this.compositeDiagnosisCodePointer);
		sb.append(Writer.getSegmentTerminator() );
		return sb.toString();
	}
	
	
}