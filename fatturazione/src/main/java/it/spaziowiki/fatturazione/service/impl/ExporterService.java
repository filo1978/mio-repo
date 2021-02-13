package it.spaziowiki.fatturazione.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaziowiki.fatturazione.service.IExporterService;
import it.spaziowiki.fatturazione.utils.resourceManager.IResourceManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
@Transactional
public class ExporterService implements IExporterService {

	@Autowired
	IResourceManager resourceManager;

	@Autowired
	private DataSource dataSource; // Don't need to create the DataSource bean!

	@Autowired
	private ResourceLoader resourceLoader;

	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String MASTER_REPORT_FILE_NAME = "module.jasper";

	@Override
	public JasperPrint exportPdfFile(String idFattura, String tipoFattura) throws Exception {
		Connection conn = dataSource.getConnection();

		String pathMasterReport = resourceLoader
				.getResource("classpath:report/" + tipoFattura + "/" + MASTER_REPORT_FILE_NAME).getURI().getPath();
		String pathSubreportDir = resourceLoader.getResource("classpath:report/" + tipoFattura).getURI().getPath()
				+ "/";

		// JasperReport jasperReport = JasperCompileManager.compileReport(path);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(pathMasterReport);

		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(SUBREPORT_DIR, pathSubreportDir);
		parameters.put("ID_FATTURA", idFattura);

		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

		return print;
	}

}
