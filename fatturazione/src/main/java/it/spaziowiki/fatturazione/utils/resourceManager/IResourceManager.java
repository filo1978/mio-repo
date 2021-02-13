package it.spaziowiki.fatturazione.utils.resourceManager;

public interface IResourceManager {
	
public String getResourcePath();
	
	public String getResourcePath(String... resources);
	
	public String getResourcePath(String resource);

}
