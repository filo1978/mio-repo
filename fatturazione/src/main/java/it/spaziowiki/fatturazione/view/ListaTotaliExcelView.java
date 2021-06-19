package it.spaziowiki.fatturazione.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import it.spaziowiki.fatturazione.form.TotaliForm;

public class ListaTotaliExcelView extends AbstractExcelListView{

	@Override
	protected String getSheetName() {
		return "Lista totali";
	}

	@Override
	protected List<String> getHeaders() {
		return Arrays.asList("Tipo", "Cliente","Data", "Importo");
	}

	@Override
	protected void setExcelRows(Sheet excelSheet, CellStyle cellStyle, Map<String, Object> model) {
		@SuppressWarnings("unchecked")
		List<TotaliForm> list = (List<TotaliForm>) model.get("listaTotali");
		int record = 1;
		for (TotaliForm f : list) {
			Row excelRow = excelSheet.createRow(record++);
			getCell(0,excelRow,cellStyle).setCellValue(f.getTipo());
			excelSheet.autoSizeColumn(0);
			getCell(1,excelRow,cellStyle).setCellValue(f.getCliente());
			excelSheet.autoSizeColumn(1);
			getCell(2,excelRow,cellStyle).setCellValue(f.getData());
			excelSheet.autoSizeColumn(2);
			getCell(3,excelRow,cellStyle).setCellValue(f.getImporto().doubleValue());
			excelSheet.autoSizeColumn(3);
		}
	}

}
