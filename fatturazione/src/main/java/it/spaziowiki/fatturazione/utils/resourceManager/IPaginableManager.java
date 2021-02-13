package it.spaziowiki.fatturazione.utils.resourceManager;

import java.util.Collection;

import it.spaziowiki.fatturazione.form.AbstractForm;


public  interface IPaginableManager {
	
	final static int DEFAULT_ELEMENTS_FOR_PAGE = 15 ; 
	
	default public int getNumberForPage(){
		return DEFAULT_ELEMENTS_FOR_PAGE;
	}
	
	public int getMaxNumberOfPages(String idElement);
	
	public Collection<? extends AbstractForm> getPaginableRangeObjects(String idElement,int iDisplayStart, int iDisplayLength);
	
	public Collection<? extends AbstractForm> getPaginableObjects(String idElement);

}
