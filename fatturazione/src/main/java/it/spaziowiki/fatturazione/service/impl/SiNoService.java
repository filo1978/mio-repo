package it.spaziowiki.fatturazione.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.spaziowiki.fatturazione.enums.FlagSiNoEnum;
import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.service.ISiNoService;

@Service
public class SiNoService implements ISiNoService {

	@Override
	public List<PairDto> getSiNoList() {
		List<PairDto> l = new ArrayList<PairDto>();
		l.add(new PairDto(FlagSiNoEnum.SI.getCod(),FlagSiNoEnum.SI.getDescrizione()));
		l.add(new PairDto(FlagSiNoEnum.NO.getCod(),FlagSiNoEnum.NO.getDescrizione()));
		return l;
	}

}
