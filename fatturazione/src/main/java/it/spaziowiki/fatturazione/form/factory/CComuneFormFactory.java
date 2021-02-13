package it.spaziowiki.fatturazione.form.factory;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.CComune;
import it.spaziowiki.fatturazione.form.CComuneForm;

@Component
public class CComuneFormFactory extends AbstractFormFactory<CComune, CComuneForm>{


	@Override
	public CComuneForm getForm(CComune comune) {
		if (comune == null)
			return null;
		CComuneForm comuneForm = new CComuneForm();
		comuneForm.setCodCom(comune.getId().getCodCom());
		comuneForm.setCodProv(comune.getId().getCodProv());
		comuneForm.setDescrizione(comune.getDescrizione());
		return comuneForm;
	}

	
	
}
