package fr.service.saccr.export.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository

public class ExportRepositoryZDP extends ExportRepository{
	
    @Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
    
    @Value("${spring.directory.tmp.file}")
	private String DIR_TMP;
	 
	public void fastexport(String query, String filePath,String fileName, String[] params) throws Exception {     
	    super.fastexport(query,filePath,fileName,params,DIR_TMP,this.jdbcTemplate);
	}   
}


