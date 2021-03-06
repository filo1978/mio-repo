package it.spaziowiki.fatturazione.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import it.spaziowiki.fatturazione.form.BozzaForm;

public class ListaBozzeExcelView extends AbstractExcelListView {

	@Override
	protected String getSheetName() {
		return "Lista bozze";	
	}

	@Override
	protected List<String> getHeaders() {
		return Arrays.asList("Cliente","P.IVA Cliente","Mese", "Attività","Importo");
	}

	@Override
	protected void setExcelRows(Sheet excelSheet, CellStyle cellStyle, Map<String, Object> model) {
		@SuppressWarnings("unchecked")
		List<BozzaForm> bozzeList = (List<BozzaForm>) model.get("listaBozze");
		int record = 1;
		for (BozzaForm bozza : bozzeList) {
			Row excelRow = excelSheet.createRow(record++);
			getCell(0,excelRow,cellStyle).setCellValue(bozza.getDenominazioneCliente());
			excelSheet.autoSizeColumn(0);
			getCell(1,excelRow,cellStyle).setCellValue(bozza.getPivaCiente());
			excelSheet.autoSizeColumn(1);
			getCell(2,excelRow,cellStyle).setCellValue(bozza.getDescrMese());
			excelSheet.autoSizeColumn(2);
			getCell(3,excelRow,cellStyle).setCellValue(bozza.getAttivita());
			excelSheet.autoSizeColumn(3);
			getCell(4,excelRow,cellStyle).setCellValue(bozza.getImportoAttvita().doubleValue());
			excelSheet.autoSizeColumn(4);
		}
	}

}
