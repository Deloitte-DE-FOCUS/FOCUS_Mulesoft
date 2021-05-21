package gov.delaware.edi.x12.HealthCareClaim837;

import java.io.InputStream;
import java.util.Scanner;
import gov.delaware.edi.x12.Segment;
import gov.delaware.edi.x12.message.DetailMessage835;
import gov.delaware.edi.x12.message.DetailMessage837;
import gov.delaware.edi.x12.message.FunctionalGroup;
import gov.delaware.edi.x12.message.HealthCareClaimInstTransaction;
import gov.delaware.edi.x12.message.InterchangeEnvelope;
import gov.delaware.edi.x12.message.Transaction;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import gov.delaware.edi.x12.segment.BHT;
import gov.delaware.edi.x12.segment.CL1;
import gov.delaware.edi.x12.segment.CLM;
import gov.delaware.edi.x12.segment.CN1;
import gov.delaware.edi.x12.segment.DMG;
import gov.delaware.edi.x12.segment.DTP;
import gov.delaware.edi.x12.segment.GE;
import gov.delaware.edi.x12.segment.GS;
import gov.delaware.edi.x12.segment.HI;
import gov.delaware.edi.x12.segment.HL;
import gov.delaware.edi.x12.segment.HLLoop;
import gov.delaware.edi.x12.segment.IEA;
import gov.delaware.edi.x12.segment.ISA;
import gov.delaware.edi.x12.segment.LX;
import gov.delaware.edi.x12.segment.N3;
import gov.delaware.edi.x12.segment.N4;
import gov.delaware.edi.x12.segment.NM1;
import gov.delaware.edi.x12.segment.NTE;
import gov.delaware.edi.x12.segment.PER;
import gov.delaware.edi.x12.segment.PRV;
import gov.delaware.edi.x12.segment.REF;
import gov.delaware.edi.x12.segment.SBR;
import gov.delaware.edi.x12.segment.SE;
import gov.delaware.edi.x12.segment.ST;
import gov.delaware.edi.x12.segment.SV2;

public class Reader {

	public static String segmentTerminator="~";
	public static String elementTerminator="*";
	public static String componentTerminator=":";
	public static String segment = "";
	public static String getSegment() {
		return segment;
	}

	public static void setSegment(String segment) {
		Reader.segment = segment;
	}

	public static Map process837(InputStream is)
	{
		Map<String,Object> Content = new LinkedHashMap<String,Object>();
		try {
			//FileInputStream fis = new FileInputStream("src/main/resources/abc.txt");
			//String x12String = IOUtils.toString(fis, "UTF-8");
			
			Scanner sc = new Scanner(is);
			sc.useDelimiter("~");
			String[] elements=null;
			Segment currentSegment =  new Segment();
			/*		
			 * if(sc.hasNext())
			{
					elements = sc.next().split("*");
					if(elements[0].equals("I"))
			}
			*/
			//Map<String, Object> finalResult = new HashMap<String,Object>(); 	
			ISA interchangeControlHeader = getInterchangeControlHeader(sc);
			GS functionalGroupHeader = getFunctionalGroupHeader(sc);
			ST transactionSetHeader = getTransactionSetHeader(sc);
			Content.put("interchangeControlHeader", interchangeControlHeader);
			Content.put("functionalGroupHeader", functionalGroupHeader);
			Content.put("transactionSetHeader", transactionSetHeader);
			
			//Map  detailSegments = processNextDetailSegment(sc);			
			//Content.put("detailSegments", detailSegments);
			
			SE transactionSetTrailer = getTransactionSetTrailer(sc);
			GE  functionalGroupTrailer = getFunctionalGroupTrailer(sc);
			IEA  interchangeControlTrailer = getInterchangeControlTrailer(sc);
			Content.put("transactionSetTrailer", interchangeControlHeader);
			Content.put("functionalGroupTrailer", functionalGroupHeader);
			Content.put("interchangeControlTrailer", transactionSetHeader);
			sc.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Segment which caused the error :" + getCurrentSegment());
		}
		return Content;
	}

	public static InterchangeEnvelope getInterchangeEnvelope(Scanner sc)
	{
		ISA interchangeControlHeader = getInterchangeControlHeader(sc);
		List<FunctionalGroup>  functionalGroupList = getFunctionalGroupList(sc);
		IEA  interchangeControlTrailer = getInterchangeControlTrailer(sc);
		InterchangeEnvelope envelope = new InterchangeEnvelope(interchangeControlHeader, functionalGroupList, interchangeControlTrailer);
		return envelope;
	}
	
