package br.com.jgm.api.util;

public class CnpjUtils {
	
	private static int [] valores_primeiro_digito = new int[]{5,4,3,2,9,8,7,6,5,4,3,2};
	private static int [] valores_segundo_digito = new int[] {6,5,4,3,2,9,8,7,6,5,4,3,2};

	
	public static boolean cnpj_valido(String cnpj){
		if(cnpj.length() < 14)
			return false;
		int[] digitos_cnpj = new int[13];
		
		for(int i = 0; i < 12 ; i++){
			digitos_cnpj[i] = Integer.parseInt(cnpj.substring(i,i+1));
		}
		int primeiro_digito = retornaCauculoDigito(digitos_cnpj,valores_primeiro_digito);
		digitos_cnpj[12] = primeiro_digito;
		int segundo_digito = retornaCauculoDigito(digitos_cnpj,valores_segundo_digito);

		String digito_validacao = Integer.toString(primeiro_digito) + Integer.toString(segundo_digito);

		return digito_validacao.equals(cnpj.substring(12,14));
	}

	private static int retornaCauculoDigito(int[] digitos_cnpj, int[] valores_base) {
		int total = 0;
		for (int i = 0; i < valores_base.length; i++) {
			total += (digitos_cnpj[i] * valores_base[i]);
		}
		int mod = total%11;

		if(mod < 2)
			return 0;
		else
			return 11-mod;
	}
	

}
