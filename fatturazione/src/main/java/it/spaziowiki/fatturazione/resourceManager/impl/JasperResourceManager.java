package it.spaziowiki.fatturazione.resourceManager.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.utils.resourceManager.IResourceManager;

@Component
public class JasperResourceManager implements IResourceManager{
	
	@Autowired
	ServletContext context;

	@Override
	public String getResourcePath(){
		StringBuilder resourcePath = new StringBuilder(context.getRealPath("/"));
		resourcePath.append("WEB-INF").append(File.separator)
					.append("classes").append(File.separator)
					.append("report").append(File.separator);
		return resourcePath.toString();
	}

	@Override
	public String getResourcePath(String... resources) {
		StringBuilder result = new StringBuilder(getResourcePath());
		String separator ="";
		for(String resource : resources){
			result.append(separator).append(resource);
			separator = File.separator;
		}
		return result.toString();
	}
	
	@Override
	public String getResourcePath(String resource){
		StringBuilder resourcePath = new StringBuilder(getResourcePath());
		resourcePath.append(resource).append(File.separator);
		return resourcePath.toString();
	}
}
