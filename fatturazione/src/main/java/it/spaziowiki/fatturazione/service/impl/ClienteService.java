package it.spaziowiki.fatturazione.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaziowiki.fatturazione.entities.CComune;
import it.spaziowiki.fatturazione.entities.CComunePK;
import it.spaziowiki.fatturazione.entities.Cliente;
import it.spaziowiki.fatturazione.exception.ClienteDeleteExceltion;
import it.spaziowiki.fatturazione.exception.ClienteInsertException;
import it.spaziowiki.fatturazione.exception.ClienteUpdateException;
import it.spaziowiki.fatturazione.form.ClienteForm;
import it.spaziowiki.fatturazione.form.ClienteListBean;
import it.spaziowiki.fatturazione.form.factory.ClienteFormFactory;
import it.spaziowiki.fatturazione.repository.CComuneRepository;
import it.spaziowiki.fatturazione.repository.ClienteRepository;
import it.spaziowiki.fatturazione.service.IClienteService;

@Service
@Transactional
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteFormFactory clienteFormFactory;
	
	@Autowired
	private CComuneRepository comuneRepository;
	
	@Override
	public ClienteForm insert(ClienteForm clienteForm) throws ClienteInsertException{
		checkInsert(clienteForm);
		Cliente cliente = new Cliente();
		synchCliente(clienteForm,cliente);
		Integer idCliente=clienteRepository.getMaxIdCliente();
		if(idCliente==null)
			idCliente=0;
		idCliente++;
		cliente.setIdCliente(idCliente);
		clienteRepository.save(cliente);
		clienteForm.setIdCliente(cliente.getIdCliente());
		return clienteForm;
	}
	
	private void checkInsert(ClienteForm clienteForm)throws ClienteInsertException{
		List<Cliente> l=clienteRepository.findByPiva(clienteForm.getPiva());
		if(l!=null&&!l.isEmpty())
			throw new ClienteInsertException("Non è possibile inserire il dato perchè esiste già un altro cliente con la stessa p. iva");
	}
	
	private void checkUpdate(ClienteForm clienteForm)throws ClienteUpdateException{
		List<Cliente> l=clienteRepository.findByPiva(clienteForm.getPiva());
		if(l!=null&&!l.isEmpty()&&l.get(0).getIdCliente().compareTo(clienteForm.getIdCliente())!=0)
			throw new ClienteUpdateException("Non è possibile agiornare il dato perchè esiste già un altro cliente con la stessa p. iva");
	}

	@Override
	public ClienteForm update(ClienteForm clienteForm) throws ClienteUpdateException{
		checkUpdate(clienteForm);
		Cliente cliente =clienteRepository.findById(clienteForm.getIdCliente()).get();
		synchCliente(clienteForm,cliente);
		clienteRepository.save(cliente);
		return clienteForm;
	}
	
	private void synchCliente(ClienteForm clienteForm,Cliente cliente){
		cliente.setCap(clienteForm.getCap());
		CComunePK id = new CComunePK();
		id.setCodCom(clienteForm.getCodCom());
		id.setCodProv(clienteForm.getCodProv());
		CComune comune=comuneRepository.findById(id).get();
		cliente.setCComune(comune);
		cliente.setDenominazione(clienteForm.getDenominazione());
		cliente.setNickname(clienteForm.getNickname());
		cliente.setEmail(clienteForm.getEmail());
		cliente.setIndirizzo(clienteForm.getIndirizzo());
		cliente.setPiva(clienteForm.getPiva());
		cliente.setTelefono(clienteForm.getTelefono());
	}

	@Override
	public void delete(ClienteForm clienteForm) throws ClienteDeleteExceltion {
		Cliente cliente =clienteRepository.findById(clienteForm.getIdCliente()).get();
		if(cliente.getFatturas()!=null&&cliente.getFatturas().size()>0)
			throw new ClienteDeleteExceltion("non è possibile eliminare il cliente perchè sono presenti preventivi o fatture");
		clienteRepository.delete(cliente);
			
		
	}

	
	@Override
	public ClienteListBean getAllClienti(Integer paginaListaClienti){
		Pageable sortedByDenominazione = PageRequest.of(paginaListaClienti, 5, Sort.by("denominazione"));
		Page pagina= clienteRepository.findAll(sortedByDenominazione);
		long numeroTotaleElementi=pagina.getTotalElements();
		long numeroTotalePagina=pagina.getTotalPages();
		ClienteListBean clienteListBean = new ClienteListBean(numeroTotaleElementi, numeroTotalePagina, clienteFormFactory.getList(pagina.getContent()));
		return clienteListBean;
	}

	@Override
	public List<ClienteForm> getAllClienti() {
		return clienteFormFactory.getList(clienteRepository.findAllByOrderByDenominazioneAsc());
	}

	@Override
	public ClienteForm select(Integer idCliente) {
		Cliente cliente=clienteRepository.findById(idCliente).get();
		return clienteFormFactory.getForm(cliente);
	}


}
