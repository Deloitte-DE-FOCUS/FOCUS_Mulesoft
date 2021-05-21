package gov.delaware.edi.x12.message;

import gov.delaware.edi.x12.segment.SE;
import gov.delaware.edi.x12.segment.ST;

public class Transaction {
	
    public ST transactionSetHeader = null;
    public SE transactionSetTrailer = null;
    public DetailMessage837 detailMessage837 = null;
    public DetailMessage835 detailMessage835 = null;
	
    public Transaction() {
		super();
	}

	public ST getTransactionSetHeader() {
		return transactionSetHeader;
	}

	public void setTransactionSetHeader(ST transactionSetHeader) {
		this.transactionSetHeader = transactionSetHeader;
	}

	public SE getTransactionSetTrailer() {
		return transactionSetTrailer;
	}

	public void setTransactionSetTrailer(SE transactionSetTrailer) {
		this.transactionSetTrailer = transactionSetTrailer;
	}

	public DetailMessage837 getDetailMessage837() {
		return detailMessage837;
	}

	public void setDetailMessage837(DetailMessage837 detailMessage837) {
		this.detailMessage837 = detailMessage837;
	}

	public DetailMessage835 getDetailMessage835() {
		return detailMessage835;
	}

	public void setDetailMessage835(DetailMessage835 detailMessage835) {
		this.detailMessage835 = detailMessage835;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(transactionSetHeader);
		if(this.detailMessage835 != null){
		builder.append(detailMessage835.toString());
		}
		if(this.detailMessage837 != null) {
		builder.append(detailMessage837.toString());
		}
		builder.append(transactionSetTrailer);
		return builder.toString();
	}
    
	
    
}
