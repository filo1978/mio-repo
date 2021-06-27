package it.spaziowiki.fatturazione.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import it.spaziowiki.fatturazione.form.FatturaForm;


public class ListaFattureExcelView extends AbstractExcelListView {

	
	@Override
	protected String getSheetName() {
		return "Lista fatture";
	}

	@Override
	protected List<String> getHeaders() {
		return Arrays.asList("Anno", "Nr. fattura","Data fattura", "Cliente","P.IVA Cliente",
				"Importo lordo", "IVA", "Bollo", "Stato","Data pagamento","Dati pagamento");
	}

	@Override
	protected void setExcelRows(Sheet excelSheet, CellStyle cellStyle,Map<String, Object> model) {
		@SuppressWarnings("unchecked")
		List<FatturaForm> fattureList = (List<FatturaForm>) model.get("listaFatture");
		int record = 1;
		for (FatturaForm f : fattureList) {
			Row excelRow = excelSheet.createRow(record++);
			getCell(0,excelRow,cellStyle).setCellValue(f.getAnno());
			excelSheet.autoSizeColumn(0);
			getCell(1,excelRow,cellStyle).setCellValue(f.getNumeroFattura());
			excelSheet.autoSizeColumn(1);
			getCell(2,excelRow,cellStyle).setCellValue(f.getDtFattura());
			excelSheet.autoSizeColumn(2);
			getCell(3,excelRow,cellStyle).setCellValue(f.getDenominazioneCliente());
			excelSheet.autoSizeColumn(3);
			getCell(4,excelRow,cellStyle).setCellValue(f.getPivaCiente());
			excelSheet.autoSizeColumn(4);
			getCell(5,excelRow,cellStyle).setCellValue(f.getImportoLordo().doubleValue());
			excelSheet.autoSizeColumn(5);
			getCell(6,excelRow,cellStyle).setCellValue(f.getIva().doubleValue());
			excelSheet.autoSizeColumn(6);
			getCell(7,excelRow,cellStyle).setCellValue(f.getIdBollo());
			excelSheet.autoSizeColumn(7);
			getCell(8,excelRow,cellStyle).setCellValue(f.getDescrStato());
			excelSheet.autoSizeColumn(8);
			getCell(9,excelRow,cellStyle).setCellValue(f.getDataPagamento());
			excelSheet.autoSizeColumn(9);
			getCell(10,excelRow,cellStyle).setCellValue(f.getDatiPagamento());
			excelSheet.autoSizeColumn(10);
		}
	}

	
}
