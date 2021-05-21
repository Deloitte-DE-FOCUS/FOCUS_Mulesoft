package gov.delaware.edi.x12.message;

import gov.delaware.edi.x12.segment.SE;
import gov.delaware.edi.x12.segment.ST;

public class HealthCareClaimInstTransaction extends Transaction{
	
	public HealthCareClaimInstTransaction() {
		super();
	}

	public HealthCareClaimInstTransaction(ST transactionSetHeader, SE transactionSetTrailer,
			DetailMessage837 detailMessage837) {
		super();
		this.transactionSetHeader = transactionSetHeader;
		this.transactionSetTrailer = transactionSetTrailer;
		this.detailMessage837 = detailMessage837;
	}
}
