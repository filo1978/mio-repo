package it.spaziowiki.fatturazione.form.factory;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.Attivita;
import it.spaziowiki.fatturazione.form.AttivitaForm;

@Component
public class AttivitaFormFactory extends AbstractFormFactory<Attivita, AttivitaForm> {

	@Override
	public AttivitaForm getForm(Attivita attivita) {
		if(attivita==null)
			return null;
		AttivitaForm attivitaForm= new AttivitaForm();
		attivitaForm.setDescrizione(attivita.getDescrizione());
		attivitaForm.setImportoNettoAttivita(attivita.getImportoNetto());
		attivitaForm.setIdAttivita(attivita.getIdAttivita());
		attivitaForm.setIdFattura(attivita.getFattura().getIdFattura());
		return attivitaForm;
	}

}
