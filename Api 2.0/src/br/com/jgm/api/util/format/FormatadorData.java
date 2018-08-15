package br.com.jgm.api.util.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorData {

	private static SimpleDateFormat spdfMascaraUsuario = null;
	
	private static Date toDate(Object obj) throws ParseException{
		
		if(obj instanceof String)
			
			if(spdfMascaraUsuario != null)
				return spdfMascaraUsuario.parse((String)obj);
			else
				return DateFormats.spdfPadrao.parse((String)obj);
		
		else if(obj instanceof Date)
			return (Date)obj;
		return null;
	}

	public static String StringDataPadrao(Object data){
		spdfMascaraUsuario = null;
		try {
			return DateFormats.spdfPadrao.format(toDate(data));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String StringDataPadrao(Object data , String mascaraData){
		spdfMascaraUsuario = new SimpleDateFormat(mascaraData);
		try {
			return spdfMascaraUsuario.format(toDate(data));
		} catch (ParseException e) {
			return null;
		}
	}
}
