package it.spaziowiki.fatturazione.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import it.spaziowiki.fatturazione.form.AbstractForm;
import it.spaziowiki.fatturazione.form.IstanzeDataTablesDTO;
import it.spaziowiki.fatturazione.utils.resourceManager.IPaginableManager;

public abstract class SmartAbstractController {

	protected static final String ERROR = "msgKO";
	protected static final String SUCCESS = "msgOK";
	protected static final String WARNING = "wrn";

	@Autowired
	protected HttpSession session;

	protected IstanzeDataTablesDTO<? extends AbstractForm> getDataTable(String idForPaginableManager,
			IPaginableManager paginableManager, IstanzeDataTablesDTO<? extends AbstractForm> dataTable,
			int iDisplayStart, int iDisplayLength, int sEcho) {

		Collection<? extends AbstractForm> accts = paginableManager.getPaginableObjects(idForPaginableManager);
		int numeroRecordTotali = accts.size();
		dataTable.setAaData(
				paginableManager.getPaginableRangeObjects(idForPaginableManager, iDisplayStart, iDisplayLength));
		dataTable.setiTotalDisplayRecords(numeroRecordTotali);
		dataTable.setiTotalRecords(numeroRecordTotali);
		dataTable.setsEcho(sEcho);
		return dataTable;
	}
}
