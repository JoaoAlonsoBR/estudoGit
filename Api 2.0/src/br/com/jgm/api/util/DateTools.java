package br.com.jgm.api.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.jgm.api.util.format.DateFormats;

public class DateTools {
	
	public static void main(String[] args) {
		System.out.println(DateTools.retornaUltimoDiaMes("01/01/2018"));
	}

	public static Calendar retornaUltimoDiaMes(String data_lanc) {

		try {
			Date parse = DateFormats.spdfPadrao.parse(data_lanc);
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(parse);
		 	data.add(Calendar.MONTH, 1);
		 	data.add(Calendar.DAY_OF_MONTH, -1);
		 	return data;
		}catch (ParseException e) {
			
		}
		return null;
	}
}
