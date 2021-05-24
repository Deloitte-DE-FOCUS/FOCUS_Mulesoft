package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 3, name = "PRV")
public class PRV  extends gov.delaware.edi.x12.Segment {
    public PRV() { super(); }
    public PRV(String content) {
        super(content);      
		this.providerCode = elements[1];
		this.referenceIdentificationQualifier = elements[2];
		this.referenceIdentification = elements[3];
/*		this.put("providerCode", elements[1]);
		this.put("referenceIdentificationQualifier", elements[2]);
		this.put("referenceIdentification", elements[3]);*/

/*		Remaining fields are not used for FOCUS
 * 		this.put("stateOrProvinceCode", elements[4]);
		this.put("providerSpecialtyInformation", elements[5]);
		this.put("providerOrganizationCode", elements[6]);*/

    }
    
    
    public String providerCode;
    public String referenceIdentificationQualifier;
    public String referenceIdentification;
    /* Below Fields not used in FOCUS*/
    public String stateOrProvinceCode;
    public String providerSpecialtyInformation;
    public String providerOrganizationCode;
	public PRV(String providerCode, String referenceIdentificationQualifier, String referenceIdentification,
			String stateOrProvinceCode, String providerSpecialtyInformation, String providerOrganizationCode) {
		super();
		this.providerCode = providerCode;
		this.referenceIdentificationQualifier = referenceIdentificationQualifier;
		this.referenceIdentification = referenceIdentification;
		this.stateOrProvinceCode = stateOrProvinceCode;
		this.providerSpecialtyInformation = providerSpecialtyInformation;
		this.providerOrganizationCode = providerOrganizationCode;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public String getReferenceIdentificationQualifier() {
		return referenceIdentificationQualifier;
	}
	public void setReferenceIdentificationQualifier(String referenceIdentificationQualifier) {
		this.referenceIdentificationQualifier = referenceIdentificationQualifier;
	}
	public String getReferenceIdentification() {
		return referenceIdentification;
	}
	public void setReferenceIdentification(String referenceIdentification) {
		this.referenceIdentification = referenceIdentification;
	}
	public String getStateOrProvinceCode() {
		return stateOrProvinceCode;
	}
	public void setStateOrProvinceCode(String stateOrProvinceCode) {
		this.stateOrProvinceCode = stateOrProvinceCode;
	}
	public String getProviderSpecialtyInformation() {
		return providerSpecialtyInformation;
	}
	public void setProviderSpecialtyInformation(String providerSpecialtyInformation) {
		this.providerSpecialtyInformation = providerSpecialtyInformation;
	}
	public String getProviderOrganizationCode() {
		return providerOrganizationCode;
	}
	public void setProviderOrganizationCode(String providerOrganizationCode) {
		this.providerOrganizationCode = providerOrganizationCode;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		return "PRV" + Writer.elementTerminator  + providerCode + Writer.elementTerminator
				+ referenceIdentificationQualifier + Writer.elementTerminator + referenceIdentification
				+ Writer.getSegmentTerminator() ;
	}
    
    
}
