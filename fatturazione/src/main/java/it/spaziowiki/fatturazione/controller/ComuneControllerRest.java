package it.spaziowiki.fatturazione.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.spaziowiki.fatturazione.form.CComuneForm;
import it.spaziowiki.fatturazione.service.ICComuneService;

@RestController
public class ComuneControllerRest {

	@Autowired
	private ICComuneService comuneService;;

	@RequestMapping(method = RequestMethod.GET, value = "/get-comuni-by-provincia")
	@ResponseBody
	public List<CComuneForm> getComuniByProvincia(@ModelAttribute("codProv") String codProv) {

		List<CComuneForm> l = comuneService.getComuneByProvincia(codProv);

		return l;
	}

}
