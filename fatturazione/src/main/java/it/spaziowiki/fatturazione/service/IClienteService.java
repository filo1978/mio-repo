package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.exception.ClienteDeleteExceltion;
import it.spaziowiki.fatturazione.exception.ClienteInsertException;
import it.spaziowiki.fatturazione.exception.ClienteUpdateException;
import it.spaziowiki.fatturazione.form.ClienteForm;
import it.spaziowiki.fatturazione.form.ClienteListBean;

public interface IClienteService{

	ClienteForm insert(ClienteForm clienteForm) throws ClienteInsertException;

	ClienteForm update(ClienteForm clienteForm) throws ClienteUpdateException;
	
	ClienteForm select(Integer idCliente);
	
	void delete(ClienteForm clienteForm) throws ClienteDeleteExceltion;

	ClienteListBean getAllClienti(Integer paginaListaClienti);
	
	List<ClienteForm> getAllClienti();

}
