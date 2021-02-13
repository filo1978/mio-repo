package it.spaziowiki.fatturazione.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public abstract class AbstractExcelListView extends AbstractXlsView {

	
	protected abstract String getSheetName();
	
	protected abstract List<String> getHeaders();
	
	protected abstract void setExcelRows(Sheet excelSheet, CellStyle cellStyle,Map<String, Object> model);
	
	
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
		Sheet excelSheet = workbook.createSheet(getSheetName());
		CellStyle headerCellStyle = getHeaderStyle(workbook);
		CellStyle normCoreCellStyle = getNormCoreStyle(workbook);
		setExcelHeader(excelSheet,headerCellStyle,getHeaders());
		setExcelRows(excelSheet, normCoreCellStyle,model);
	}
	
	protected Cell getCell(int numero,Row excelRow,CellStyle cellStyle) {
		Cell c =excelRow.createCell(numero);
		c.setCellStyle(cellStyle);
		return c;
	}

	
	protected void setExcelHeader(Sheet excelSheet ,CellStyle headerCellStyle,List<String> headers) {
		Row excelHeader = excelSheet.createRow(0);
		int i = 0;
		for (String header : headers) {
			getCell(i,excelHeader,headerCellStyle).setCellValue(header);
			excelSheet.autoSizeColumn(i); 
			i++;
		}
	}
	
	protected CellStyle getHeaderStyle(Workbook workbook) {
		Font font = getHeaderFont(workbook);

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

//		headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(font);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		return style;
	}
	
	private Font getHeaderFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setBold(true);
		return font;
	}
	
	private Font getNormCoreFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setBold(false);
		return font;
	}

	protected CellStyle getNormCoreStyle(Workbook workbook) {
		Font font = getNormCoreFont(workbook);

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.JUSTIFY);
		style.setFont(font);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
//		style.setFillForegroundColor(IndexedColors.RED.getIndex());
//		HSSFWorkbook hwb = new HSSFWorkbook();
//		HSSFPalette palette = hwb.getCustomPalette();
		// get the color which most closely matches the color you want to use
//		HSSFColor myColor = palette.findSimilarColor(255, 89, 89);
//		// get the palette index of that color 
//		short palIndex = myColor.getIndex();
//
//		style.setFillForegroundColor(palIndex);

//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

}
