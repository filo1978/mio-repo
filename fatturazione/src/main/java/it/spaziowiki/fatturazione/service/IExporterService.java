package it.spaziowiki.fatturazione.service;

import net.sf.jasperreports.engine.JasperPrint;

public interface IExporterService {
	
	JasperPrint exportPdfFile(String idFattura, String tipoFattura) throws Exception;
}
