package it.spaziowiki.fatturazione.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaziowiki.fatturazione.entities.CMese;
import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.repository.CMeseRepository;
import it.spaziowiki.fatturazione.service.ICMeseService;

@Service
@Transactional
public class CMeseService implements ICMeseService {

	@Autowired
	private CMeseRepository meseRepository;
	
	@Override
	public List<PairDto> findAll() {
		 List<CMese> l=meseRepository.findAllByOrderByCodAsc();
		 List<PairDto> toRet= new ArrayList<PairDto>();
		 for(CMese mese:l) {
			 PairDto e= new PairDto(mese.getCod(), mese.getDescrizione());
			 toRet.add(e);
		 }
		return toRet;
	}

}
