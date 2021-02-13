package it.spaziowiki.fatturazione.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.service.IFatturaService;

@RestController
public class AnnoFatturaRestController {

	@Autowired
	private IFatturaService fatturaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-all-anno-fatture")
	@ResponseBody
	public List<PairDto> getAllAnnoFatture() {
		
		return fatturaService.getAllAnnoFatture();
	}
	
}
