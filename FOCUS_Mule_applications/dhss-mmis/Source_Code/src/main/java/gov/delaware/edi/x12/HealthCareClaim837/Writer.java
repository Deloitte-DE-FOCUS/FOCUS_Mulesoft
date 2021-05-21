package gov.delaware.edi.x12.HealthCareClaim837;

public class Writer {

	public static String segmentTerminator="~";
	public static String elementTerminator="*";
	public static String componentTerminator=":";
	
	public static int transactionSegmentCount = 0 ;
	public static int hierarchicalIDNumber = 0 ;
	public static int childhHerarchicalIDNumber = 0 ;

	public static int getChildhHerarchicalIDNumber() {
		return childhHerarchicalIDNumber;
	}
	public static void setChildhHerarchicalIDNumber(int childhHerarchicalIDNumber) {
		Writer.childhHerarchicalIDNumber = childhHerarchicalIDNumber;
	}
	public static void resetTransactionSegmentCount()
	{
		Writer.transactionSegmentCount = 0;
	}
	public static void resetHierarchicalIDNumber()
	{
		Writer.hierarchicalIDNumber = 0;
	}

	
	public static int getHierarchicalIDNumber() {
		return hierarchicalIDNumber;
	}
	public static void setHierarchicalIDNumber(int hierarchicalIDNumber) {
		Writer.hierarchicalIDNumber = hierarchicalIDNumber;
	}
	public static String getSegmentTerminator() {
		return segmentTerminator;
	}
	public static void setSegmentTerminator(String segmentTerminator) {
		Writer.segmentTerminator = segmentTerminator;
	}
	public static String getElementTerminator() {
		return elementTerminator;
	}
	public static void setElementTerminator(String elementTerminator) {
		Writer.elementTerminator = elementTerminator;
	}
	public static String getComponentTerminator() {
		return componentTerminator;
	}
	public static void setComponentTerminator(String componentTerminator) {
		Writer.componentTerminator = componentTerminator;
	}
	public static int getTransactionSegmentCount() {
		return transactionSegmentCount;
	}
	public static void setTransactionSegmentCount(int transactionSegmentCount) {
		Writer.transactionSegmentCount = transactionSegmentCount;
	}

	
	


}
