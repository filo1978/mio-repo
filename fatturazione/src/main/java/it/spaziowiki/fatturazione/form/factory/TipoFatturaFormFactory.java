package it.spaziowiki.fatturazione.form.factory;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.TipoFattura;
import it.spaziowiki.fatturazione.form.PairDto;

@Component
public class TipoFatturaFormFactory extends AbstractFormFactory<TipoFattura, PairDto> {

	@Override
	public PairDto getForm(TipoFattura tipoFattura) {
		if(tipoFattura==null)
			return null;
		PairDto pairDto = new PairDto(tipoFattura.getCod(),tipoFattura.getDescrizione());
		return pairDto;
	}

}
