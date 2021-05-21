package gov.delaware.edi.x12.segment;



import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 3, name = "DMG")
public class DMG extends gov.delaware.edi.x12.Segment {
    public DMG() { super(); }
    public DMG(String content) {
    	super(content);
		this.dateTimePeriodFormatQualifier = elements[1];
		this.dateTimePeriod = elements[2];
		this.genderCode = elements[3];

		/*	Below field's not used for FOCUS	
		 * this.put("setMaritalStatusCode", elements[4]);
		this.put("compositeRaceOrEthnicityInformation", elements[5]);
		this.put("citizenshipStatusCode", elements[6]);
		this.put("countryCode", elements[7]);
		this.put("basisOfVerificationCode", elements[8]);
		this.put("quantity", elements[9]);
		this.put("codeListQualifierCode", elements[10]);
		this.put("quantsetIndustryCodeity", elements[11]);*/
    }
	public String dateTimePeriodFormatQualifier;
	public String dateTimePeriod;
	public String genderCode;
	
	/* Not used By FOCUS */
	public String maritalStatusCode;
	public String compositeRaceOrEthnicityInformation;
	public String citizenshipStatusCode;
	public String countryCode;
	public String basisOfVerificationCode;
	public String quantity;
	public String codeListQualifierCode;
	public String quantsetIndustryCodeity;
    
    
    
	public DMG(String dateTimePeriodFormatQualifier, String dateTimePeriod, String genderCode,
			String maritalStatusCode, String compositeRaceOrEthnicityInformation, String citizenshipStatusCode,
			String countryCode, String basisOfVerificationCode, String quantity, String codeListQualifierCode,
			String quantsetIndustryCodeity) {
		super();
		this.dateTimePeriodFormatQualifier = dateTimePeriodFormatQualifier;
		this.dateTimePeriod = dateTimePeriod;
		this.genderCode = genderCode;
		this.maritalStatusCode = maritalStatusCode;
		this.compositeRaceOrEthnicityInformation = compositeRaceOrEthnicityInformation;
		this.citizenshipStatusCode = citizenshipStatusCode;
		this.countryCode = countryCode;
		this.basisOfVerificationCode = basisOfVerificationCode;
		this.quantity = quantity;
		this.codeListQualifierCode = codeListQualifierCode;
		this.quantsetIndustryCodeity = quantsetIndustryCodeity;
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
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getMaritalStatusCode() {
		return maritalStatusCode;
	}
	public void setMaritalStatusCode(String maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}
	public String getCompositeRaceOrEthnicityInformation() {
		return compositeRaceOrEthnicityInformation;
	}
	public void setCompositeRaceOrEthnicityInformation(String compositeRaceOrEthnicityInformation) {
		this.compositeRaceOrEthnicityInformation = compositeRaceOrEthnicityInformation;
	}
	public String getCitizenshipStatusCode() {
		return citizenshipStatusCode;
	}
	public void setCitizenshipStatusCode(String citizenshipStatusCode) {
		this.citizenshipStatusCode = citizenshipStatusCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getBasisOfVerificationCode() {
		return basisOfVerificationCode;
	}
	public void setBasisOfVerificationCode(String basisOfVerificationCode) {
		this.basisOfVerificationCode = basisOfVerificationCode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCodeListQualifierCode() {
		return codeListQualifierCode;
	}
	public void setCodeListQualifierCode(String codeListQualifierCode) {
		this.codeListQualifierCode = codeListQualifierCode;
	}
	public String getQuantsetIndustryCodeity() {
		return quantsetIndustryCodeity;
	}
	public void setQuantsetIndustryCodeity(String quantsetIndustryCodeity) {
		this.quantsetIndustryCodeity = quantsetIndustryCodeity;
	}
/*	@Override
	public String toString() {
		return "DMG*" + dateTimePeriodFormatQualifier + "*"
				+ dateTimePeriod + "*" + genderCode + "~";
	}*/
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		StringBuilder builder = new StringBuilder();
		builder.append("DMG");
		builder.append(Writer.elementTerminator);
		builder.append(dateTimePeriodFormatQualifier);
		builder.append(Writer.elementTerminator);
		builder.append(dateTimePeriod);
		builder.append(Writer.elementTerminator);
		builder.append(genderCode);
		builder.append(Writer.getSegmentTerminator());
		return builder.toString();
	}

	
}
