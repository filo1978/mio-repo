package it.spaziowiki.fatturazione.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.spaziowiki.fatturazione.form.TotaliForm;
import it.spaziowiki.fatturazione.service.IFatturaService;

@Controller
public class TotaliController {

	@Autowired
	private IFatturaService fatturaService;
	
	@RequestMapping("/totali")
	public String indexTotali() {
		return "totali";
	}
	
	@RequestMapping(value = "/lista-totali", method = RequestMethod.GET)
	public @ResponseBody List<TotaliForm>  getTotaleFattoreProjection(){
		return fatturaService.findAllPagate();
	}
	
	@RequestMapping(value = "/scarica-totali-excel", method = RequestMethod.GET)
	public ModelAndView scaricaListaBlackExcel() {
		List<TotaliForm> l= fatturaService.findAllPagate();
		return new ModelAndView("listaTotaliExcelView", "listaTotali", l);
	}
	
}
