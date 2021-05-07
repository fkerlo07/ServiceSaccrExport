package fr.service.saccr.utils;

public class FTPConnectionError extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FTPConnectionError() {
   	 super("FTP Connection Exception",1050);	 
    }   
}