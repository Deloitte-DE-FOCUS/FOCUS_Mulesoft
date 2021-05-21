package gov.delaware.util;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
public class MMISTransformer extends AbstractTransformer{

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		InputStream inputStream = (InputStream) src;
		return MMISTransformer.process(inputStream);
	}

	//static Map<String,String> pidMapTemp = new HashMap<String,String>();
	static Map<String,String> mciMapTemp = new HashMap<String,String>();
	static StringBuilder sbTemp = null;

	/*Function to obtain a Iterator of grouped records having same PID*/
	public static Iterator<String> process(InputStream in) 
	{
		Iterator<String> recordIterator = null; //Initialize  Iterator
		sbTemp = new StringBuilder(); //Initialize String builder

		List<String> fakeRecordList = new ArrayList<String>(); // Creating a fake record, for getting last Record Set properly	
		fakeRecordList.add("-99999999999999999999Fake Record"); // Adding a fake record, for getting last Record Set properly	

		Stream<String> fakeRecordStream = fakeRecordList.stream(); //Creating a fake record stream, for getting last Record Set properly
		Stream<String> stream = new BufferedReader(new InputStreamReader(in)).lines(); //Obtain Stream from InputStream
		Stream<String> finalStream = Stream.concat(stream,fakeRecordStream).sequential(); //Contain the actual stream with fake record streamObtain Stream from InputStream
		{   
			/*Obtain a Iterator using a lambda expression over the stream*/
			recordIterator =	finalStream.map(x -> {  

				if(x.length() >= 20 ) //Checking if the line length greater than 10, so that PID can retrieved without error
				{
					//String orignalPid = x.substring(0, 10); //Obtain PID with white-spaces
					String orignalMci = x.substring(10, 20); //Obtain MCI with white-spaces
					//String pid = orignalPid.trim(); ////Obtain trimmed PID
					String mci = orignalMci.trim(); ////Obtain trimmed MCI

					//Process when HashMap already contains the key or if mci is empty
					if(mciMapTemp.containsKey(mci) || mci.equals("") )
					{
						sbTemp.append("\n").append(x); //Append the Record along with line feed character
						return ""; //Return empty row to the output stream
					}
					else //Process when HashMap does not contains the key and mci is not empty
					{
						mciMapTemp.clear(); //Clear Hashmap
						mciMapTemp.put(mci, mci); //Put mci entry in Hashmap

						String s = sbTemp.toString(); //Obtain String from String Builder
						s = org.apache.commons.lang3.StringUtils.removeStart(s,"\n"); //Remove \n character from beginning (which may come due to the processing), if it exists 
						sbTemp = new StringBuilder(); //Obtain a new instance of StringBuilder
						sbTemp.append(x); //Append new record
						if(x.equals("-99999999999999999999Fake Record"))	{
							try {
								in.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if((!s.endsWith("\n")) && !s.equals(""))
						s = s + "\n";
						return s; //Return the aggregated grouped record
					}
				}
				else // Process When Line length is less than 10
				{
					sbTemp.append("\n").append(x); //Append record with a line feed character
					return ""; // Return empty string
				}
			}
					).filter(line -> ((line.length() > 0) ) ).iterator(); //Filtering for records having length greater than 0 so that we don't get the empty records and obtaining a Iterator

		}
		return recordIterator;
	}
	
/*	public Map processRecord(String record)
	{
		Map<String,String> map = new HashMap<String,String>();
		if(record.length() == 152)
			map.put("record", "record");
		String s = "";	
		return null;
	}*/
}
