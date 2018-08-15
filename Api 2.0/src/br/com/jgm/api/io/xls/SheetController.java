package br.com.jgm.api.io.xls;

import java.util.Calendar;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SheetController {
	
	
	@SuppressWarnings("unused")
	private HSSFSheet sheet;
	private Iterator<Row> rows;
	private Row rowAtual;
	
	public SheetController(HSSFSheet sheet){
		this.sheet = sheet;
		rows = sheet.rowIterator();
	}
	
	public boolean nextRow(){
		boolean flag = rows.hasNext();
		if(flag)
			setRowAtual(rows.next());
		return flag;
	}

	public String retornaStringDeCelula(int coluna){
		try{
			return CellValueConverter.toString(getRowAtual().getCell(coluna));
		}catch(Exception e){
			return "";
		}
		
	}
	
	public Calendar retornaCalendarDeCelula(int coluna){
		return CellValueConverter.toCalendar(getRowAtual().getCell(coluna));
	}

	public Double retornaDoubleDeCelula(int coluna) {
		return CellValueConverter.toDouble(getRowAtual().getCell(coluna));
	}

	public Row getRowAtual() {
		return rowAtual;
	}

	public void setRowAtual(Row rowAtual) {
		this.rowAtual = rowAtual;
	}

	public Double trataCampoAliquota(int coluna) {
		Cell cell = getRowAtual().getCell(coluna);
		String decimalString = CellValueConverter.toString(cell);
		Double aliquota = Double.parseDouble(decimalString.replaceAll("%", ""));
		if(aliquota < 0.99d)
			aliquota = aliquota * 100;
		return aliquota;
	}
	

}
