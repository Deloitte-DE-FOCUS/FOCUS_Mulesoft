package gov.delaware.requester;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.mule.api.MuleMessage;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.api.transport.PropertyScope;
import org.mule.transport.sftp.SftpClient;
import org.mule.transport.sftp.SftpConnector;
//import org.mule.transport.sftp.SftpStream;
import org.mule.transport.sftp.SftpInputStream;
import org.mule.transport.sftp.SftpMessageRequester;
import org.mule.transport.sftp.SftpReceiverRequesterUtil;
import org.mule.transport.sftp.notification.SftpNotifier;



public class CustomSftpMessageRequester extends SftpMessageRequester {

	private SftpReceiverRequesterUtil sftpRRUtil = null;
	public CustomSftpMessageRequester(InboundEndpoint endpoint) {
		super(endpoint);
		
		sftpRRUtil = new SftpReceiverRequesterUtil(endpoint);
	}
	
	@Override
	   protected MuleMessage doRequest(long timeout) throws Exception
    {
		        
		/* Modification begins  */
        long fileSize = -1; // Initializing variable to get File Size
        SftpClient client = null ; // Initializing SFTP Client to be used to getting file size
        MuleMessage message = null; // Initializing MuleMessage to be returned by this function
        String filename = null; // Initializing variable to which will store filename
        String query  = endpoint.getEndpointURI().getQuery(); // Getting Query part of the endpoint for e.g. connector=SFTP&myfilename=abcd.txt

        String[] queryParams = query.split("&"); // Splitting Query parameters with '&' to get array of all parameters
        boolean isContainsCheckEnabled = false;
        String containsFlag = null;
        
        
        for (String paramEntry : queryParams) {
        	if(paramEntry.startsWith("isContainsCheckEnabled")) // Checking a param with name starting with 'myfilename'
        	{
        		containsFlag = paramEntry.split("=").length ==2 ? ( paramEntry.split("=")[1].trim().equals("") ? null : paramEntry.split("=")[1] ) : null;
        		if(containsFlag.equals("true"))
        			isContainsCheckEnabled = true;
        	}
        }
        /* Looping over all entries of the array*/
        for (String paramEntry : queryParams) {
        	if(paramEntry.startsWith("myfilename")) // Checking a param with name starting with 'myfilename'
        	{
        		// Extract from the Query param entry, String after the first Index of '=', store in filename variable
        		// If the part after '='  doesn't exist set filename as null 
        		filename = paramEntry.split("=").length ==2 ? ( paramEntry.split("=")[1].trim().equals("") ? null : paramEntry.split("=")[1] ) : null;
        		//System.out.println("Before Decode :" + filename);
        		//filename = URLDecoder.decode(filename, "UTF-8");
        		//System.out.println("After Decode :" + filename);
      		         		
        	}
        }
        
        /* Checking if filename variable is null, i.e. when Filename was not provided to the Requester in the URI*/
        if(filename==null)
        {
        	logger.error("No Filename provided to the Requester ");
        	return null;
        }
		
        /* Get all available files within the folder :
         *  getAvailableFiles(false) Gives all files in the specified SFTP path, getAvailableFiles(true) gives only one random file
         *  */
			String[] fileNamesList = sftpRRUtil.getAvailableFiles(false); //Get Array of names of all files in the SFTP Path
			/* Checking if there are any file names retrieved , returns null if none retrieved*/
			if (fileNamesList.length == 0) 
			{
				logger.warn("No file present in the SFTP Location");
				return null;
			}

			String path = null; // Initializing path varaible with null
			
			/* Looping over the list of File names and checking each item with the filename  */
			for (String name: fileNamesList) {
				if(isContainsCheckEnabled)
				{
					if(name.contains(filename))
					{	
						path = name; //Assigning the filename to path variable
						break; //Exiting out of the for loop
					}					
				}
				else
				{
					if(name.equals(filename))
					{	
						path = name; //Assigning the filename to path variable
						break; //Exiting out of the for loop
					}					
				}

			} // End of For Loop
			
			/* Checking if the filename was retrieved from the for loop i.e when the required file is present in the SFTP location
			 *  return null if it wasn't retrieved i.e. when file was not present in the SFTP location
			 *  */
			if(path==null)
			{
				logger.warn("No file present in the SFTP Location with name : " + filename);
				return null;
			} 
			
	/* Modification ends */		
			
	/* Modification starts : Start of try block 
	 * Try - Finally block is used to envelope the entire code to safely disconnect the SFTP Client used to retrieve file size
	 * */
			try {
	/* Modification ends */			
		    SftpNotifier notifier = new SftpNotifier((SftpConnector) connector, createNullMuleMessage(),
		                endpoint, endpoint.getName());
		    
		    /* Modified code to retrieve Size of the File */
			client = ((SftpConnector) connector).createSftpClient(endpoint, notifier);
			fileSize = client.getSize(path);
			/* Modification ends*/
			
			/* Modification starts
			 * Creating instance of org.mule.transport.sftp.SftpInputStream  
			 *  org.mule.transport.sftp.SftpInputStream is being returned by SftpReceiverRequesterUtil.retrieveFile()
			 *  , and is an instance of org.mule.transport.sftp.SftpInputStream 
			 *   Any instance of org.mule.transport.sftp.SftpStream is necessary for performing post processing actions
			 *   The orignal code used org.mule.transport.sftp.SftpStream but it is not visible to this Java Class so using this instead
			 *  */
			SftpInputStream sftpStream = (SftpInputStream) sftpRRUtil.retrieveFile(path, notifier);
			/* Modification Ends*/
	           if (sftpStream != null)
	            {
	                sftpStream.performPostProcessingOnClose(true);
	            }

	            logger.debug("Routing file: " + path);

	            message = createMuleMessage(sftpStream);
	            message.setProperty(SftpConnector.PROPERTY_ORIGINAL_FILENAME, path, PropertyScope.INBOUND);
	            
	            /* Modification for Setting File Size as inbound property in the Mule Message*/
	            message.setProperty("fileSize", fileSize, PropertyScope.INBOUND);
	            /* Modification ends */
	            
	            // Now we can update the notifier with the message
	            notifier.setMessage(message);
	            return message;
	            
	    /* Modification begins 
	     * Try - Finally block is used to envelope the entire code to safely disconnect the SFTP Client used to retrieve file size
	    *  */
		} finally
        {
			
            if (client != null)
            {
                ((SftpConnector) connector).releaseClient(endpoint, client);
            }
        }
		/* Modification ends */
    }
	
/*	public static void main(String[] args) {
		String filename = "qwe&w=qe.txt";
		String encoded = "";
		String decoded = "";
		try {
			encoded = java.net.URLEncoder.encode(filename,"UTF-8");
			decoded = URLDecoder.decode(encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(encoded);
		System.out.println(decoded);
	}*/
	
}