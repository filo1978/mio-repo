package it.spaziowiki.fatturazione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.spaziowiki.fatturazione.form.CComuneForm;
import it.spaziowiki.fatturazione.service.ICComuneService;

@Controller
public class ProvaController {

	@Autowired
	private ICComuneService comuneService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(ModelMap model) {
		CComuneForm comuneForm= comuneService.findById("008", "038");
		ModelAndView m = new ModelAndView("login");
		m.addObject("comune", comuneForm);
		return m;
	}

	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public ModelAndView blank() {
		ModelAndView m = new ModelAndView("blank");
		return m;
	}
}
