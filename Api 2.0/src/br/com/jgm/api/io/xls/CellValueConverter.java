package br.com.jgm.api.io.xls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;


public class CellValueConverter {
	


	public static String toString (Cell cell) throws IllegalStateException{
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();
	}

	public static Calendar toCalendar(Cell cell) throws IllegalStateException{
		try{
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			Calendar calendar = HSSFDateUtil.getJavaCalendar(cell.getNumericCellValue(), false);
			return calendar;
		}catch(Exception e){
			SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
			try	{
				Date time = spdf.parse(cell.getStringCellValue());
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(time);
				return calendar;
			}catch(Exception e1){
				String valorCampo = cell.getStringCellValue();
				return HSSFDateUtil.getJavaCalendar(Double.parseDouble(valorCampo), false);
			}
		}
	}

	public static Double toDouble(Cell cell)throws IllegalStateException {
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		return cell.getNumericCellValue();
	}


}
