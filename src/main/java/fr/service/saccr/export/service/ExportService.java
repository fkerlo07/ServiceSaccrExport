package fr.service.saccr.export.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import fr.service.saccr.export.repository.ExportRepositoryZDE;
import fr.service.saccr.export.repository.ExportRepositoryZDP;
import fr.service.saccr.utils.CustomException;
import fr.service.saccr.utils.FTPConnectionError;
import fr.service.saccr.utils.FTPLoginException;
import fr.service.saccr.utils.FTPUploadException;
import fr.service.saccr.utils.ResponseJSON;
import lombok.extern.slf4j.Slf4j;


@Slf4j

public abstract class ExportService {
		 
	private FTPClient ftp = null;
	
	public void FTPInit(String host, String user, String pwd) throws Exception{
		ftp = new FTPClient();
		//ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			log.error("Error during ftp conection");
			ftp.disconnect();
			throw new FTPConnectionError();
		}
		ftp.login(user, pwd);
		reply=ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			log.error("Error during ftp login");
			ftp.disconnect();
			throw new FTPLoginException();
		}
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}
	
	public void uploadFile(String localFileFullName, String fileName, String hostDir)
			throws Exception {
        File f=new File(localFileFullName);
		InputStream input = new FileInputStream(f);
		int reply;
		this.ftp.storeFile(hostDir + fileName, input);
		input.close();
		reply = ftp.getReplyCode();
		f.delete();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new FTPUploadException();
		}
	}

	public void FTPDisconnect(){
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				// do nothing as file is already saved to server
			}
		}
	}
	public ResponseJSON processZDE(String query, String filePath,String fileName, String[] params, ExportRepositoryZDE exportRepo, boolean activFtp, String server, String user, String pass, String DIR_TMP) throws Exception {
		try {
		log.info("Start");
		exportRepo.fastexport(query,filePath, fileName, params);
		log.info("fast export finished");
		if(activFtp)
		{
	    this.FTPInit(server, user, pass);
		log.info("ftp init done");
		this.uploadFile(DIR_TMP+"//"+fileName, fileName, filePath);
		log.info("ftp upload done");
		this.FTPDisconnect();
		}
		}
		catch(CustomException e) {
			return new ResponseJSON(fileName,filePath,"csv",e.getErrorCode().toString(),e.getErrorMessage());			
		}
		return new ResponseJSON(fileName,filePath,"csv","0","Export Successful");
	}
	
	public ResponseJSON processZDP(String query, String filePath,String fileName, String[] params, ExportRepositoryZDP exportRepo, boolean activFtp, String server, String user, String pass, String DIR_TMP) throws Exception {
		try {
		log.info("Start");
		exportRepo.fastexport(query,filePath, fileName, params);
		log.info("fast export finished");
		if(activFtp)
		{
	    this.FTPInit(server, user, pass);
		log.info("ftp init done");
		this.uploadFile(DIR_TMP+"//"+fileName, fileName, filePath);
		log.info("ftp upload done");
		this.FTPDisconnect();
		}
		}
		catch(CustomException e) {
			return new ResponseJSON(fileName,filePath,"csv",e.getErrorCode().toString(),e.getErrorMessage());			
		}
		return new ResponseJSON(fileName,filePath,"csv","0","Export Successful");
	}
}
