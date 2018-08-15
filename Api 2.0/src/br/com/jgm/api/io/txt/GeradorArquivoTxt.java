package br.com.jgm.api.io.txt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import br.com.jgm.api.io.CampoLeiaute;
import br.com.jgm.api.io.registro.Registro;


public class GeradorArquivoTxt {

	private List<String> linhas_arquivo;
	private List<Registro> registros;
	
	public GeradorArquivoTxt(List<Registro> registros){
		this.linhas_arquivo = new ArrayList<>();
		this.registros = registros;
	}
	
	public void gerarArquivoTxt(String nomeArquivo) throws IOException{
		carregarLinhas();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomeArquivo+".txt")));
		for(String linha : linhas_arquivo){
			writer.write(linha);
			writer.newLine();
			writer.flush();
		}
		writer.close();
	}

	private void carregarLinhas() {
		for(Registro registro : registros){
			linhasRegistro(registro);
		}
	}

	private void linhasRegistro(Registro registro) {
		StringBuilder sb = new StringBuilder();
		int ultimoCampo = 0;
		for(CampoLeiaute campo : registro.getCamposRegistros()){

			while(ultimoCampo < campo.getIndexCampo() - 1){
				sb.append("|");
				ultimoCampo++;
			}
			
			String countedo = "";
			if(campo.getConteudoCampo() != null){
				countedo = campo.getConteudoCampo().replace(Pattern.quote("|"), "");
			}
			ultimoCampo = campo.getIndexCampo();
			sb.append(countedo+"|");
		}
		linhas_arquivo.add(sb.toString());
		if(registro.getRegistroFilho() != null){
			for(Registro regFilho : registro.getRegistroFilho()){
				linhasRegistro(regFilho);
			}
		}
	}

}
