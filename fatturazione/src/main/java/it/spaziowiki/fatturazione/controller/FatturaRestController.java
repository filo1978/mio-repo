package it.spaziowiki.fatturazione.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.spaziowiki.fatturazione.enums.TipoFatturaEnum;
import it.spaziowiki.fatturazione.form.ClienteFatturaAnnoForm;
import it.spaziowiki.fatturazione.form.ClienteForm;
import it.spaziowiki.fatturazione.form.FatturaAnnoFormWrapper;
import it.spaziowiki.fatturazione.form.FatturaForm;
import it.spaziowiki.fatturazione.form.ImportoMeseAnnoForm;
import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.form.TotaleFattureForm;
import it.spaziowiki.fatturazione.service.IFatturaService;

@RestController
public class FatturaRestController {
	
	@Autowired
	protected HttpSession session;
	
	@Autowired
	private IFatturaService fatturaService;
	
	
	@RequestMapping(value = "/grafico-fatture-anno", method = RequestMethod.GET)
	public FatturaAnnoFormWrapper getGraficoFattureAnno(){
		return fatturaService.getFatturaAnno();
	}
	
	@RequestMapping(value = "/grafico-fatture-mese-anno", method = RequestMethod.GET)
	public List<ImportoMeseAnnoForm> getGraficoFattureMeseAnno(){
		
		List<ImportoMeseAnnoForm> l = new ArrayList<ImportoMeseAnnoForm>();
		List<PairDto> listaAnni=fatturaService.getAllAnnoFatture();
		for(PairDto anno:listaAnni) {
			
			ImportoMeseAnnoForm importoMeseAnno=new ImportoMeseAnnoForm();
			importoMeseAnno.setAnno(Integer.valueOf(anno.getCod()));
			importoMeseAnno.setLista(fatturaService.getImportoMese(Integer.valueOf(anno.getCod())));
			l.add(importoMeseAnno);
			
			
		}
		
//		Calendar c = Calendar.getInstance();
//		Integer annoAttuale=c.get(Calendar.YEAR);
//		ImportoMeseAnnoForm importoMeseAnnoAttuale=new ImportoMeseAnnoForm();
//		importoMeseAnnoAttuale.setAnno(annoAttuale);
//		importoMeseAnnoAttuale.setLista(fatturaService.getImportoMese(annoAttuale));
//		Integer annoPrecedente=annoAttuale-1;
//		ImportoMeseAnnoForm importoMeseAnnoPrecedente=new ImportoMeseAnnoForm();
//		importoMeseAnnoPrecedente.setAnno(annoPrecedente);
//		importoMeseAnnoPrecedente.setLista(fatturaService.getImportoMese(annoPrecedente));
//		l.add(importoMeseAnnoPrecedente);
//		l.add(importoMeseAnnoAttuale);
		return l;
	}
	
	@RequestMapping(value = "/grafico-fatture-cliente-anno", method = RequestMethod.GET)
	public List<ClienteFatturaAnnoForm> getGraficoClienteFatturaAnno(@RequestParam(value = "anno", required = true) String anno){
		return fatturaService.getClienteFatturaAnno(Integer.valueOf(anno));
	}
	
	
	@RequestMapping(value = "/lista-all-fatture-cliente", method = RequestMethod.GET)
	public List<FatturaForm> getAllFattureCliente(){
		return fatturaService.getAllFattureCliente(getClienteFromFromSession().getIdCliente(),TipoFatturaEnum.FATTURA.getCod());
	}
	
	@RequestMapping(value = "/lista-all-bozze-cliente", method = RequestMethod.GET)
	public List<FatturaForm> getAllBozzeCliente(){
		return fatturaService.getAllFattureCliente(getClienteFromFromSession().getIdCliente(),TipoFatturaEnum.BOZZA.getCod());
	}
	
	@RequestMapping(value = "/lista-all-prestazioni-cliente", method = RequestMethod.GET)
	public List<FatturaForm> getAllPrestazioniCliente(){
		return fatturaService.getAllFattureCliente(getClienteFromFromSession().getIdCliente(),TipoFatturaEnum.BLACK.getCod());
	}
	
	@RequestMapping(value = "/lista-all-fatture", method = RequestMethod.GET)
	public List<FatturaForm> getAllFatture(){
		return fatturaService.getAllFatture();
	}
	
	@RequestMapping(value = "/lista-all-fatture-black", method = RequestMethod.GET)
	public List<FatturaForm> getAllFattureBlack(){
		return fatturaService.getAllFattureBlack();
	}
	
	@RequestMapping(value = "/lista-all-bozze", method = RequestMethod.GET)
	public List<FatturaForm> getAllBozze(){
		return fatturaService.getAllBozze();
	}
	
	private ClienteForm getClienteFromFromSession(){
		return  (ClienteForm)session.getAttribute("cliente");
	}

	@RequestMapping(value = "/riassunto-fatture", method = RequestMethod.GET)
	public List<TotaleFattureForm> getTotaleFattoreProjection(){
		return fatturaService.getTotaleFattoreProjection();
	}
}
