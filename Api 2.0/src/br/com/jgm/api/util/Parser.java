package br.com.jgm.api.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.jgm.api.util.format.DateFormats;

public class Parser {

	public static Double stringParaDouble(String valor){
		return Double.parseDouble(valor.replaceAll("\\.", "").replaceAll(",", "\\."));
	}
	
	public static Calendar stringToCalendar(String data){
		Date dateParsed;
		try {
			dateParsed = DateFormats.spdfPadrao.parse(data);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(dateParsed);
			return cal;
		} catch (Exception e) {
			System.out.println("Erro no parse de " + data + " messagem: " + e.getMessage());
			return null;
		}

	}
	
	public static String dateToString(Date data){
		return DateFormats.spdfPadrao.format(data);
	}
	
	public static String calendarToString(Calendar cal){
		return DateFormats.spdfPadrao.format(cal.getTime());
	}
	
}
