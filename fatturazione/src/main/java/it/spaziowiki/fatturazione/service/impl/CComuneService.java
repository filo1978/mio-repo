package it.spaziowiki.fatturazione.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spaziowiki.fatturazione.entities.CComune;
import it.spaziowiki.fatturazione.entities.CComunePK;
import it.spaziowiki.fatturazione.form.CComuneForm;
import it.spaziowiki.fatturazione.form.factory.CComuneFormFactory;
import it.spaziowiki.fatturazione.repository.CComuneRepository;
import it.spaziowiki.fatturazione.service.ICComuneService;

@Service
@Transactional
public class CComuneService implements ICComuneService {

	@Autowired
	private CComuneFormFactory comuneFactory;
	
	@Autowired
	private CComuneRepository comuneRepository;
	
	@Override
	public CComuneForm findById(String codCom, String codProv) {
		CComunePK id=new CComunePK();
		id.setCodCom(codCom);
		id.setCodProv(codProv);
		CComune comune =comuneRepository.findById(id).get();;
		return comuneFactory.getForm(comune);
	}
	
	@Override
	public List<CComuneForm> getComuneByProvincia(String codProv){
		List<CComune>l=comuneRepository.findByIdCodProvOrderByDescrizioneAsc(codProv);
		return comuneFactory.getList(l);
	}

}
