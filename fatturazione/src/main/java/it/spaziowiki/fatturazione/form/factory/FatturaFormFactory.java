package it.spaziowiki.fatturazione.form.factory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.ibm.icu.util.Calendar;

import it.spaziowiki.fatturazione.entities.Cliente;
import it.spaziowiki.fatturazione.entities.Fattura;
import it.spaziowiki.fatturazione.form.FatturaForm;

@Component
public class FatturaFormFactory extends AbstractFormFactory<Fattura, FatturaForm> {

	@Override
	public FatturaForm getForm(Fattura fattura) {
		if(fattura==null)
			return null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Cliente cliente=fattura.getCliente();
		FatturaForm fatturaForm = new FatturaForm();
		if(fattura.getIdFattura()!=null)
			fatturaForm.setIdFattura(fattura.getIdFattura());
		fatturaForm.setIdCliente(cliente.getIdCliente());
		fatturaForm.setDescrTipo(fattura.getTipoFattura().getDescrizione());
		fatturaForm.setCodTipo(fattura.getTipoFattura().getCod());
		fatturaForm.setNumeroFattura(fattura.getNumeroFattura());
		fatturaForm.setIdBollo(fattura.getIdBollo());
		fatturaForm.setImportoNetto(fattura.getImportoNetto());
		fatturaForm.setIva(fattura.getIva());
		fatturaForm.setOggetto(fattura.getOggetto());
		if(fattura.getStatoFattura()!=null) {
			fatturaForm.setCodStato(fattura.getStatoFattura().getCod());
			fatturaForm.setDescrStato(fattura.getStatoFattura().getDescrizione());
		}
		fatturaForm.setImportoLordo(getImportoLordo(fattura));
		fatturaForm.setDenominazioneCliente(cliente.getDenominazione());
		fatturaForm.setPivaCiente(cliente.getPiva());
		if(fattura.getDtFattura()!=null) {
			fatturaForm.setDtFattura(formatter.format(fattura.getDtFattura()));
			fatturaForm.setAnno(formatter.getCalendar().get(Calendar.YEAR));
		}
		return fatturaForm;
	}

	private BigDecimal getImportoLordo(Fattura fattura) {
		BigDecimal importoNetto=fattura.getImportoNetto();
		BigDecimal iva=fattura.getIva();
		return importoNetto.add(importoNetto.multiply(iva.divide(new BigDecimal(100))));
	}

}
