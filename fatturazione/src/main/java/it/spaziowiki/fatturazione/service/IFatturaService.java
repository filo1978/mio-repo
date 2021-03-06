package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.exception.FatturaDeleteException;
import it.spaziowiki.fatturazione.exception.FatturaException;
import it.spaziowiki.fatturazione.form.BozzaForm;
import it.spaziowiki.fatturazione.form.ClienteFatturaAnnoForm;
import it.spaziowiki.fatturazione.form.FatturaAnnoFormWrapper;
import it.spaziowiki.fatturazione.form.FatturaForm;
import it.spaziowiki.fatturazione.form.ImportoMeseForm;
import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.form.TotaleFattureForm;

public interface IFatturaService {

	List<FatturaForm> getAllFattureCliente(Integer idCliente,String codTipoFattura);
	
	Integer insert(FatturaForm fatturaForm) throws FatturaException;
	
	void update(FatturaForm fatturaForm) throws FatturaException;
	
	void delete(FatturaForm fatturaForm) throws FatturaDeleteException;
	
	FatturaForm duplica(Integer idFattura) throws FatturaException;
	
	FatturaForm select(Integer idFattura);
	
	List<FatturaForm> getAllFatture();
	
	List<PairDto> getAllAnnoFatture();

	List<FatturaForm> getAllBozze();

	List<TotaleFattureForm> getTotaleFattoreProjection();

	List<ImportoMeseForm> getImportoMese(Integer anno);

	List<ClienteFatturaAnnoForm> getClienteFatturaAnno(Integer anno);

	FatturaAnnoFormWrapper getFatturaAnno();

	List<FatturaForm> getAllFattureBlack();

	List<BozzaForm> getAllBozzeEntity();
	
	List<BozzaForm> getAllBozzeCliente(Integer idCliente);
}
