package it.spaziowiki.fatturazione.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import it.spaziowiki.fatturazione.enums.TipoFatturaEnum;
import it.spaziowiki.fatturazione.exception.FatturaDeleteException;
import it.spaziowiki.fatturazione.exception.FatturaException;
import it.spaziowiki.fatturazione.form.FatturaForm;
import it.spaziowiki.fatturazione.service.IClienteService;
import it.spaziowiki.fatturazione.service.IExporterService;
import it.spaziowiki.fatturazione.service.IFatturaService;
import it.spaziowiki.fatturazione.service.IStatoFatturaService;
import it.spaziowiki.fatturazione.service.ITipoFatturaService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@SessionAttributes("fattura")
public class FatturaController extends SmartAbstractController{

	private Logger logger = LoggerFactory.getLogger(FatturaController.class);
	
	@Autowired
	private ITipoFatturaService tipoFatturaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IFatturaService fatturaService;
	
	@Autowired
	private IExporterService exporterService;
	
	@Autowired
	private IStatoFatturaService statoFatturaService;
	
	@Autowired
	private HttpSession session;
	
	private static BigDecimal IVA_DEFAULT=new BigDecimal(4);
	
	@RequestMapping(value = "/lista-fatture", method = RequestMethod.GET)
	public ModelAndView listaFatture() {
		return new ModelAndView("lista-fatture");
	}
	
	@RequestMapping(value = "/scarica-lista-fatture-excel", method = RequestMethod.GET)
	public ModelAndView scaricaListaFattureExcel() {
		List<FatturaForm> l= fatturaService.getAllFatture();
		return new ModelAndView("listaFattureExcelView", "listaFatture", l);
	}
	
	
	@RequestMapping(value = "/lista-preventivi", method = RequestMethod.GET)
	public ModelAndView listaPreventivi() {
		return new ModelAndView("lista-preventivi");
	}
	
	
	@RequestMapping(value = "/stampa-fattura", method = RequestMethod.GET)
	public void stampa(HttpServletResponse response) throws IOException {

		JasperPrint jasperPrint = null;

		try {
			
			  response.setContentType("application/x-download");
			  response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

			  OutputStream out = response.getOutputStream();

			  jasperPrint =exporterService.exportPdfFile(getFatturaFormFromSession().getIdFattura().toString(), getFatturaFormFromSession().getCodTipo());
			  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		
		} catch (Exception e) {
			logger.error("Errore stampa::",e);
		}
	}
	
	@RequestMapping(value = "/nuova-fattura", method = RequestMethod.GET)
	public ModelAndView nuovaFattura(@RequestParam(value = "tipoFattura" , required = true) String tipoFattura) {
		FatturaForm fatturaForm=new FatturaForm();
		fatturaForm.setCodTipo(tipoFattura);
		fatturaForm.setIva(IVA_DEFAULT);
		return getNuovaFatturaModel(fatturaForm);
	}
	
	@RequestMapping(value = "/duplica-fattura", method = RequestMethod.GET)
	public ModelAndView duplicaFattura() {
		Integer idFattura=getFatturaFormFromSession().getIdFattura();
		try {
			FatturaForm fatturaForm = fatturaService.duplica(idFattura);
			ModelAndView m=getDettaglioFatturaModel(fatturaForm.getIdFattura());
			m.addObject(SUCCESS, "Duplicazione avvenuta correttamente");
			return m;
		} catch (FatturaException e) {
			ModelAndView m=getDettaglioFatturaModel(new Integer(idFattura));
			m.addObject(ERROR, e.getMessage());
			return m;
		}
	}
	
	@RequestMapping(value = "/dettaglio-fattura", method = RequestMethod.POST)
	public ModelAndView dettaglio(@RequestParam(value = "idFattura" , required = true) String idFattura) {
		return getDettaglioFatturaModel(new Integer(idFattura));
	}
	
	@RequestMapping(value = "/delete-fattura", method = RequestMethod.POST)
	public ModelAndView delete(FatturaForm fatturaForm) {
		try {
			fatturaService.delete(fatturaForm);
			String page="lista-fatture";
			if(!fatturaForm.isTipoFattura())
				page="lista-preventivi";
			return new ModelAndView(page);
		} catch (FatturaDeleteException e) {
			ModelAndView m=getNuovaFatturaModel(fatturaForm);
			m.addObject(ERROR, e.getMessage());
			return m;
		}
	}
	
	@RequestMapping(value = "/add-fattura", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("fattura") @Valid FatturaForm fatturaForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		Integer idFattura=fatturaForm.getIdFattura();
		String msgSuccess="";
		boolean isInsert=(idFattura==null);
		
		if (bindingResult.hasErrors()) {
			ModelAndView m=getNuovaFatturaModel(fatturaForm);
			m.addAllObjects(bindingResult.getModel());
			return m;
		}
		
		try {
			if(isInsert){
				msgSuccess="Inserimento avvenuto correttamente";
				idFattura=fatturaService.insert(fatturaForm);
			}else{
				msgSuccess="Aggiornamento avvenuto correttamente";
				fatturaService.update(fatturaForm);
			}
			ModelAndView m=getDettaglioFatturaModel(idFattura);
			m.addObject(SUCCESS, msgSuccess);
			return m;
		} catch (FatturaException e) {
			if(isInsert){
				ModelAndView m=getNuovaFatturaModel(fatturaForm);
				m.addObject(ERROR, e.getMessage());
				return m;
			}else{
				ModelAndView m=getDettaglioFatturaModel(idFattura);
				m.addObject(ERROR, e.getMessage());
				return m;
			}
		}
	}
	
	private ModelAndView getDettaglioFatturaModel(Integer idFattura){
		ModelAndView m =  getFatturaModel();
		FatturaForm fatturaForm=fatturaService.select(idFattura);
		m.addObject("fattura", fatturaForm);
		m.addObject("titoloPagina", getTitoloDettaglioPagina(fatturaForm));
		m.addObject("isInsert", false);
		return m;
	}
	
	private ModelAndView getNuovaFatturaModel(FatturaForm fatturaForm){
		boolean isInsert=fatturaForm.getIdFattura()==null;
		ModelAndView m =  getFatturaModel();
		m.addObject("fattura", fatturaForm);
		String titoloPagina="";
		if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.FATTURA.getCod())){
			titoloPagina="Nuova fattura";
		}else{
			titoloPagina="Nuovo preventivo";
		}
		m.addObject("titoloPagina", titoloPagina);
		m.addObject("isInsert", isInsert);
		return m;
	}
	
	private String getTitoloDettaglioPagina(FatturaForm fatturaForm){
		String titoloPagina="";
		if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.FATTURA.getCod())){
			titoloPagina="Dettaglio fattura";
		}else{
			titoloPagina="Dettaglio preventivo";
		}
		return titoloPagina;
	}
	
	private ModelAndView getFatturaModel(){
		ModelAndView m = new ModelAndView("dettaglio-fattura");
		m.addObject("listaTipoFattura", tipoFatturaService.findAllByOrderByDescrizioneAsc());
		m.addObject("listaClienti", clienteService.getAllClienti());
		m.addObject("listaStatiFattura", statoFatturaService.findAllByOrderByDescrizioneAsc());
		return m;
	}
	
	protected FatturaForm getFatturaFormFromSession() {
		return (FatturaForm) session.getAttribute("fattura");
	}
}
