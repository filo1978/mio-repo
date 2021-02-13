package it.spaziowiki.fatturazione.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.form.factory.TipoFatturaFormFactory;
import it.spaziowiki.fatturazione.repository.TipoFatturaRepository;
import it.spaziowiki.fatturazione.service.ITipoFatturaService;

@Service
@Transactional
public class TipoFatturaService implements ITipoFatturaService {

	@Autowired
	private TipoFatturaRepository tipoFatturaRepository;

	@Autowired
	private TipoFatturaFormFactory tipoFatturaFormFactory;

	@Override
	public List<PairDto> findAllByOrderByDescrizioneAsc() {
		return tipoFatturaFormFactory.getList(tipoFatturaRepository.findAllByOrderByDescrizioneAsc());
	}

}
