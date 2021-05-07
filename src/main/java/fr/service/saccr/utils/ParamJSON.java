package fr.service.saccr.utils;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParamJSON {
	    @NotNull
	    private String query;
	    @NotNull(message="Parameter filePath is NULL")
	    private String filePath;
	    @NotNull
	    private String fileName;
	    @NotNull
	    private String[] params;
        
	    @JsonProperty(value="query")
		public String getQuery() {
			return query;
		}
	    @JsonProperty(value="query")
		public void setQuery(String query) {
			this.query = query;
		}
	    @JsonProperty(value="filePath")
		public String getFilePath() {
			return filePath;
		}
	    @JsonProperty(value="filePath")
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	    @JsonProperty(value="fileName")
		public String getFileName() {
			return fileName;
		}
	    @JsonProperty(value="fileName")
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	    @JsonProperty(value="params")
		public String[] getParams() {
			return params;
		}
	    @JsonProperty(value="params")
		public void setParams(String[] params) {
			this.params = params;
		}
}
