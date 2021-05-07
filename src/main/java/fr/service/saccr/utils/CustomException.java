package fr.service.saccr.utils;

public  abstract class CustomException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer errorCode;
    private String errorMessage;
	public CustomException(String errorMessage, Integer errorCode) {
    	 super();
    	 this.errorCode=errorCode;
    	 this.errorMessage=errorMessage;   	 
     }   
	public Integer getErrorCode() {
		return this.errorCode;
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
        
}
