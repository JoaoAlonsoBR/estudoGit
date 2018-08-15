package br.com.jgm.api.io.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class ConversorLayoutPdf {
	
	private boolean linhaValida;
	
	public ConversorLayoutPdf(){
		linhaValida = false;
	};
	
	public List<String> retornaCamposLeiaute(String caminhoArquivoLeiaute) throws InvalidPasswordException, IOException{
		String[] array = retornaLinhaFiltradas(caminhoArquivoLeiaute);
		ArrayList<String> campos = new ArrayList<>();
		for(int i = 0; i < array.length ; i++){
			
			String linha = array[i];
			if(!linha.contains("Tipo de horário")){
				String[] split = linha.split(" ");
				String campo = split[split.length - 1];
				campo = campo.replaceAll("\\(", "");
				campo = campo.replaceAll("\\)", "");
				campos.add(campo);
			}else{
				campos.add("TIPO_HORARIO");
			}
		}
		
		return campos;
	}
	
	private String[] retornaContudoStringDePDf(String filePath) throws IOException, InvalidPasswordException {
		Path path = Paths.get(filePath);
		byte[] data = Files.readAllBytes(path);
	
		PDDocument load = PDDocument.load(data);
		
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String conteudo = pdfStripper.getText(load);
		load.close();
		
		return conteudo.split("\n");
		
	}
	
	private String[] retornaLinhaFiltradas(String filePath) throws InvalidPasswordException, IOException{
		String[] array = retornaContudoStringDePDf(filePath);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length ; i++) {
			String linha = array[i].trim();

			if(linhaValida && linha.length() > 3)
				sb.append(linha+"|");
			verificaLinhaValida(linha);
		}
		
		return sb.toString().split(Pattern.quote("|"));
	}
	
	private void verificaLinhaValida(String linha){

		if((linha.length() == 3 && linha.contains("/")) && linhaValida)
			linhaValida =false;
		if(!linhaValida)
			linhaValida = linha.contains("MáscaraDescrição Valor FixoDc");
	}
	
}
