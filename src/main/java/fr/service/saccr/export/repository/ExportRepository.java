package fr.service.saccr.export.repository;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import fr.service.saccr.utils.FastExportException;
import fr.service.saccr.utils.TmpFileNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Slf4j

public abstract class ExportRepository {
	

	public void fastexport(String query, String filePath,String fileName, String[] params, String DIR_TMP, JdbcTemplate jdbcTemplate) throws Exception {     
	    String csvFilePath = DIR_TMP+"//"+fileName;
	    log.info("\n Sample TestFastExport: \n");
	    log.info(" Looking for the Teradata JDBC driver... ");
	    try  {
	    	Connection connection = (Connection) jdbcTemplate.getDataSource().getConnection();
	    	log.info(" connection successful. \n");
	       // String sql = "exec GITRISQ_SACCR.GET_CHS_SACCR_RESULTS('2021-03-31','7','7','7','7','7','*' )";
           // String queryBand ="SET QUERY_BAND='APPLICATION=SACCR;MODULE=Export;' FOR TRANSACTION;";
            //Statement statement = connection.createStatement();
            //statement.executeUpdate(queryBand);
            //connection.close();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i=0;i<params.length;i++)
            {
            	 preparedStatement.setObject(i+1, params[i]);
            }
	      //  statement.executeUpdate(queryBand);
	        ResultSet result = preparedStatement.executeQuery();
	        log.info(" query executed. \n" + result.getMetaData());
	        ResultSetMetaData metaData=result.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
	        //BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(csvFilePath))));
		    BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath)));
	        String header="";
	        for (int i = 1; i <= numberOfColumns; i++) {
	            String columnName = metaData.getColumnName(i);
	            header=header+columnName +";";
	        }    
	        // write header line containing column names       
	        fileWriter.write(header);         
	        while (result.next()) {
	        	Object valueObject;
	        	String line="";
	            for (int i = 1; i <= numberOfColumns; i++) {
	                valueObject = result.getObject(i);
	                line=line +valueObject+";";
	            } 
	            fileWriter.newLine();
	            fileWriter.write(line);            
	        }	         
	        preparedStatement.close();
	        fileWriter.close();	         
	    } catch (SQLException e) {
	        log.error("SQL error during export:"+e);
	        throw new FastExportException();
	    }
         catch (FileNotFoundException e) {
            log.error("temporary file not found connection error:"+e);
            throw new TmpFileNotFoundException();
        }
	}   
}