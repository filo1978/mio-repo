package it.spaziowiki.fatturazione.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.spaziowiki.fatturazione.exception.ClienteDeleteExceltion;
import it.spaziowiki.fatturazione.exception.ClienteInsertException;
import it.spaziowiki.fatturazione.exception.ClienteUpdateException;
import it.spaziowiki.fatturazione.form.CComuneForm;
import it.spaziowiki.fatturazione.form.ClienteForm;
import it.spaziowiki.fatturazione.service.ICComuneService;
import it.spaziowiki.fatturazione.service.ICProvinciaService;
import it.spaziowiki.fatturazione.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController extends SmartAbstractController{

	
	@Autowired
	private ICProvinciaService provinciaService;
	
	@Autowired 
	private ICComuneService comuneService;
	
	@Autowired
	private IClienteService clienteService;
	
	
	@RequestMapping(value = "/lista-clienti", method = RequestMethod.GET)
	public ModelAndView getListaClienti(){
		return new ModelAndView("lista-clienti");
	}
	
	
	@RequestMapping(value = "/nuovo-cliente", method = RequestMethod.GET)
	public ModelAndView nuovoCliente() {
		return getNuovoClienteModel(new ClienteForm());
	}
	
	private ModelAndView getNuovoClienteModel(ClienteForm clienteForm){
		ModelAndView m = new ModelAndView("dettaglio-cliente");
		String titoloPagina="Nuovo cliente";
		if(clienteForm.getIdCliente()!=null)
			titoloPagina="Dettaglio cliente";
		m.addObject("cliente", clienteForm);
		m.addObject("listaProvincie", provinciaService.findAllByOrderByDescrizioneAsc());
		if(clienteForm.getCodProv()==null||clienteForm.getCodProv().isEmpty()){
			m.addObject("listaComuni", new ArrayList<CComuneForm>());
		}else{
			m.addObject("listaComuni", comuneService.getComuneByProvincia(clienteForm.getCodProv()));
		}
		m.addObject("titoloPagina", titoloPagina);
		return m;
	}
	
	@RequestMapping(value = "/dettaglio-cliente", method = RequestMethod.POST)
	public ModelAndView dettaglioCliente(@RequestParam(value = "idCliente" , required = true) String idCliente) {
		ModelAndView model = getDettaglioClienteModel(idCliente);
		return model;
	}
	
	


	private ModelAndView getDettaglioClienteModel(String idCliente) {
		ModelAndView model = new ModelAndView("dettaglio-cliente");
		ClienteForm clienteForm=clienteService.select(new Integer(idCliente));
		model.addObject("cliente",clienteForm);
		model.addObject("listaProvincie", provinciaService.findAllByOrderByDescrizioneAsc());
		model.addObject("listaComuni", comuneService.getComuneByProvincia(clienteForm.getCodProv()));
		model.addObject("titoloPagina", "Dettaglio cliente");
		return model;
	}
	
	@RequestMapping(value = "/add-cliente", method = RequestMethod.POST)
	public ModelAndView addCliente(@ModelAttribute("cliente") @Valid ClienteForm cliente,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			ModelAndView m=getNuovoClienteModel(cliente);
			m.addAllObjects(bindingResult.getModel());
			return m;
		}
		try{
			String msgOk="Inserimento avvenuto con successo";
			if(cliente.getIdCliente()==null){
				cliente = clienteService.insert(cliente);
			}else{
				cliente = clienteService.update(cliente);
				msgOk="Aggiornamento avvenuto con successo";
			}
			ModelAndView m= getDettaglioClienteModel(cliente.getIdCliente().toString());
			m.addObject(SUCCESS, msgOk);
			return m;
		}catch(ClienteUpdateException e){
			ModelAndView m= getDettaglioClienteModel(cliente.getIdCliente().toString());
			m.addObject(ERROR, e.getMessage());
			return m;
		}catch(ClienteInsertException e){
			ModelAndView m=getNuovoClienteModel(cliente);
			m.addObject(ERROR, e.getMessage());
			return m;
		}
	}
	
	@RequestMapping(value = "/delete-cliente", method = RequestMethod.POST)
	public ModelAndView deleteCliente(ClienteForm cliente) {
		try {
			clienteService.delete(cliente);
			ModelAndView m=getListaClienti();
			m.addObject(SUCCESS, "Eliminazione avvenuta con successo");
			return m;
		} catch (ClienteDeleteExceltion e) {
			ModelAndView m= getDettaglioClienteModel(cliente.getIdCliente().toString());
			m.addObject(ERROR, e.getMessage());
			return m;
		}
	}
	
	
}
