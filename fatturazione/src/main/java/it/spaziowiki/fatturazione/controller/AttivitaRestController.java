package it.spaziowiki.fatturazione.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.spaziowiki.fatturazione.exception.AttivitaSaveException;
import it.spaziowiki.fatturazione.form.AttivitaForm;
import it.spaziowiki.fatturazione.form.FatturaForm;
import it.spaziowiki.fatturazione.form.TotaleAttivitaForm;
import it.spaziowiki.fatturazione.service.IAttivitaService;

@RestController
public class AttivitaRestController {

	@Autowired
	private IAttivitaService attivitaService;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/lista-attivita", method = RequestMethod.GET)
	public List<AttivitaForm> getLista() {
		return attivitaService.findAttivitaByFattura(getFatturaFormFromSession().getIdFattura());
	}
	 
	
	
	@RequestMapping(value = "/get-totale-attivita", method = RequestMethod.GET)
	@ResponseBody
	public TotaleAttivitaForm getTotaleAttivita() {
		TotaleAttivitaForm totaleAttivitaForm = new TotaleAttivitaForm();
		List<AttivitaForm> l =attivitaService.findAttivitaByFattura(getFatturaFormFromSession().getIdFattura());
		BigDecimal somma = new BigDecimal(0);
		for(AttivitaForm a:l) {
			 if(a.getImportoNettoAttivita()!=null) {
				 totaleAttivitaForm.setToCheck(true);
				 somma=somma.add(a.getImportoNettoAttivita());
			 }
		}
		totaleAttivitaForm.setTotaleAttivita(somma);
		return totaleAttivitaForm;
	}
	
	@RequestMapping(value = "/empty-attivita", method = RequestMethod.GET)
	@ResponseBody
	public AttivitaForm getEmpty() {
		return attivitaService.getEmptyAttivita(getFatturaFormFromSession().getIdFattura());
	}
	
	
	@RequestMapping(value = "/salva-attivita", method = RequestMethod.POST)
	@ResponseBody
	public AttivitaForm insertOrUpdate(@RequestBody AttivitaForm attivitaForm, BindingResult bindingResult) {
		attivitaForm.setIdFattura(getFatturaFormFromSession().getIdFattura());
		try {
			if(attivitaForm.getIdAttivita().intValue()==-1){
				attivitaForm=attivitaService.insert(attivitaForm);
			}else{
				attivitaForm=attivitaService.update(attivitaForm);
			}
			attivitaForm.setMsgOk("Aggiornamento eseguito correttamente");
			return attivitaForm;
		} catch (AttivitaSaveException e) {
			attivitaForm.addError(e.getMessage());
			return attivitaForm;
		}
	}
	
	@RequestMapping(value = "/elimina-attivita", method = RequestMethod.POST)
	@ResponseBody
	public AttivitaForm delete(@RequestBody AttivitaForm attivitaForm) {
		attivitaService.delete(attivitaForm);
		return new AttivitaForm();
	}
	
	@RequestMapping(value = "/dettaglio-attivita", method = RequestMethod.GET)
	@ResponseBody
	public AttivitaForm dettaglio(@RequestParam(value = "idAttivita", required = true) String idAttivita) {
		return attivitaService.getDettaglioAttivita(new Integer(idAttivita));
	}
	

	protected FatturaForm getFatturaFormFromSession() {
		return (FatturaForm) session.getAttribute("fattura");
	}

}
