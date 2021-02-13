package it.spaziowiki.fatturazione.form.factory;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.CProvincia;
import it.spaziowiki.fatturazione.form.CProvinciaForm;

@Component
public class CProvinciaFormFactory extends AbstractFormFactory<CProvincia, CProvinciaForm> {

	@Override
	public CProvinciaForm getForm(CProvincia element) {
		if(element==null)
			return null;
		CProvinciaForm provinciaForm = new CProvinciaForm();
		provinciaForm.setCodProv(element.getCodProv());
		provinciaForm.setDescrizione(element.getDescrizione());
		provinciaForm.setSigla(element.getSigla());
		return provinciaForm;
	}

}
