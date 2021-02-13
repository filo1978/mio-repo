package it.spaziowiki.fatturazione.form.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFormFactory<R, F> {

	public abstract F getForm(R element);

	public List<F> getList(List<R> listaRecord) {
		List<F> listToRet = new ArrayList<>();
		if (listaRecord == null)
			return listToRet;

		for (R element : listaRecord)
			listToRet.add(getForm(element));

		return listToRet;
	}

}
