package gov.delaware.edi.x12.segment;

import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

@Declaration(fieldSize = 12, name = "NM1")
public class NM1 extends gov.delaware.edi.x12.Segment {
    public NM1() {
        super();
    }
    public NM1(String content) {
    	super(content);
/*		this.put("entityIdentifierCode", elements[1]);
		this.put("entityTypeQualifier", elements[2]);
		this.put("nameLastOrOrganizationName", elements[3]);
		this.put("nameFirst", elements[4]);
		this.put("nameMiddle", elements[5]);
		
		if(elements.length > 6)
			{
			this.put("namePrefix", elements[6]);
			this.put("nameSuffix", elements[7]);
			this.put("identificationCodeQualifier", elements[8]);
			this.put("identificationCode", elements[9]);*/
				this.entityIdentifierCode = elements[1];
				this.entityTypeQualifier = elements[2];
				this.nameLastOrOrganizationName = elements[3];
				this.nameFirst = elements[4];
				this.nameMiddle = elements[5];
				this.namePrefix = elements[6];
				this.nameSuffix = elements[7];
				this.identificationCodeQualifier = elements[8];
				this.identificationCode = elements[9];	
			    this.entityRelationshipCode = elements[10];	
			    this.entityIdentifierCodeNotUsed = elements[11];	
			    this.nameLastOrOrganizationNameNotUsed = elements[12];	
		

/* Not used in FOCUS*/
/*				this.EntityRelationshipCode = elements[10];
				this.EntityIdentifierCodeNotUsed = elements[11];
				this.NameLastOrOrganizationNameNotUsed = elements[12];	*/

    }

    //public String[] elements;
    public String entityIdentifierCode;
    public String entityTypeQualifier;
    public String nameLastOrOrganizationName;
    public String nameFirst;
    public String nameMiddle;
    public String namePrefix;
    public String nameSuffix;
    public String identificationCodeQualifier;
    public String identificationCode;
    
    /*Complex child elemenets*/
    
    public N3 N3;
    public N4 N4;
    public DMG DMG;
    public REF REF;
    public PER PER;
    
    /*Not used in FOCUS*/
    public String entityRelationshipCode;
    public String entityIdentifierCodeNotUsed;
    public String nameLastOrOrganizationNameNotUsed;
    
	public NM1(String entityIdentifierCode, String entityTypeQualifier, String nameLastOrOrganizationName,
			String nameFirst, String nameMiddle, String namePrefix, String nameSuffix,
			String identificationCodeQualifier, String identificationCode, gov.delaware.edi.x12.segment.N3 n3,
			gov.delaware.edi.x12.segment.N4 n4, gov.delaware.edi.x12.segment.DMG dMG,
			gov.delaware.edi.x12.segment.REF rEF, gov.delaware.edi.x12.segment.PER pER) {
		super();
		this.entityIdentifierCode = entityIdentifierCode;
		this.entityTypeQualifier = entityTypeQualifier;
		this.nameLastOrOrganizationName = nameLastOrOrganizationName;
		this.nameFirst = nameFirst;
		this.nameMiddle = nameMiddle;
		this.namePrefix = namePrefix;
		this.nameSuffix = nameSuffix;
		this.identificationCodeQualifier = identificationCodeQualifier;
		this.identificationCode = identificationCode;
		N3 = n3;
		N4 = n4;
		DMG = dMG;
		REF = rEF;
		PER = pER;
	}
	public String getEntityIdentifierCode() {
		return entityIdentifierCode;
	}
	public void setEntityIdentifierCode(String entityIdentifierCode) {
		this.entityIdentifierCode = entityIdentifierCode;
	}
	public String getEntityTypeQualifier() {
		return entityTypeQualifier;
	}
	public void setEntityTypeQualifier(String entityTypeQualifier) {
		this.entityTypeQualifier = entityTypeQualifier;
	}
	public String getNameLastOrOrganizationName() {
		return nameLastOrOrganizationName;
	}
	public void setNameLastOrOrganizationName(String nameLastOrOrganizationName) {
		this.nameLastOrOrganizationName = nameLastOrOrganizationName;
	}
	public String getNameFirst() {
		return nameFirst;
	}
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}
	public String getNameMiddle() {
		return nameMiddle;
	}
	public void setNameMiddle(String nameMiddle) {
		this.nameMiddle = nameMiddle;
	}
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getNameSuffix() {
		return nameSuffix;
	}
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}
	public String getIdentificationCodeQualifier() {
		return identificationCodeQualifier;
	}
	public void setIdentificationCodeQualifier(String identificationCodeQualifier) {
		this.identificationCodeQualifier = identificationCodeQualifier;
	}
	public String getIdentificationCode() {
		return identificationCode;
	}
	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}
	
	
	
	public N3 getN3() {
		return N3;
	}
	public void setN3(N3 n3) {
		N3 = n3;
	}
	public N4 getN4() {
		return N4;
	}
	public void setN4(N4 n4) {
		N4 = n4;
	}
	public DMG getDMG() {
		return DMG;
	}
	public void setDMG(DMG dMG) {
		DMG = dMG;
	}
	public REF getREF() {
		return REF;
	}
	public void setREF(REF rEF) {
		REF = rEF;
	}
	public PER getPER() {
		return PER;
	}
	public void setPER(PER pER) {
		PER = pER;
	}
	
	
	
	
	public String getEntityRelationshipCode() {
		return entityRelationshipCode;
	}
	public void setEntityRelationshipCode(String entityRelationshipCode) {
		this.entityRelationshipCode = entityRelationshipCode;
	}
	public String getEntityIdentifierCodeNotUsed() {
		return entityIdentifierCodeNotUsed;
	}
	public void setEntityIdentifierCodeNotUsed(String entityIdentifierCodeNotUsed) {
		this.entityIdentifierCodeNotUsed = entityIdentifierCodeNotUsed;
	}
	public String getNameLastOrOrganizationNameNotUsed() {
		return nameLastOrOrganizationNameNotUsed;
	}
	public void setNameLastOrOrganizationNameNotUsed(String nameLastOrOrganizationNameNotUsed) {
		this.nameLastOrOrganizationNameNotUsed = nameLastOrOrganizationNameNotUsed;
	}
	@Override
	public String toString() {
		updateTransactionSegmentCount();
		return "NM1" + Writer.elementTerminator + entityIdentifierCode
				+ Writer.elementTerminator + entityTypeQualifier + Writer.elementTerminator + nameLastOrOrganizationName + Writer.elementTerminator + nameFirst + Writer.elementTerminator + nameMiddle
				+ Writer.elementTerminator + (namePrefix == null ? "" : namePrefix) 
				+ Writer.elementTerminator + (nameSuffix == null ? "" : nameSuffix) 
				+ Writer.elementTerminator + (identificationCodeQualifier == null ? "" : identificationCodeQualifier) 
				+ Writer.elementTerminator +  (identificationCode == null ? "" : identificationCode)
				+ Writer.getSegmentTerminator() 
				+ (this.N3 == null ? "" : this.N3.toString()) 
				+ (this.N4 == null ? "" : this.N4.toString()) 
				+ (this.DMG == null ? "" : this.DMG.toString()) 
				+ (this.REF == null ? "" : this.REF.toString()) 
				+ (this.PER ==null ? "" : this.PER.toString()) ;
	}
    
    
}
