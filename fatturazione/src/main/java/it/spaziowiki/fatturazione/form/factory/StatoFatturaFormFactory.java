package it.spaziowiki.fatturazione.form.factory;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.StatoFattura;
import it.spaziowiki.fatturazione.form.PairDto;

@Component
public class StatoFatturaFormFactory extends AbstractFormFactory<StatoFattura, PairDto> {

	@Override
	public PairDto getForm(StatoFattura statoFattura) {
		if(statoFattura==null)
			return null;
		PairDto pairDto = new PairDto(statoFattura.getCod(),statoFattura.getDescrizione());
		return pairDto;
	}

}
