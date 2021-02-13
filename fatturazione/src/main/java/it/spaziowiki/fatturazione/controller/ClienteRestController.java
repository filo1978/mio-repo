package it.spaziowiki.fatturazione.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.spaziowiki.fatturazione.form.ClienteForm;
import it.spaziowiki.fatturazione.service.IClienteService;

@RestController
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value = "/lista-all-clienti", method = RequestMethod.GET)
	public List<ClienteForm> getListaClienti(){
		return clienteService.getAllClienti();
	}
}
