package fr.service.saccr.utils;

public class TmpFileNotFoundException extends CustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TmpFileNotFoundException() {
   	 super("Temporary file not found",1070);	
	}

}
