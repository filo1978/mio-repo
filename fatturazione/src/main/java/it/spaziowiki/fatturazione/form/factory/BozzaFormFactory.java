package it.spaziowiki.fatturazione.form.factory;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.Attivita;
import it.spaziowiki.fatturazione.entities.Fattura;
import it.spaziowiki.fatturazione.form.BozzaForm;

@Component
public class BozzaFormFactory extends AbstractFormFactory<Fattura, BozzaForm> {

	@Override
	public BozzaForm getForm(Fattura fattura) {
		BozzaForm bozzaForm = new BozzaForm();
		bozzaForm.setDenominazioneCliente(fattura.getCliente().getDenominazione());
		bozzaForm.setPivaCiente(fattura.getCliente().getPiva());
		bozzaForm.setDescrMese(fattura.getMese().getDescrizione());
		String attivitaStr="";
		BigDecimal importoTotale=new BigDecimal(0);
		for(Attivita attivita:fattura.getAttivitas()) {
			attivitaStr+=" "+attivita.getDescrizione();
			if(attivita.getImportoNetto()!=null)
				importoTotale=importoTotale.add(attivita.getImportoNetto());
		}
		bozzaForm.setAttivita(attivitaStr);
		bozzaForm.setImportoAttvita(importoTotale);
		return bozzaForm;
	}

}
