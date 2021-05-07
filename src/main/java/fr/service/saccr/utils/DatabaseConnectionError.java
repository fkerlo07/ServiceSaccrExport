package fr.service.saccr.utils;

public class DatabaseConnectionError extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseConnectionError() {
		super("Connection to database in error",1020);
	}

}
