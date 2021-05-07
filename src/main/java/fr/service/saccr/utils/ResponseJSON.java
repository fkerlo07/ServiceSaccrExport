package fr.service.saccr.utils;

public class ResponseJSON {
		
		private String fileName;
		private String fileType;
		private String filePath;
		private String errorCode;
		private String errorType;
		
		public ResponseJSON(String namefile, String filePath, String fileType, String errorCode, String errorType) {
			this.setFileName(namefile);
			this.setFilePath(filePath);
			this.setFileType(fileType);
			this.setErrorCode(errorCode);
			this.setErrorType(errorType);
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrorType() {
			return errorType;
		}
		public void setErrorType(String errorType) {
			this.errorType = errorType;
		}
}


