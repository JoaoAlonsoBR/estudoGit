package br.com.jgm.api.util;

import java.text.Normalizer;

public class StringUtils {

	public static String removeAcentos(String string){
        string = Normalizer.normalize(string, Normalizer.Form.NFD);  
        string = string.replaceAll("[^\\p{ASCII}]", "");  
        return string;
	}
}
