package gov.delaware.util;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transport.sftp.SftpClient;


public class SFTPController extends AbstractMessageTransformer{



//public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
public Boolean removeFile() {
	logger.info("Entered");
	Boolean result=false;
	SFTPController controller = new SFTPController();
	String sftp_host = System.getProperty("sftp.tplclient.transaction.host");
	sftp_host = sftp_host.replaceAll("\\s+","");
	String userName = System.getProperty("sftp.tplclient.transaction.username");
	userName = userName.replaceAll("\\s+","");
	String password = System.getProperty("sftp.tplclient.transaction.password");
	password = password.replaceAll("\\s+","");
	password = password.replaceAll("%40+","@");
	password = password.replaceAll("%21+","!");
	password = password.replaceAll("%23+","#");
	password = password.replaceAll("%24+","$");
	password = password.replaceAll("%25+","%");
	password = password.replaceAll("%26+","&");
	password = password.replaceAll("%3F+","?");
	password = password.replaceAll("%3D+","=");
	password = password.replaceAll("%3A+",":");
	password = password.replaceAll("%3B+",";");
	password = password.replaceAll("%2A+","*");	
	SftpClient client = new SftpClient(sftp_host);
	try{
		client.login(userName,password);
		logger.info("SFTP Client connected correctly.");
		logger.info(System.getProperty("sftp.tplclient.delete.filename"));
		result = controller.deleteFile(System.getProperty("sftp.tplclient.delete.filename"),client);
		logger.info("SFTP File Removed: ."+result);
	}
	catch(Exception er){
		logger.info("Error occurred: "+er);
	}
	return result;
}


/***
* @param path
* @param client
* @return
*/

	public Boolean deleteFile(String fileName,SftpClient client){	
		Boolean deleted;
		try{	
			client.deleteFile(fileName);
			deleted = true;
		}
		catch(Exception er){
			logger.info("Error occurred: "+er.getMessage());
			deleted = false;
		}
		return deleted;
	}
	@Override
	public Object transformMessage(MuleMessage arg0, String arg1) throws TransformerException {
		// TODO Auto-generated method stub
		return null;
	}
}