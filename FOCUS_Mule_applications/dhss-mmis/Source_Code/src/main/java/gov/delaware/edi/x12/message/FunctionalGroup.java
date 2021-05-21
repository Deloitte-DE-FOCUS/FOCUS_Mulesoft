package gov.delaware.edi.x12.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gov.delaware.edi.x12.message.Transaction;
import gov.delaware.edi.x12.segment.GE;
import gov.delaware.edi.x12.segment.GS;


public class FunctionalGroup {
    private GS functionalGroupHeader;
    private List<Transaction> transactions;
    private GE functionalGroupTrailer;

    public FunctionalGroup() {
        functionalGroupHeader = new GS();
        transactions = new ArrayList<Transaction>();
        functionalGroupTrailer = new GE();
    }

    public FunctionalGroup(GS functionalGroupHeader, List<Transaction> transactions, GE functionalGroupTrailer) {
		super();
		this.functionalGroupHeader = functionalGroupHeader;
		this.transactions = transactions;
		this.functionalGroupTrailer = functionalGroupTrailer;
	}

	public GS getFunctionalGroupHeader() {
        return functionalGroupHeader;
    }

    public void setFunctionalGroupHeader(GS functionalGroupHeader) {
        this.functionalGroupHeader = functionalGroupHeader;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public GE getFunctionalGroupTrailer() {
        return functionalGroupTrailer;
    }

    public void setFunctionalGroupTrailer(GE functionalGroupTrailer) {
        this.functionalGroupTrailer = functionalGroupTrailer;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(functionalGroupHeader.toString());
		if(this.transactions != null && this.transactions.size() > 0){
		builder.append(transactions.stream().map(Object::toString).collect(Collectors.joining("")));
		}
		builder.append(functionalGroupTrailer.toString());
		return builder.toString();
	}
    
    
}
