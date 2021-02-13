package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.form.CProvinciaForm;

public interface ICProvinciaService {

	List<CProvinciaForm> findAllByOrderByDescrizioneAsc();
	
}
