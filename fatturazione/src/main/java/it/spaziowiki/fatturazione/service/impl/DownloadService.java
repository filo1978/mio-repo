package it.spaziowiki.fatturazione.service.impl;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

 
@Service
public class DownloadService {
 
	public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF = "application/pdf";
	public static final String EXTENSION_TYPE_EXCEL = "xls";
	public static final String EXTENSION_TYPE_PDF = "pdf";
//	static Logger logger = Logger.getLogger(DownloadService.class);
 
	
	public static final int BUFFER_SIZE = 16 * 1024;
	public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	
	   
	public HttpEntity<byte[]> scriviFile(byte[] buffer,String nameForInputStream){
		
		try{
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    header.set("Content-Disposition",  "attachment; filename=" + nameForInputStream);
	    header.setContentLength(buffer.length);

	    return new HttpEntity<byte[]>(buffer, header);
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
			
	
	}
	
    
//    public void downloadFromInputStream(HttpServletResponse response, InputStream inputStream, String nameForInputStream, int lengthInputStream) throws IOException {
//    	response.setContentType(MIMETYPE_OCTET_STREAM);
//        response.setContentLength(lengthInputStream);        
//        response.setHeader(CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", nameForInputStream));
//        
//    	OutputStream outputStream = null;
//    	 
//        try {        	       
//            outputStream = response.getOutputStream();
//            
//	        byte[] buffer = new byte[BUFFER_SIZE];
//	        int bytesRead = -1;
//
//	        while ((bytesRead = inputStream.read(buffer)) != -1) {
//	        	outputStream.write(buffer, 0, bytesRead);
//	        }
//        } catch (IOException ioex) {
//        	logger.error("Impossibile portare a termine il download di " + nameForInputStream, ioex);
//        	
//        	throw ioex;
//        } finally {
//        	try {
//	        	if (inputStream != null) {
//					inputStream.close();
//	        	}
//	        	if (outputStream != null) {
//	        		outputStream.flush();
//	        		outputStream.close();
//	        	}
//        	} catch (IOException ioex) {
//        		logger.warn("Impossibile chiudere la risorsa " + nameForInputStream, ioex);
//        	}
//        }
//    }
    
// metodi private
    
    private String getWebAppPath(HttpServletRequest request, String filePath) {    	
        ServletContext context = request.getSession().getServletContext();
        String appPath = context.getRealPath("");
        
//        if (logger.isDebugEnabled()) { 
//        	logger.debug("appPath = " + appPath);
//        }	
 
        return appPath + filePath;
    }
    
    private String getMimeType(HttpServletRequest request, String fullPath) {    	
        String mimeType = request.getSession().getServletContext().getMimeType(fullPath);
        
        if (mimeType == null) {            
            mimeType = MIMETYPE_OCTET_STREAM;
        }
        
//        if (logger.isDebugEnabled()) {
//        	logger.debug("MIME type: " + mimeType);
//        }
        
        return mimeType;
    }
	
}
