package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.form.CComuneForm;

public interface ICComuneService {

	CComuneForm findById(String codCom,String codProv);

	List<CComuneForm> getComuneByProvincia(String codProv);
	
}
