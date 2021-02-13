package it.spaziowiki.fatturazione.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaziowiki.fatturazione.form.CProvinciaForm;
import it.spaziowiki.fatturazione.form.factory.CProvinciaFormFactory;
import it.spaziowiki.fatturazione.repository.CProvinciaRepository;
import it.spaziowiki.fatturazione.service.ICProvinciaService;

@Service
@Transactional
public class CProvinciaService implements ICProvinciaService{

	@Autowired
	private CProvinciaRepository prinviciaRepository;
		
	@Autowired
	private CProvinciaFormFactory provinciaFormFactory;
	
	@Override
	public List<CProvinciaForm> findAllByOrderByDescrizioneAsc() {
		return provinciaFormFactory.getList(prinviciaRepository.findAllByOrderByDescrizioneAsc());
		
	}

}
