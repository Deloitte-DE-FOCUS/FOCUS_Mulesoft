package gov.delaware.edi.x12.segment;

import java.util.Set;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;
@Declaration(fieldSize = 3, name = "N4")
public class N4   extends gov.delaware.edi.x12.Segment {
    public N4() {super();}
    public N4(String content) {
    	super(content);
		this.cityName = elements[1];
		this.stateOrProvinceCode = elements[2];
		this.postalCode = elements[3];
        
        /*		this.put("cityName", elements[1]);
		this.put("stateOrProvinceCode", elements[2]);
		this.put("postalCode", elements[3]);*/
/*		Remaining elements not used in FOCUS
 * 		this.put("countryCode", elements[4]);
		this.put("locationQualifier", elements[5]);
		this.put("locationIdentifier", elements[6]);
		this.put("countrySubdivisionCode", elements[7]);*/

    }
    //public String[] elements;
    public String cityName;
    public String stateOrProvinceCode;
    public String postalCode;
    /*		Below elements not used in FOCUS */
    public String countryCode;
    public String locationQualifier;
    public String locationIdentifier;
    public String countrySubdivisionCode;
    
	public N4(String cityName, String stateOrProvinceCode, String postalCode) {
		super();
		this.cityName = cityName;
		this.stateOrProvinceCode = stateOrProvinceCode;
		this.postalCode = postalCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateOrProvinceCode() {
		return stateOrProvinceCode;
	}
	public void setStateOrProvinceCode(String stateOrProvinceCode) {
		this.stateOrProvinceCode = stateOrProvinceCode;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getLocationQualifier() {
		return locationQualifier;
	}
	public void setLocationQualifier(String locationQualifier) {
		this.locationQualifier = locationQualifier;
	}
	public String getLocationIdentifier() {
		return locationIdentifier;
	}
	public void setLocationIdentifier(String locationIdentifier) {
		this.locationIdentifier = locationIdentifier;
	}
	public String getCountrySubdivisionCode() {
		return countrySubdivisionCode;
	}
	public void setCountrySubdivisionCode(String countrySubdivisionCode) {
		this.countrySubdivisionCode = countrySubdivisionCode;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		return "N4" + Writer.elementTerminator + cityName + Writer.elementTerminator + stateOrProvinceCode + Writer.elementTerminator
				+ postalCode + Writer.getSegmentTerminator();
	} 
}
