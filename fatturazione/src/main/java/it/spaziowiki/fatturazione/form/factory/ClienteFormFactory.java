package it.spaziowiki.fatturazione.form.factory;

import org.springframework.stereotype.Component;

import it.spaziowiki.fatturazione.entities.Cliente;
import it.spaziowiki.fatturazione.form.ClienteForm;

@Component
public class ClienteFormFactory extends AbstractFormFactory<Cliente, ClienteForm> {

	@Override
	public ClienteForm getForm(Cliente cliente) {
		if(cliente==null)
			return null;
		ClienteForm clienteForm = new ClienteForm();
		clienteForm.setCap(cliente.getCap());
		clienteForm.setCodCom(cliente.getCComune().getId().getCodCom());
		clienteForm.setCodProv(cliente.getCComune().getId().getCodProv());
		clienteForm.setDenominazione(cliente.getDenominazione());
		clienteForm.setNickname(cliente.getNickname());
		clienteForm.setPiva(cliente.getPiva());
		clienteForm.setEmail(cliente.getEmail());
		clienteForm.setIdCliente(cliente.getIdCliente());
		clienteForm.setIndirizzo(cliente.getIndirizzo());
		clienteForm.setTelefono(cliente.getTelefono());
		clienteForm.setDecrComune(cliente.getCComune().getDescrizione());
		clienteForm.setSiglaProv(cliente.getCComune().getCProvincia().getSigla());
		return clienteForm;
	}

}
