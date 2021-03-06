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

import it.spaziowiki.fatturazione.enums.StatoFatturaEnum;
import it.spaziowiki.fatturazione.enums.TipoFatturaEnum;
import it.spaziowiki.fatturazione.exception.FatturaDeleteException;
import it.spaziowiki.fatturazione.exception.FatturaException;
import it.spaziowiki.fatturazione.form.FatturaForm;
import it.spaziowiki.fatturazione.service.ICMeseService;
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
	private ICMeseService meseService;
	
	@Autowired
	private HttpSession session;
	
	private static BigDecimal IVA_DEFAULT=new BigDecimal(4);
	
	@RequestMapping(value = "/lista-fatture", method = RequestMethod.GET)
	public ModelAndView listaFatture() {
		return new ModelAndView("lista-fatture");
	}
	
	@RequestMapping(value = "/lista-prestazioni", method = RequestMethod.GET)
	public ModelAndView listaBlack() {
		return new ModelAndView("lista-black");
	}
	
	@RequestMapping(value = "/scarica-lista-fatture-excel", method = RequestMethod.GET)
	public ModelAndView scaricaListaFattureExcel() {
		List<FatturaForm> l= fatturaService.getAllFatture();
		return new ModelAndView("listaFattureExcelView", "listaFatture", l);
	}
	
	@RequestMapping(value = "/scarica-lista-prestazioni-excel", method = RequestMethod.GET)
	public ModelAndView scaricaListaBlackExcel() {
		List<FatturaForm> l= fatturaService.getAllFattureBlack();
		return new ModelAndView("listaFattureExcelView", "listaFatture", l);
	}
	
	@RequestMapping(value = "/lista-bozze", method = RequestMethod.GET)
	public ModelAndView listaPreventivi() {
		return new ModelAndView("lista-bozze");
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
		fatturaForm.setCodStato(StatoFatturaEnum.NON_PAGATA.getCod());
		fatturaForm.setCodTipo(tipoFattura);
		fatturaForm.setIva(getIva(tipoFattura));
		return getNuovaFatturaModel(fatturaForm);
	}
	
	private BigDecimal getIva(String tipoFattura) {
		if(tipoFattura.equals(TipoFatturaEnum.BLACK.getCod())) {
			return new BigDecimal(0);
		}else {
			return IVA_DEFAULT;
		}
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
			if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.BLACK.getCod())) {
				return new ModelAndView("lista-black");
			}else if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.BOZZA.getCod())) {
				return new ModelAndView("lista-bozze");
			}else{
				return new ModelAndView("lista-fatture");
			}
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
		m.addObject("isInsert", false);
		setValoriTipo(m,fatturaForm.getCodTipo());
		return m;
	}
	
	private void setValoriTipo(ModelAndView m,String codTipo) {
		if(codTipo.equals(TipoFatturaEnum.FATTURA.getCod())) {
			m.addObject("indietroButton", "Lista fatture");
			m.addObject("indietroLink", "/lista-fatture");
			m.addObject("titoloPagina", "Dettaglio fattura");
		}else if(codTipo.equals(TipoFatturaEnum.BLACK.getCod())) {
			m.addObject("indietroButton", "Lista prestazioni");
			m.addObject("indietroLink", "/lista-prestazioni");
			m.addObject("titoloPagina", "Dettaglio prestazione");
		}else if(codTipo.equals(TipoFatturaEnum.BOZZA.getCod())) {
			m.addObject("indietroButton", "Lista bozze");
			m.addObject("indietroLink", "/lista-bozze");
			m.addObject("titoloPagina", "Dettaglio bozza");
		}else {
			throw new RuntimeException("Errore: non definito il tipo fattura="+codTipo);	
		}
	}

	private ModelAndView getNuovaFatturaModel(FatturaForm fatturaForm){
		boolean isInsert=fatturaForm.getIdFattura()==null;
		ModelAndView m =  getFatturaModel();
		m.addObject("fattura", fatturaForm);
		String titoloPagina="";
		if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.FATTURA.getCod())){
			titoloPagina="Nuova fattura";
		}else if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.BLACK.getCod())){
			titoloPagina="Nuova prestazione";
		}else{
			titoloPagina="Nuova bozza";
		}
		m.addObject("titoloPagina", titoloPagina);
		m.addObject("isInsert", isInsert);
		setValoriTipo(m,fatturaForm.getCodTipo());
		return m;
	}
	
	private ModelAndView getFatturaModel(){
		ModelAndView m = new ModelAndView("dettaglio-fattura");
		m.addObject("listaTipoFattura", tipoFatturaService.findBozzaFatturaByOrderByDescrizioneAsc());
		m.addObject("listaClienti", clienteService.getAllClienti());
		m.addObject("listaStatiFattura", statoFatturaService.findAllByOrderByDescrizioneAsc());
		m.addObject("listaMesi", meseService.findAll());
		return m;
	}
	
	protected FatturaForm getFatturaFormFromSession() {
		return (FatturaForm) session.getAttribute("fattura");
	}
}
