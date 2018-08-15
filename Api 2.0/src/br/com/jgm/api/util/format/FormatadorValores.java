package br.com.jgm.api.util.format;

public class FormatadorValores {

	public static String formatarDecimalPadrao(Double valor){
		try{
			return DecimalFormats.decimalFormatPadrao.format(valor);
		}catch(Exception e){
			return "0,00";
		}
	}
}