	public static ISA getInterchangeControlHeader(Scanner sc)
	{
		String interchangeControlHeader = getNextSegment(sc);
		interchangeControlHeader = interchangeControlHeader.startsWith(System.lineSeparator()) ? interchangeControlHeader.substring(1) : interchangeControlHeader ;
		ISA ISASegment = new ISA(interchangeControlHeader);	
		return ISASegment;
	}
	public static IEA getInterchangeControlTrailer(Scanner sc)
	{
		
		String interchangeControlTrailer = getCurrentSegment();
		interchangeControlTrailer = interchangeControlTrailer.startsWith(System.lineSeparator()) ? interchangeControlTrailer.substring(1) : interchangeControlTrailer ;
		IEA ISASegment = new IEA(interchangeControlTrailer);	
		return ISASegment;
	}
	public static List<FunctionalGroup> getFunctionalGroupList(Scanner sc)
	{
		getNextSegment(sc);
		List<FunctionalGroup> functionalGroups = new ArrayList<FunctionalGroup>();
		while(getCurrentSegment().startsWith("GS"))
		{
			FunctionalGroup functionalGroup = getFunctionalGroup(sc);
			functionalGroups.add(functionalGroup);
		}
		return functionalGroups; 
	}
	public static FunctionalGroup getFunctionalGroup(Scanner sc)
	{			
		GS GSSegment = new GS(getCurrentSegment());
		getNextSegment(sc);
		List<Transaction> transactions = getTransactions(sc);
		GE  GESegment = getFunctionalGroupTrailer(sc);
		getNextSegment(sc);
		return new FunctionalGroup(GSSegment, transactions, GESegment);
	}	


	
	public static List<Transaction> getTransactions(Scanner sc)
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		while(getCurrentSegment().startsWith("ST"))
		{
			Transaction transaction = getTransaction(sc);
			transactions.add(transaction);
		}
		return transactions;
	}
	public static Transaction getTransaction(Scanner sc)
	{
		Transaction transaction = null; 
		ST STSegment = getTransactionSetHeader(sc);
		getNextSegment(sc);
		String X12Type = STSegment.getTransactionSetIDCode();
		if(X12Type.equals("837"))
			{
				DetailMessage837 detailSegments = processNextDetailSegment837(sc);
				SE transactionSetTrailer = getTransactionSetTrailer(sc);
				getNextSegment(sc);
				transaction = new HealthCareClaimInstTransaction(STSegment,transactionSetTrailer,detailSegments);
			}
		else if(X12Type.equals("835"))
		{
			DetailMessage835 detailSegments =null; // To be implemented if Required
			SE transactionSetTrailer = getTransactionSetTrailer(sc);
			getNextSegment(sc);
			transaction = null;
		}
		return transaction;
	}
	
	public static GS getFunctionalGroupHeader(Scanner sc)
	{
		
		String functionalGroupHeader = getNextSegment(sc);
		
		functionalGroupHeader = functionalGroupHeader.startsWith(System.lineSeparator()) ? functionalGroupHeader.substring(1) : functionalGroupHeader ;
		GS GSSegment = new GS(functionalGroupHeader);	
		return GSSegment;
	}
	public static GE getFunctionalGroupTrailer(Scanner sc)
	{
		String functionalGroupTrailer = getCurrentSegment();
		functionalGroupTrailer = functionalGroupTrailer.startsWith(System.lineSeparator()) ? functionalGroupTrailer.substring(1) : functionalGroupTrailer ;
		GE GSSegment = new GE(functionalGroupTrailer);	
		return GSSegment;
	}
	
	public static ST getTransactionSetHeader(Scanner sc)
	{
		String transactionSetHeader = getCurrentSegment();
		
		transactionSetHeader = transactionSetHeader.startsWith(System.lineSeparator()) ? transactionSetHeader.substring(1) : transactionSetHeader ;
		ST STSegment = new ST(transactionSetHeader);	
		return STSegment;
	}
	
	public static SE getTransactionSetTrailer(Scanner sc)
	{
		
		String transactionSetHeader = getCurrentSegment();
		transactionSetHeader = transactionSetHeader.startsWith(System.lineSeparator()) ? transactionSetHeader.substring(1) : transactionSetHeader ;
		SE STSegment = new SE(transactionSetHeader);	
		return STSegment;
	}	
	
	public static DetailMessage837 processNextDetailSegment837(Scanner sc)
	{
		Map<String,Object> detailSegments = new LinkedHashMap<String,Object>();
		BHT BHT = null;
		REF REF = null;
		List<NM1> NM1List = null;
		List<HL> HLList = null;
		List<HLLoop> HLLoopList = null;
		//getNextSegment(sc);
		if(getCurrentSegment().startsWith("BHT"))
			{
				BHT = new BHT(getCurrentSegment());
				//detailSegments.put("BHT",new BHT(getCurrentSegment()));
				getNextSegment(sc);	
			}
		if(getCurrentSegment().startsWith("REF"))
		{
			REF = new REF(getCurrentSegment());
			//detailSegments.put("REF",new REF(getCurrentSegment()));
			getNextSegment(sc);
		}
		if(getCurrentSegment().startsWith("NM1"))
		{
			 NM1List = processNM1(sc);
			//	detailSegments.put("NM1List",processNM1(sc));
		}
		if(getCurrentSegment().startsWith("HL"))
		{
			HLLoopList = processHL(sc);
			//detailSegments.put("HLList",processHL(sc));
		}
		return new DetailMessage837(BHT, REF, NM1List, HLLoopList);
	}
	
	public static List<NM1> processNM1(Scanner sc)
	{	
		Map<String,Object> map = new HashMap<String,Object>();
		List<NM1> NM1List = new ArrayList<NM1>();
		//NM1List.add(new NM1(getCurrentSegment()));
		while(sc.hasNext() && getCurrentSegment().startsWith("NM1"))
		{
			NM1 nm1 = new NM1(getCurrentSegment());
			getNextSegment(sc);

			/*Detect Child Elements */

			if(getCurrentSegment().startsWith("N3"))
			{
				nm1.setN3(new N3(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("N4"))
			{
				nm1.setN4(new N4(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("DMG"))
			{
				nm1.setDMG(new DMG(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("REF"))
			{
				nm1.setREF(new REF(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("PER"))
			{
				nm1.setPER(new PER(getCurrentSegment()));
				getNextSegment(sc);
			}
		
			NM1List.add(nm1);
		}
		return NM1List;
	}
	
	public static List<HLLoop> processHL(Scanner sc)
	{	
		List<HL> HLList = new ArrayList<HL>();
		
		HLLoop HLLoop = null;
		List<HLLoop> HLLoopList = new ArrayList<HLLoop>();
		List<HL> childHLList = null;
		//NM1List.add(new NM1(getCurrentSegment()));
		while(sc.hasNext() && getCurrentSegment().startsWith("HL"))
		{
			boolean isParent=true;
			
			HL hl = new HL(getCurrentSegment());
			getNextSegment(sc);
			
			/*Detect Child Elements */
			if(getCurrentSegment().startsWith("PRV"))
			{
				isParent = true;
				HLLoop = new HLLoop();
				childHLList = new ArrayList<HL>();
				HLLoop.setChildHLList(childHLList);
				
				hl.setPRV(new PRV(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("SBR"))
			{
				isParent = false;
				hl.setSBRList(processSBR(sc));
				//SubElements.put("SBRList", processSBR(sc));
			}
			//PAT not used
			if(getCurrentSegment().startsWith("DTP"))
			{
				hl.setDTPList(processDTP(sc));
				//SubElements.put("DTPList", processDTP(sc));
			}
			//CUR not used 
			if(getCurrentSegment().startsWith("NM1"))
			{
				hl.setNM1List(processNM1(sc));
				//SubElements.put("NM1List", processNM1(sc));
				//getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("CLM"))
			{
				hl.setCLMList(processCLM(sc));
				//SubElements.put("CLMList", processCLM(sc));
				//getNextSegment(sc);
			}
			
			if(isParent)
			{
				HLLoop.setParentHL(hl);
				HLLoopList.add(HLLoop);
			}
			else
			{
				childHLList.add(hl);
			}
			
			HLList.add(hl);
			
		}		
		return HLLoopList;
	}
	
	private static List<CLM>  processCLM(Scanner sc) {
		
		List<CLM> CLMList = new ArrayList<CLM>();
		while(sc.hasNext() && getCurrentSegment().startsWith("CLM"))
		{
			
			//DTP,CL1,CN1,NTE,HI,NM1,SBR,LX
			CLM clm = new CLM(getCurrentSegment());
			getNextSegment(sc);
			//Map<String,Object> childMap = (Map<String,Object>) clm.get("SubElements");
			
			/*Detect Child Elements */
			if(getCurrentSegment().startsWith("DTP"))
			{
				clm.setDTPList(processDTP(sc));
				//childMap.put("DTPList", processDTP(sc));
			}
			if(getCurrentSegment().startsWith("CL1"))
			{
				clm.setCL1(new CL1(getCurrentSegment()));
				//childMap.put("CL1", new CL1(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("CN1"))
			{
				clm.setCN1(new CN1(getCurrentSegment()));
				//childMap.put("CN1", new CN1(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("NTE"))
			{
				clm.setNTE(new NTE(getCurrentSegment()));
				//childMap.put("NTE", new NTE(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("HI"))
			{
				clm.setHIList(processHI(sc));
				//childMap.put("HIList", processHI(sc));
			}
			
			if(getCurrentSegment().startsWith("NM1"))
			{
				clm.setNM1List(processNM1(sc));
				//childMap.put("NM1List",processNM1(sc));
			}
/*	SBR Segment not used for FOCUS 
 * 		if(getCurrentSegment().startsWith("SBR"))
			{
				childMap.put("SBR",processSBR(sc));
				getNextSegment(sc);
			}*/
			if(getCurrentSegment().startsWith("PRV")) //Looks like a deviation
			{
				clm.setPRV(new PRV(getCurrentSegment()));
				getNextSegment(sc);
				//clm.setNM1List(processNM1(sc));
				//childMap.put("NM1List",processNM1(sc));
			}
			
			
			if(getCurrentSegment().startsWith("LX"))
			{
				clm.setLXList(processLX(sc));
				//childMap.put("LXList",processLX(sc));
				//getNextSegment(sc);
			}
		
			CLMList.add(clm);
		}

		//System.out.println("Last elemeent Proceesed : "+ getCurrentSegment());
		return CLMList;
		
	}


	public static List<DTP> processDTP(Scanner sc)
	{	
		List<DTP> dtpList = new ArrayList<DTP>();
		while(getCurrentSegment().startsWith("DTP"))
		{
			DTP lx = new DTP(getCurrentSegment());
			getNextSegment(sc);
			dtpList.add(lx);
		}
		return dtpList;
	}	

	
	public static List<LX> processLX(Scanner sc)
	{	
		List<LX> lxList = new ArrayList<LX>();
		while(getCurrentSegment().startsWith("LX"))
		{
			LX lx = new LX(getCurrentSegment());
			getNextSegment(sc);
			
			//Map<String,Object> childMap = (Map<String,Object>) lx.get("SubElements");
			if(getCurrentSegment().startsWith("SV2"))
			{
				lx.setSV2(new SV2(getCurrentSegment()));
				//childMap.put("SV2", new SV2(getCurrentSegment()));
				getNextSegment(sc);
			}
			if(getCurrentSegment().startsWith("DTP"))
			{
				lx.setDTPList(processDTP(sc));
				//childMap.put("DTPList", processDTP(sc));
			}
			lxList.add(lx);
		}
		return lxList;
	}	
	
	public static List<SBR> processSBR(Scanner sc)
	{	
		List<SBR> sbrList = new ArrayList<SBR>();
		while(getCurrentSegment().startsWith("SBR"))
		{
			SBR SBR = new SBR(getCurrentSegment());
			getNextSegment(sc);
			
			//Map<String,Object> childMap = (Map<String,Object>) sbr.get("SubElements");
			if(getCurrentSegment().startsWith("NM1"))
			{
				SBR.setNM1List(processNM1(sc));
			}
			if(getCurrentSegment().startsWith("DMG"))
			{
				SBR.setDMG(new DMG(getCurrentSegment()));
				//childMap.put("DMG", new DMG(getCurrentSegment()));
				getNextSegment(sc);
			}
			sbrList.add(SBR);
		}
		
		return sbrList;
	}
	
	public static List<HI> processHI(Scanner sc)
	{	
		List<HI> HIList = new ArrayList<HI>();
		while(sc.hasNext() && getCurrentSegment().startsWith("HI"))
		{
			HI hi = new HI(getCurrentSegment());
			getNextSegment(sc);
			HIList.add(hi);
		}
		return HIList;
	}
	
	
	public static String getNextSegment(Scanner sc)
	{
		String nextSegment = sc.next();
		nextSegment = nextSegment.startsWith(System.lineSeparator()) ? nextSegment.substring(1) : nextSegment ;
		setSegment(nextSegment);
		return nextSegment;
	}
	public static String getCurrentSegment()
	{
		return getSegment();
	}
	public static String getSegmentTerminator() {
		return Reader.segmentTerminator;
	}

	public  static void setSegmentTerminator(String segmentTerminator) {
		Reader.segmentTerminator = segmentTerminator;
	}

	public  static String getElementTerminator() {
		return Reader.elementTerminator;
	}

	public static void setElementTerminator(String elementTerminator) {
		Reader.elementTerminator = elementTerminator;
	}

	public  static String getComponentTerminator() {
		return Reader.componentTerminator;
	}

	public static void setComponentTerminator(String componentTerminator) {
		Reader.componentTerminator = componentTerminator;
	}

}