package it.spaziowiki.fatturazione.form.factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.Fattura;
import it.spaziowiki.fatturazione.form.TotaliForm;

@Component
public class TotaliFormFactory extends AbstractFormFactory<Fattura, TotaliForm> {

	@Override
	public TotaliForm getForm(Fattura fattura) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return new TotaliForm(fattura.getTipoFattura().getDescrizione(), fattura.getCliente().getDenominazione(), formatter.format(fattura.getDtFattura()), fattura.getImportoNetto(), fattura.getIdFattura());
	}

}
