package fr.service.saccr.export.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.service.saccr.export.repository.ExportRepositoryZDE;
import fr.service.saccr.utils.ResponseJSON;


@Service

public class ExportServiceZDE extends ExportService {
	@Autowired
	ExportRepositoryZDE exportRepo;
	
     @Value("${config.ftp.server.host}")
	 private String server;
	 @Value("${config.ftp.server.port}")
	 private String port;
	 @Value("${config.ftp.server.username}")
	 private String user;
	 @Value("${config.ftp.server.password}")
	 private String pass;
	 @Value("${spring.directory.tmp.file}")
	 private String DIR_TMP;
	 @Value("${ftp.activate}")
	 private boolean activFtp;
	 
		public ResponseJSON processZDE(String query, String filePath,String fileName, String[] params) throws Exception {
			return super.processZDE(query, filePath, fileName, params, this.exportRepo, this.activFtp, this.server, this.user, this.pass, this.DIR_TMP);
		}	


}
